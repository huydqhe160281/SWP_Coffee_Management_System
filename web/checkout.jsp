<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Checkout Success</title>
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <style>
            body {
                font-family: 'Open Sans', sans-serif;
                color: #333;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
                text-align: center;
            }

            .container {
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
                max-width: 500px;
                margin: auto;
                border-radius: 4px;
            }

            .btn_print {
                display: inline-block;
                text-decoration: none;
                border: 1px solid gray;
                border-radius: 3px;
                padding: 10px 20px;
                color: white;
                background-color: #007bff;
                margin-top: 20px;
            }

            .btn_print:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Checkout thành công!</h2>
            <p>Đơn hàng của bạn đã được lưu thành công.</p>
            <a href="#" class="btn_print" onclick="window.print()">In hóa đơn</a>
        </div>
    </body>
</html>
