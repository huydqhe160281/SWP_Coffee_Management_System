<%-- 
    Document   : order
    Created on : May 29, 2024, 8:21:13 AM
    Author     : Namqd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Order Management</title>
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap/css/bootstrap.min.css" />
    </head>
    <body>
        <div class="container">
            <h2>Order List</h2>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Account ID</th>
                        <th>Order Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>${order.orderID}</td>
                            <td>${order.accountID}</td>
                            <td>${order.orderDate}</td>
                            <td>
                                <a href="order?action=view&orderId=${order.orderID}" class="btn btn-info">View</a>
                                <a href="order?action=delete&orderId=${order.orderID}" class="btn btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="order?action=add" class="btn btn-primary">Add Order</a>
        </div>
    </body>
</html>
