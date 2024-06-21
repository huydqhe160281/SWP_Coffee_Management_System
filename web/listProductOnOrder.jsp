<%-- 
    Document   : listProductOnOrder
    Created on : Jun 11, 2024, 4:15:31 PM
    Author     : Namqd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <c:forEach var="product" items="${products}">
        <div class="product-item" onclick="addToOrder(${product.productID}, '${product.productName}', ${product.unitPrice})">
            <img src="${product.image}" alt="${product.productName}">
            <span>${product.productName}</span>
            <span>${product.unitPrice}</span>
        </div>
    </c:forEach>
</body>
</html>
