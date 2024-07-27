<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Order Page</title>
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <style>
            body {
                font-family: 'Open Sans', sans-serif;
                color: #333;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
            }

            .container {
                display: flex;
                flex-direction: row;
                height: auto;
            }

            .main-content {
                flex: 1;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }

            .category-item, .product-item {
                width: 100px;
                height: 70px;
                cursor: pointer;
                padding: 10px;
                margin-bottom: 10px;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
                border-radius: 4px;
                text-align: center;
                display: inline-flex;
                justify-content: center;
                align-items: center;
            }

            .category-item:hover, .product-item:hover {
                background-color: #e9e9e9;
            }

            .order-sidebar {
                width: 50%;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
                margin-left: 20px;
            }

            .order-table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            .order-table th, .order-table td {
                padding: 8px;
                text-align: center;
            }

            .order-table th {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            .order-table td input {
                width: 60px;
                text-align: center;
            }

            .order-table td.product-name {
                text-align: left;
            }

            .btn_payment {
                display: block;
                text-align: center;
                text-decoration: none;
                border: 1px solid gray;
                border-radius: 3px;
                padding: 10px;
                color: white;
                margin-top: 20px;
            }

            .btn_cash {
                background-color: #4caf50;
            }

            .btn_vnpay {
                background-color: #448aff;
            }

            #discountModal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0,0,0,0.4);
            }

            .modal-content {
                background-color: #fefefe;
                margin: 10% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 40%;
            }

            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="main-content">
                <h3>Categories</h3>
                <div id="categories">
                    <c:forEach var="category" items="${categories}">
                        <div class="category-item" onclick="window.location.href = 'order?categoryID=${category.categoryID}'">
                            ${category.categoryName}
                        </div>
                    </c:forEach>
                </div>
                <h3>Products</h3>
                <div id="products">
                    <c:forEach var="product" items="${products}">
                        <div class="product-item" 
                             data-product-id="${product.productID}"
                             data-product-name="${product.productName}"
                             data-product-size="${product.type}"
                             data-product-price="${product.price}"
                             onclick="addToOrder(this)">
                            ${product.productName} (${product.type})
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="order-sidebar">
                <h3>Order</h3>
                <table class="order-table">
                    <thead>
                        <tr>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="orderList"></tbody>
                </table>
                <div>Total: <span id="totalAmount">0</span></div>
                <div>Discount: <span id="discountInfo">None</span></div>
                <div>New Total: <span id="newTotalAmount">0</span></div>
                <button class="btn_discount" onclick="showDiscountModal()">Add Discount</button>
                <button class="btn_payment btn_cash" onclick="checkout('cash')">Payment in Cash</button>
                <button class="btn_payment btn_vnpay" onclick="checkout('vnpay')">Payment via VNPay Wallet</button>
            </div>
        </div>

        <!-- Discount Modal -->
        <div id="discountModal">
            <div class="modal-content">
                <span class="close" onclick="closeDiscountModal()">&times;</span>
                <h2>Select Discount</h2>
                <div id="discounts"></div>
            </div>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                loadOrderFromCookie();
            });

            function addToOrder(element) {
                const productID = element.getAttribute('data-product-id');
                const productName = element.getAttribute('data-product-name');
                const size = element.getAttribute('data-product-size');
                const price = parseFloat(element.getAttribute('data-product-price'));

                const orderList = document.getElementById('orderList');
                const orderItem = document.createElement('tr');

                const productNameElem = document.createElement('td');
                productNameElem.className = 'product-name';
                productNameElem.textContent = productName + " (" + size + ")";

                const quantityInput = document.createElement('input');
                quantityInput.type = 'number';
                quantityInput.value = 1;
                quantityInput.min = 1;
                quantityInput.oninput = updateTotal;

                const quantityTd = document.createElement('td');
                quantityTd.appendChild(quantityInput);

                const priceElem = document.createElement('td');
                priceElem.className = 'price';
                priceElem.textContent = price.toFixed(0);

                const removeBtn = document.createElement('button');
                removeBtn.textContent = 'X';
                removeBtn.onclick = function () {
                    orderList.removeChild(orderItem);
                    updateTotal();
                    saveOrderToCookie();
                };

                const removeTd = document.createElement('td');
                removeTd.appendChild(removeBtn);

                orderItem.appendChild(productNameElem);
                orderItem.appendChild(quantityTd);
                orderItem.appendChild(priceElem);
                orderItem.appendChild(removeTd);

                orderList.appendChild(orderItem);
                updateTotal();
                saveOrderToCookie();
            }

            function updateTotal() {
                const orderItems = document.querySelectorAll('#orderList tr');
                let total = 0;
                orderItems.forEach(item => {
                    const quantity = item.querySelector('input').value;
                    const price = item.querySelector('.price').textContent;
                    total += quantity * parseFloat(price.replace(/,/g, ''));
                });
                document.getElementById('totalAmount').textContent = formatNumber(total);
                updateNewTotal();
            }

            function applyDiscount(discount) {
                const totalElem = document.getElementById('totalAmount');
                const total = parseFloat(totalElem.textContent.replace(/,/g, ''));
                let newTotal = total;
                const discountValue = total * (discount.Value / 100);

                if (discountValue < discount.MaxDiscount) {
                    newTotal = total - discountValue;
                } else {
                    newTotal = total - discount.MaxDiscount;
                }

                document.getElementById('discountInfo').textContent = 'Voucher ' + discount.Code + ' - ' + discount.Value + '% (Max: ' + discount.MaxDiscount + ')';
                document.getElementById('newTotalAmount').textContent = formatNumber(newTotal);
                closeDiscountModal();
            }

            function updateNewTotal() {
                const discountInfo = document.getElementById('discountInfo').textContent;
                if (discountInfo !== 'None') {
                    const totalElem = document.getElementById('totalAmount');
                    const total = parseFloat(totalElem.textContent.replace(/,/g, ''));
                    const discount = discountInfo.match(/\d+/g).map(Number);
                    let newTotal = total;
                    const discountValue = total * (discount[1] / 100);

                    if (discountValue < discount[2]) {
                        newTotal = total - discountValue;
                    } else {
                        newTotal = total - discount[2];
                    }
                    document.getElementById('newTotalAmount').textContent = formatNumber(newTotal);
                } else {
                    document.getElementById('newTotalAmount').textContent = document.getElementById('totalAmount').textContent;
                }
            }

            function showDiscountModal() {
                const modal = document.getElementById('discountModal');
                modal.style.display = 'block';

                fetch('order?action=getActiveDiscounts')
                        .then(response => response.json())
                        .then(data => {
                            const discountsDiv = document.getElementById('discounts');
                            discountsDiv.innerHTML = '';

                            data.forEach(discount => {
                                const discountDiv = document.createElement('div');
                                discountDiv.textContent = 'Voucher ' + discount.Code + ' - ' + discount.Value + '% (Max: ' + discount.MaxDiscount + ')';
                                discountDiv.onclick = () => applyDiscount(discount);
                                discountsDiv.appendChild(discountDiv);
                            });
                        });
            }

            function closeDiscountModal() {
                const modal = document.getElementById('discountModal');
                modal.style.display = 'none';
            }

            function checkout(paymentMethod) {
                const orderDetails = getOrderDetailsFromDOM();
                const discountInfo = document.getElementById('discountInfo').textContent;

                if (paymentMethod === 'cash') {
                    // Save order and show payment success page
                    saveOrderToDatabase(orderDetails, discountInfo, 'cash');
                } else if (paymentMethod === 'vnpay') {
                    // Redirect to VNPay payment page
                    redirectToVNPayPage(orderDetails, discountInfo);
                }
            }

            function getOrderDetailsFromDOM() {
                const orderItems = document.querySelectorAll('#orderList tr');
                const orderList = [];
                orderItems.forEach(item => {
                    const productName = item.querySelector('td:first-child').textContent;
                    const quantity = item.querySelector('input').value;
                    const price = item.querySelector('.price').textContent;
                    orderList.push({productName, quantity, price});
                });
                return orderList;
            }

            function saveOrderToDatabase(orderDetails, discountInfo, paymentMethod) {
                const orderData = {
                    orderDetails: orderDetails,
                    discountInfo: discountInfo,
                    paymentMethod: paymentMethod
                };

                fetch('order?action=saveOrder', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(orderData)
                })
                        .then(response => {
                            if (response.ok) {
                                window.location.href = 'payment_success.jsp';
                            } else {
                                alert('Error processing your order. Please try again.');
                            }
                        });
            }

            function redirectToVNPayPage(orderDetails, discountInfo) {
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = 'vnpay_pay.jsp';

                const orderDetailsInput = document.createElement('input');
                orderDetailsInput.type = 'hidden';
                orderDetailsInput.name = 'orderDetails';
                orderDetailsInput.value = JSON.stringify(orderDetails);
                form.appendChild(orderDetailsInput);

                const discountInfoInput = document.createElement('input');
                discountInfoInput.type = 'hidden';
                discountInfoInput.name = 'discountInfo';
                discountInfoInput.value = discountInfo;
                form.appendChild(discountInfoInput);

                document.body.appendChild(form);
                form.submit();
            }

            function saveOrderToCookie() {
                const orderItems = document.querySelectorAll('#orderList tr');
                const orderList = [];
                orderItems.forEach(item => {
                    const productName = item.querySelector('td:first-child').textContent;
                    const quantity = item.querySelector('input').value;
                    const price = item.querySelector('.price').textContent;
                    orderList.push({productName, quantity, price});
                });
                document.cookie = "orderList=" + JSON.stringify(orderList) + "; path=/";
            }

            function loadOrderFromCookie() {
                const cookies = document.cookie.split(';');
                const orderCookie = cookies.find(cookie => cookie.trim().startsWith("orderList="));
                if (orderCookie) {
                    const orderList = JSON.parse(orderCookie.split('=')[1]);
                    const orderListElem = document.getElementById('orderList');
                    orderList.forEach(order => {
                        const orderItem = document.createElement('tr');

                        const productNameElem = document.createElement('td');
                        productNameElem.className = 'product-name';
                        productNameElem.textContent = order.productName;

                        const quantityInput = document.createElement('input');
                        quantityInput.type = 'number';
                        quantityInput.value = order.quantity;
                        quantityInput.min = 1;
                        quantityInput.oninput = updateTotal;

                        const quantityTd = document.createElement('td');
                        quantityTd.appendChild(quantityInput);

                        const priceElem = document.createElement('td');
                        priceElem.className = 'price';
                        priceElem.textContent = order.price;

                        const removeBtn = document.createElement('button');
                        removeBtn.textContent = 'X';
                        removeBtn.onclick = function () {
                            orderListElem.removeChild(orderItem);
                            updateTotal();
                            saveOrderToCookie();
                        };

                        const removeTd = document.createElement('td');
                        removeTd.appendChild(removeBtn);

                        orderItem.appendChild(productNameElem);
                        orderItem.appendChild(quantityTd);
                        orderItem.appendChild(priceElem);
                        orderItem.appendChild(removeTd);

                        orderListElem.appendChild(orderItem);
                    });
                    updateTotal();
                }
            }

            function formatNumber(num) {
                return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            }
        </script>
    </body>
</html>
