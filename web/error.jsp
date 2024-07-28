<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>Đã xảy ra lỗi</h1>
    <p>${errorMessage}</p>
    <button onclick="window.history.back()">Quay lại</button>
</body>
</html>

