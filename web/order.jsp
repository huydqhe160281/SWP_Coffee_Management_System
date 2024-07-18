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
            .size-option {
                display: inline-block;
                padding: 5px 10px;
                margin: 5px;
                border: 1px solid #ddd;
                border-radius: 4px;
                cursor: pointer;
            }
            .size-option.disabled {
                background-color: #e9e9e9;
                cursor: not-allowed;
            }
            .size-option.selected {
                background-color: #007bff;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="main-content">
                <h3>Categories</h3>
                <div id="categories">
                    <c:forEach var="category" items="${categories}">
                        <div class="category-item">
                            <a href="order?categoryID=${category.categoryID}">
                                ${category.categoryName}
                            </a>
                        </div>
                    </c:forEach>
                </div>
                <h3>Products</h3>
                <div id="products">
                    <c:forEach var="product" items="${products}">
                        <div class="product-item">
                            <a href="order?categoryID=${selectedCategoryID}&productID=${product.productID}">
                                ${product.productName}
                            </a>
                        </div>
                    </c:forEach>
                </div>
                <h3>Sizes</h3>
                <div id="sizes">
                    <c:forEach var="size" items="${sizes}">
                        <div class="size-option ${size eq 'M' ? 'M' : ''} ${size eq 'L' ? 'L' : ''} ${size eq 'X' ? 'X' : ''}">
                            ${size}
                        </div>
                    </c:forEach>
                </div>
                <button id="addProduct" class="btn btn-primary">Add</button>
            </div>
            <div class="order-sidebar">
                <h3>Order</h3>
                <div id="orderList"></div>
                <div>Total: <span id="totalAmount"></span></div>
            </div>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const addProductBtn = document.getElementById("addProduct");
                addProductBtn.addEventListener("click", function () {
                    const selectedSize = document.querySelector(".size-option.selected");
                    if (selectedSize) {
                        const sizeType = selectedSize.textContent.trim();
                        const productID = "${selectedProductID}";
                        const productName = "${products[0].productName}";
                        const unitPrice = parseFloat("${products[0].price}");

                        const orderList = document.getElementById("orderList");
                        const orderItem = document.createElement("div");
                        orderItem.classList.add("order-item");

                        const nameElem = document.createElement("span");
                        nameElem.textContent = productName + " (" + sizeType + ")";
                        orderItem.appendChild(nameElem);

                        const quantityInput = document.createElement("input");
                        quantityInput.type = "number";
                        quantityInput.value = 1;
                        quantityInput.min = 1;
                        quantityInput.addEventListener("input", updateTotal);
                        orderItem.appendChild(quantityInput);

                        const priceElem = document.createElement("span");
                        priceElem.textContent = formatNumber(unitPrice);
                        orderItem.appendChild(priceElem);

                        const removeBtn = document.createElement("button");
                        removeBtn.textContent = "X";
                        removeBtn.addEventListener("click", function () {
                            orderList.removeChild(orderItem);
                            updateTotal();
                        });
                        orderItem.appendChild(removeBtn);

                        orderList.appendChild(orderItem);
                        updateTotal();
                    } else {
                        alert("Please select a size.");
                    }
                });

                const sizeOptions = document.querySelectorAll(".size-option");
                sizeOptions.forEach(option => {
                    option.addEventListener("click", function () {
                        sizeOptions.forEach(opt => opt.classList.remove("selected"));
                        option.classList.add("selected");
                    });
                });

                async function updateTotal() {
                    const orderItems = document.querySelectorAll(".order-item");
                    let total = 0;
                    orderItems.forEach(item => {
                        const quantityInput = item.querySelector("input[type='number']");
                        const priceSpan = item.querySelector("span");

                        if (quantityInput && priceSpan) {
                            const quantity = parseInt(quantityInput.value, 10);
                            const price = parseFloat(priceSpan.textContent.replace(/,/g, ''));

                            if (!isNaN(quantity) && !isNaN(price)) {
                                total += quantity * price;
                            }
                        }
                    });
                    document.getElementById("totalAmount").textContent = formatNumber(total);
                }

                function formatNumber(num) {
                    return num.toLocaleString('en-US', {minimumFractionDigits: 0, maximumFractionDigits: 2});
                }
            });
        </script>
    </body>
</html>
