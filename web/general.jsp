<%--
    Document   : category
    Created on : May 20, 2024, 8:38:20 PM
    Author     : ADMIN
--%>

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
        <!-- Favicon icon -->
        <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon" />
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet" />
        <!-- waves.css -->
        <link rel="stylesheet" href="assets/pages/waves/css/waves.min.css" type="text/css" media="all" />
        <!-- Required Fremwork -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap/css/bootstrap.min.css" />
        <!-- waves.css -->
        <link rel="stylesheet" href="assets/pages/waves/css/waves.min.css" type="text/css" media="all" />
        <!-- themify-icons line icon -->
        <link rel="stylesheet" type="text/css" href="assets/icon/themify-icons/themify-icons.css" />
        <!-- Font Awesome -->
        <link rel="stylesheet" type="text/css" href="assets/icon/font-awesome/css/font-awesome.min.css" />
        <!-- ico font -->
        <link rel="stylesheet" type="text/css" href="assets/icon/icofont/css/icofont.css" />
        <!-- Style.css -->
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
    </style>
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
                                                <h5 class="m-b-10">General Management</h5>
                                                <p class="m-b-0">Quản lý Thông tin chung</p>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <ul class="breadcrumb">
                                                <li class="breadcrumb-item">
                                                    <a href="index.html"> <i class="fa fa-home"></i> </a>
                                                </li>
                                                <li class="breadcrumb-item">
                                                    <a href="/general">General Management</a>
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

                                                <!-- Sub header table start -->
                                                <div class="card-header">
                                                    <div class="card-header-left">
                                                        <button class="btn btn-warning waves-effect h-15" onclick="window.location.href = '/category_create'">
                                                            Edit Information
                                                        </button>
                                                    </div>
                                                    <div class="card-header-right">
                                                        <ul class="list-unstyled card-option">
                                                            <li><i class="fa fa fa-wrench open-card-option"></i></li>
                                                            <li><i class="fa fa-window-maximize full-card"></i></li>
                                                            <li><i class="fa fa-minus minimize-card"></i></li>
                                                            <li><i class="fa fa-refresh reload-card"></i></li>
                                                            <li><i class="fa fa-trash close-card"></i></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <!-- Sub header table end -->
                                                <div class="card-header">

                                                    <div class="card w-75 m-auto">
                                                        <div class="card-header">
                                                            <h5>General System Information</h5>
                                                            <!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
                                                        </div>
                                                        <div class="card-block">
                                                            <form class="form-material">
                                                                <div
                                                                    class="form-group form-default form-static-label"
                                                                    >
                                                                    <input
                                                                        type="email"
                                                                        name="generalID"
                                                                        class="form-control"
                                                                        placeholder="Enter Email"
                                                                        hidden
                                                                        />
                                                                    <span class="form-bar"></span>
                                                                </div>
                                                                <div
                                                                    class="form-group form-default form-static-label"
                                                                    >
                                                                    <input
                                                                        type="email"
                                                                        name="email"
                                                                        class="form-control"
                                                                        placeholder="Enter Email"
                                                                        value="${general.email}"
                                                                        disabled
                                                                        />
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Email (exa@gmail.com)</label>
                                                                </div>
                                                                <div
                                                                    class="form-group form-default form-static-label"
                                                                    >
                                                                    <input
                                                                        type="number"
                                                                        name="phone"
                                                                        class="form-control"
                                                                        placeholder="Enter Phone Number"
                                                                        disabled
                                                                        />
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Phone Number</label>
                                                                </div>
                                                                <div
                                                                    class="form-group form-default form-static-label"
                                                                    >
                                                                    <input
                                                                        type="text"
                                                                        name="nameApp"
                                                                        class="form-control"
                                                                        placeholder="Enter Name of App/Web"
                                                                        value="${general.nameApp}"
                                                                        disabled
                                                                        />
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Name of App/Web</label>
                                                                </div>
                                                                <div
                                                                    class="form-group form-default form-static-label">
                                                                    <input
                                                                        type="text"
                                                                        name="footer-email"
                                                                        class="form-control"
                                                                        placeholder="Enter Address"
                                                                        value="${general.address}"
                                                                        disabled
                                                                        />
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Address</label>
                                                                </div>
                                                                <div
                                                                    class="form-group form-default form-static-label"
                                                                    >
                                                                    <input
                                                                        type="text"
                                                                        name="logoImage"
                                                                        class="form-control"
                                                                        value="${general.logoImage}"
                                                                        disabled
                                                                        />
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Logo Image Url</label>
                                                                </div>
                                                                <div
                                                                    class="form-group form-default form-static-label"
                                                                    >
                                                                    <input
                                                                        type="text"
                                                                        name="fivicoImage"
                                                                        class="form-control"
                                                                        maxlength="6"
                                                                        value="${general.fivicoImage}"
                                                                        disabled
                                                                        />
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Favico Url</label>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                            <!-- Hover table card end -->
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

        <script>
            function submitSizeForm() {
                document.getElementById('sizeForm').submit();
            }
        </script>

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
