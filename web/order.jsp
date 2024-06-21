<!DOCTYPE html>
<html>
    <head>
        <title>Coffee Shop Order</title>
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="assets/js/script.js"></script>

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

            .sidebar, .order-sidebar {
                width: 20%;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
                margin-right: 20px;
            }

            .main-content {
                flex: 1;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }

            .category-item, .product-item {
                cursor: pointer;
                padding: 10px;
                margin-bottom: 10px;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
                border-radius: 4px;
                text-align: center;
            }

            .category-item:hover, .product-item:hover {
                background-color: #e9e9e9;
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

            img {
                width: 100px;
                height: 100px;
                object-fit: cover;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="sidebar">
                <h3>Categories</h3>
                <div id="categories">
                    <c:forEach var="category" items="${categories}">
                        <div class="category-item" onclick="loadProducts(${category.categoryID})">
                            ${category.categoryName}
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="main-content">
                <h3>Products</h3>
                <div id="products">
                    <!-- Products will be loaded here -->
                </div>
            </div>
            <div class="order-sidebar">
                <h3>Order</h3>
                <div id="orderList"></div>
                <div>Total: <span id="totalAmount">0</span></div>
            </div>
        </div>

        <script>
            function loadProducts(categoryID) {
                $.get('OrderServlet?action=getProducts&categoryID=' + categoryID, function (data) {
                    $('#products').html(data);
                });
            }

            function addToOrder(productID, productName, unitPrice) {
                const orderList = document.getElementById("orderList");
                const existingItem = document.getElementById(`orderItem-${productID}`);

                if (existingItem) {
                    const quantityInput = existingItem.querySelector(".quantity");
                    quantityInput.value = parseInt(quantityInput.value) + 1;
                } else {
                    const orderItem = document.createElement("div");
                    orderItem.id = `orderItem-${productID}`;
                    orderItem.className = "order-item";
                    orderItem.innerHTML = `
                    <span>${productName}</span>
                    <input type="number" class="quantity" value="1" min="1" onchange="updateTotal()">
                    <span class="unit-price">${unitPrice}</span>
                    <span class="total-price">${unitPrice}</span>
                    <button onclick="removeFromOrder(${productID})">Remove</button>
                    `;
                    orderList.appendChild(orderItem);
                }

                updateTotal();
            }

            function updateTotal() {
                const orderItems = document.querySelectorAll(".order-item");
                let total = 0;

                orderItems.forEach(item => {
                    const quantity = item.querySelector(".quantity").value;
                    const unitPrice = item.querySelector(".unit-price").textContent;
                    const totalPrice = quantity * unitPrice;
                    item.querySelector(".total-price").textContent = totalPrice;
                    total += totalPrice;
                });

                document.getElementById("totalAmount").textContent = total;
            }

            function removeFromOrder(productID) {
                const orderItem = document.getElementById(`orderItem-${productID}`);
                orderItem.remove();
                updateTotal();
            }
        </script>
    </body>

</html>
