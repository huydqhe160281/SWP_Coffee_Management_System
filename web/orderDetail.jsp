<%-- 
    Document   : orderDetail
    Created on : May 29, 2024, 8:23:54 AM
    Author     : Namqd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Order Details</title>
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap/css/bootstrap.min.css" />
    </head>
    <body>
        <div class="container">
            <h2>Order Details</h2>
            <table class="table table-bordered">
                <tr>
                    <th>Order ID</th>
                    <td>${order.orderID}</td>
                </tr>
                <tr>
                    <th>Account</th>
                    <td>${order.accountName}</td>
                </tr>
                <tr>
                    <th>Order Date</th>
                    <td>${order.orderDate}</td>
                </tr>
            </table>

            <h3>Order Items</h3>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Unit Price</th>
                        <th>Quantity</th>
                        <th>Note</th>
                        <th>Discount</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="orderDetail" items="${orderDetails}">
                        <tr>
                            <td>${orderDetail.productName}</td>
                            <td>${orderDetail.unitPrice}</td>
                            <td>${orderDetail.quantity}</td>
                            <td>${orderDetail.note}</td>
                            <td>${orderDetail.value}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <a href="order?action=list" class="btn btn-primary">Back to Order List</a>
        </div>
    </body>
</html>
