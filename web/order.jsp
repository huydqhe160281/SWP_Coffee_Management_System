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

            .btn_checkout {
                display: block;
                text-align: center;
                text-decoration: none;
                border: 1px solid gray;
                border-radius: 3px;
                padding: 10px;
                background-color: #448aff;
                color: white;
                margin-top: 20px;
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
                <a href="checkout" class="btn_checkout">PAYMENT</a>
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

                console.log("Adding to order:", productID, productName, size, price); // Kiểm tra giá trị truyền vào

                const orderList = document.getElementById('orderList');
                const orderItem = document.createElement('tr');

                const productNameElem = document.createElement('td');
                productNameElem.className = 'product-name';
                productNameElem.textContent = productName + " (" + size + ")";
                console.log("Product name element:", productNameElem.textContent); // Kiểm tra giá trị hiển thị

                const quantityInput = document.createElement('input');
                quantityInput.type = 'number';
                quantityInput.value = 1;
                quantityInput.min = 1;
                quantityInput.oninput = updateTotal;

                const quantityTd = document.createElement('td');
                quantityTd.appendChild(quantityInput);

                const priceElem = document.createElement('td');
                priceElem.className = 'price';
                priceElem.textContent = price;

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
                    total += quantity * price;
                });
                document.getElementById('totalAmount').textContent = total;
            }

            function removeItem(button) {
                const orderItem = button.parentElement.parentElement;
                orderItem.remove();
                updateTotal();
                saveOrderToCookie();
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
                        console.log("Loaded product name:", order.productName); // Kiểm tra giá trị từ cookie

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
        </script>
    </body>
</html>
