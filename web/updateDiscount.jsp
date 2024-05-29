<%@ page import="model.Discount" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Coffee Management System</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="keywords" content="bootstrap, bootstrap admin template, admin theme, admin dashboard, dashboard template, admin template, responsive" />
        <meta name="author" content="Codedthemes" />
        <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon" />
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet" />
        <link rel="stylesheet" href="assets/pages/waves/css/waves.min.css" type="text/css" media="all" />
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" href="assets/pages/waves/css/waves.min.css" type="text/css" media="all" />
        <link rel="stylesheet" type="text/css" href="assets/icon/themify-icons/themify-icons.css" />
        <link rel="stylesheet" type="text/css" href="assets/icon/font-awesome/css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="assets/icon/icofont/css/icofont.css" />
        <link rel="stylesheet" type="text/css" href="assets/css/style.css" />
        <link rel="stylesheet" type="text/css" href="assets/css/jquery.mCustomScrollbar.css" />
    </head>
    <style>
        .limit-detail {
            max-width: 200px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .form-material {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            width: 50%;
            margin: auto;
        }
        .form-material input[type="text"],
        .form-material input[type="number"],
        .form-material input[type="date"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-material input[type="submit"] {
            width: 100%;
            padding: 10px;
            color: white;
            background-color: #5cb85c;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .form-material input[type="submit"]:hover {
            background-color: #45a045;
        }
        .alert-danger {
            color: white;
            background-color: #f44336;
            padding: 10px;
            border-radius: 4px;
            margin-bottom: 20px;
        }
        .status-active {
            color: green; /* Sets the text color to green */
        }
        .status-expired {
            color: red; /* Sets the text color to red */
        }
    </style>
    <body>
        <jsp:include page="./common/reloading.jsp"/>
        <div id="pcoded" class="pcoded">
            <div class="pcoded-overlay-box"></div>
            <div class="pcoded-container navbar-wrapper">
                <jsp:include page="./common/headingAdmin.jsp"/>
                <div class="pcoded-main-container">
                    <div class="pcoded-wrapper">
                        <jsp:include page="./common/sidebarAdmin.jsp"/>
                        <div class="pcoded-content">
                            <div class="page-header">
                                <div class="page-block">
                                    <div class="row align-items-center">
                                        <div```html
                                            <div class="col-md-4">
                                            <ul class="breadcrumb">
                                                <li class="breadcrumb-item">
                                                    <a href="index.html"> <i class="fa fa-home"></i> </a>
                                                </li>
                                                <li class="breadcrumb-item">
                                                    <a href="/discount">Discount Management</a>
                                                </li>
                                                <li class="breadcrumb-item">
                                                    <a href="/discount_update">Update Discount</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="pcoded-inner-content">
                                <div class="main-body">
                                    <div class="page-wrapper">
                                        <div class="page-body">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h3 class="text-center">Update Discount</h3>
                                                    <% if (request.getAttribute("error") != null) { %>
                                                    <div class="alert alert-danger" style="text-align: center;">
                                                        <%= request.getAttribute("error").toString() %>
                                                    </div>
                                                    <% } %>
                                                    <% 
                                                        Discount discount = (Discount) request.getAttribute("discount");
                                                        if (discount != null) { %>
                                                    <form class="form-material" action="discount_update" method="post">
                                                        <input type="hidden" name="discountID" value="<%= discount.getDiscountID() %>">
                                                        Code: <input type="text" name="code" value="<%= discount.getCode() %>" required><br>
                                                        Value (%): <input type="number" name="value" value="<%= discount.getValue() %>" required><br>
                                                        Start Date: <input type="date" name="startDate" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(discount.getStartDate()) %>" required><br>
                                                        End Date: <input type="date" name="endDate" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(discount.getEndDate()) %>" required><br>
                                                        Max Discount (VND): <input type="number" name="maxDiscount" value="<%= discount.getMaxDiscount() %>" required><br>
                                                        Quantity: <input type="number" name="quantity" value="<%= discount.getQuantity() %>" required><br>
                                                        Status: <input type="text" class="<%= discount.isStatus() ? "status-active" : "status-expired" %>" name="status" value="<%= discount.isStatus() ? "On going" : "Out of date" %>" readonly />
                                                        <input type="submit" value="Update">
                                                    </form>
                                                    <% } else { %>
                                                    <p>Discount information is not available. Please check the Discount ID.</p>
                                                    <% } %>
                                                    <a href="discount" class="btn btn-secondary mt-3" style="float: right;">Back</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="assets/js/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="assets/js/jquery-ui/jquery-ui.min.js"></script>
        <script type="text/javascript" src="assets/js/popper.js/popper.min.js"></script>
        <script type="text/javascript" src="assets/js/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/pages/waves/js/waves.min.js"></script>
        <script type="text/javascript" src="assets/js/jquery-slimscroll/jquery.slimscroll.js"></script>
        <script src="assets/js/pcoded.min.js"></script>
        <script src="assets/js/vertical/vertical-layout.min.js"></script>
        <script src="assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script type="text/javascript" src="assets/js/script.js"></script>
    </body>
</html>
