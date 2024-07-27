<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Checkout</title>
        <style>
            body {
                font-family: 'Open Sans', sans-serif;
                color: #333;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
            }
            .container {
                width: 80%;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }
            .order-details {
                margin-bottom: 20px;
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
            .btn-payment {
                display: inline-block;
                text-decoration: none;
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .btn-payment:disabled {
                background-color: #ddd;
                cursor: not-allowed;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h3>Checkout</h3>
            <div class="order-details">
                <c:forEach var="item" items="${orderDetails}">
                    <div class="order-item">
                        <span>${item.productName}</span>
                        <span>${item.quantity}</span>
                        <span>${item.unitPrice}</span>
                    </div>
                </c:forEach>
            </div>
            <div>Total: ${order.totalAmount}</div>
            <div>
                <label><input type="radio" name="paymentMethod" value="cash" onchange="togglePaymentButton()"> Cash</label>
                <label><input type="radio" name="paymentMethod" value="vnpay" onchange="togglePaymentButton()"> VNPay</label>
            </div>
            <button id="paymentButton" class="btn-payment" disabled onclick="processPayment()">PAYMENT</button>
        </div>

        <script>
            function togglePaymentButton() {
                const paymentButton = document.getElementById('paymentButton');
                paymentButton.disabled = !document.querySelector('input[name="paymentMethod"]:checked');
            }

            function processPayment() {
                const paymentMethod = document.querySelector('input[name="paymentMethod"]:checked').value;
                if (paymentMethod === 'cash') {
                    window.location.href = 'printInvoice';
                } else if (paymentMethod === 'vnpay') {
                    // Redirect to VNPay payment page
                    window.location.href = 'vnpayPayment';
                }
            }
        </script>
    </body>
</html>
