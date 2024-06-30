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
                width: 20%;
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
                            <a href="order?productID">
                                ${product.productName}
                            </a>      
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
    </body>
</html>
