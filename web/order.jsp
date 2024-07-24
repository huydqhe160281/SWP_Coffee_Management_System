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
                height: 100vh;
            }
            .main-content {
                flex: 1;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }
            .category-item, .product-item {
                width: 100px;
                height: auto;
                cursor: pointer;
                padding: 10px;
                margin-bottom: 10px;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
                border-radius: 4px;
                text-align: center;
                display: inline-block;
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
            .order-item {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px;
                margin-bottom: 10px;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
                border-radius: 4px;
            }
            .order-item input {
                width: 50px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="main-content">
                <h3>Categories</h3>
                <div id="categories">
                    <c:forEach var="category" items="${categories}">
                        <div class="category-item" onclick="window.location.href='order?categoryID=${category.categoryID}'">
                            ${category.categoryName}
                        </div>
                    </c:forEach>
                </div>
                <h3>Products</h3>
                <div id="products">
                    <c:forEach var="product" items="${products}">
                        <div class="product-item" onclick="addToOrder('${product.productID}', '${product.productName}', '${product.type}', ${product.price})">
                            ${product.productName} (${product.type})
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="order-sidebar">
                <h3>Order</h3>
                <div id="orderList"></div>
                <div>Total: <span id="totalAmount">0</span></div>
            </div>
        </div>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                loadOrderFromCookie();
            });

            function addToOrder(productID, productName, size, price) {
                const orderList = document.getElementById('orderList');
                const orderItem = document.createElement('div');
                orderItem.className = 'order-item';

                const productNameElem = document.createElement('span');
                productNameElem.textContent = `${product.productName} (${size})`;

                const quantityInput = document.createElement('input');
                quantityInput.type = 'number';
                quantityInput.value = 1;
                quantityInput.min = 1;
                quantityInput.oninput = updateTotal;

                const priceElem = document.createElement('span');
                priceElem.className = 'price';
                priceElem.textContent = price;

                const removeBtn = document.createElement('button');
                removeBtn.textContent = 'X';
                removeBtn.onclick = function() {
                    orderList.removeChild(orderItem);
                    updateTotal();
                    saveOrderToCookie();
                };

                orderItem.appendChild(productNameElem);
                orderItem.appendChild(quantityInput);
                orderItem.appendChild(priceElem);
                orderItem.appendChild(removeBtn);

                orderList.appendChild(orderItem);
                updateTotal();
                saveOrderToCookie();
            }

            function updateTotal() {
                const orderItems = document.querySelectorAll('.order-item');
                let total = 0;
                orderItems.forEach(item => {
                    const quantity = item.querySelector('input').value;
                    const price = item.querySelector('.price').textContent;
                    total += quantity * price;
                });
                document.getElementById('totalAmount').textContent = total;
            }

            function removeItem(button) {
                const orderItem = button.parentElement;
                orderItem.remove();
                updateTotal();
                saveOrderToCookie();
            }

            function saveOrderToCookie() {
                const orderItems = document.querySelectorAll('.order-item');
                const orderList = [];
                orderItems.forEach(item => {
                    const productName = item.querySelector('span').textContent;
                    const quantity = item.querySelector('input').value;
                    const price = item.querySelector('.price').textContent;
                    orderList.push({ productName, quantity, price });
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
                        const orderItem = document.createElement('div');
                        orderItem.className = 'order-item';

                        const productNameElem = document.createElement('span');
                        productNameElem.textContent = order.product.productName;

                        const quantityInput = document.createElement('input');
                        quantityInput.type = 'number';
                        quantityInput.value = order.quantity;
                        quantityInput.min = 1;
                        quantityInput.oninput = updateTotal;

                        const priceElem = document.createElement('span');
                        priceElem.className = 'price';
                        priceElem.textContent = order.price;

                        const removeBtn = document.createElement('button');
                        removeBtn.textContent = 'X';
                        removeBtn.onclick = function() {
                            orderListElem.removeChild(orderItem);
                            updateTotal();
                            saveOrderToCookie();
                        };

                        orderItem.appendChild(productNameElem);
                        orderItem.appendChild(quantityInput);
                        orderItem.appendChild(priceElem);
                        orderItem.appendChild(removeBtn);

                        orderListElem.appendChild(orderItem);
                    });
                    updateTotal();
                }
            }
        </script>
    </body>
</html>
