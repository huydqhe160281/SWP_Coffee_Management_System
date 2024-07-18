<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Account" %>
<!DOCTYPE html>
<html>
<head>
    <title>Coffee Management System - Account Details</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <meta name="keywords" content="bootstrap, bootstrap admin template, admin theme, admin dashboard, dashboard template, admin template, responsive" />
    <meta name="author" content="Codedthemes" />
    <!-- Favicon icon -->
    <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet" />
    <!-- waves.css -->
    <link rel="stylesheet" href="assets/pages/waves/css/waves.min.css" type="text/css" media="all" />
    <!-- Required Fremwork -->
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap/css/bootstrap.min.css" />
    <!-- themify-icons line icon -->
    <link rel="stylesheet" type="text/css" href="assets/icon/themify-icons/themify-icons.css" />
    <!-- Font Awesome -->
    <link rel="stylesheet" type="text/css" href="assets/icon/font-awesome/css/font-awesome.min.css" />
    <!-- ico font -->
    <link rel="stylesheet" type="text/css" href="assets/icon/icofont/css/icofont.css" />
    <!-- Style.css -->
    <link rel="stylesheet" type="text/css" href="assets/css/style.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/jquery.mCustomScrollbar.css" />
    <style>
        /* Include styles from updateAccount.jsp */
        body {
            font-family: 'Open Sans', sans-serif;
            color: #333;
            background-color: #f4f4f4;
            padding: 20px;
        }

        h1, h2, h3, h4, h5, h6 {
            color: #333;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .card {
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            margin-bottom: 20px;
            padding: 20px;
        }

        .table-responsive {
            background: #ffffff;
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 8px;
        }

        .btn-back {
            float: right;
            background-color: #007bff;
            border-color: #007bff;
            color: #fff;
            padding: .375rem .75rem;
            font-size: 1rem;
            line-height: 1.5;
            border-radius: .25rem;
            cursor: pointer;
        }

        .btn-back:hover {
            background-color: #0069d9;
            border-color: #0062cc;
        }

        .status-active {
            color: green;
        }

        .status-expired {
            color: red;
        }
    </style>
</head>
<body>

    <!-- Pre-loader start -->
    <jsp:include page="./common/reloading.jsp"/>
    <!-- Pre-loader end -->

    <div id="pcoded" class="pcoded">
        <div class="pcoded-overlay-box"></div>
        <div class="pcoded-container navbar-wrapper">

            <!-- Heading start -->
            <jsp:include page="./common/headingAdmin.jsp"/>
            <!-- Heading end -->

            <div class="pcoded-main-container">
                <div class="pcoded-wrapper">

                    <!--Sidebar start-->
                    <jsp:include page="./common/sidebarAdmin.jsp"/>
                    <!--Sidebar end-->

                    <div class="pcoded-content">
                        <!-- Page-header start -->
                        <div class="page-header">
                            <div class="page-block">
                                <div class="row align-items-center">
                                    <div class="col-md-8">
                                        <div class="page-header-title">
                                            <h5 class="m-b-10">Account Management</h5>
                                            <p class="m-b-0">Quản lý Account</p>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <ul class="breadcrumb">
                                            <li class="breadcrumb-item">
                                                <a href="index.jsp"> <i class="fa fa-home"></i> </a>
                                            </li>
                                            <li class="breadcrumb-item">
                                                <a href="/discount">Account Management</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Page-header end -->

                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                        <div class="card">                                              
                                            <div class="card-block table-border-style">
                                                <div class="table-responsive">
                                                    <div class="container">
                                                        <h1>Account Details</h1>
                                                        <div class="form-group form-default">
                                                            <label class="">Account ID: </label>
                                                            <input type="text" name="username" class="form-control" value="<%= ((Account)request.getAttribute("account")).getAccountID() %>" readonly required>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <label class="">Username: </label>
                                                            <input type="text" name="username" class="form-control" value="<%= ((Account)request.getAttribute("account")).getUsername() %>" readonly required>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <label class="">Name:</label>
                                                            <input type="text" name="name" class="form-control" value="<%= ((Account)request.getAttribute("account")).getName() %>" readonly required>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <label class="">Phone:</label>
                                                            <input type="text" name="name" class="form-control" value="<%= ((Account)request.getAttribute("account")).getPhone() %>" readonly required>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <label class="">Email:</label>
                                                            <input type="text" name="name" class="form-control" value="<%= ((Account)request.getAttribute("account")).getEmail() %>" readonly required>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <label class="">Address:</label>
                                                            <input type="text" name="name" class="form-control" value="<%= ((Account)request.getAttribute("account")).getAddress() %>" readonly required>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <label class="">Status</label>
                                                            <input type="text" name="name" class="form-control" value="<%= ((Account)request.getAttribute("account")).isStatus() ? "Active" : "InActive" %>" readonly required>
                                                        </div>
                                                        <a href="account" class="btn btn-back mt-3">Back</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                            </div>
                            <!-- Main-body end -->

                            <div id="styleSelector"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Required Jquery -->
    <script type="text/javascript" src="assets/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="assets/js/jquery-ui/jquery-ui.min.js"></script>
    <script type="text/javascript" src="assets/js/popper.js/popper.min.js"></script>
    <script type="text/javascript" src="assets/js/bootstrap/js/bootstrap.min.js"></script>
    <!-- waves js -->
    <script src="assets/pages/waves/js/waves.min.js"></script>
    <!-- jquery slimscroll js -->
    <script type="text/javascript" src="assets/js/jquery-slimscroll/jquery.slimscroll.js"></script>
    <!-- Custom js -->
    <script src="assets/js/pcoded.min.js"></script>
    <script src="assets/js/vertical/vertical-layout.min.js"></script>
    <script src="assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script type="text/javascript" src="assets/js/script.js"></script>
</body>
</html>
