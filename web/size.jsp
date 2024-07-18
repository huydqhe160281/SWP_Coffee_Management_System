<%-- 
    Document   : size
    Created on : 24-06-2024, 23:54:38
    Author     : Dinh Hai
--%>

<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
        .search-form {
            display: flex;
            align-items: center; /* Căn chỉnh các phần tử theo trục dọc */
            gap: 10px; /* Khoảng cách giữa các phần tử */
        }
        .search-form input[type="text"],
        .search-form input[type="number"],
        .search-form input[type="date"] {
            flex: 1; /* Mỗi input sẽ có độ rộng bằng nhau */
            padding: 2px; /* Padding cho input để tăng kích thước click */
            margin: 0 2px; /* Khoảng cách giữa các input */
        }
        .search-form input[type="submit"] {
            padding: 10px 20px; /* Padding cho nút submit để dễ dàng click */
            cursor: pointer; /* Con trỏ chuột khi di chuyển vào nút */
            background-color: #007bff; /* Màu nền cho nút */
            color: white; /* Màu chữ cho nút */
            border: none; /* Bỏ đường viền */
            border-radius: 3px;
        }
        .search-form input[type="submit"]:hover {
            background-color: #0056b3; /* Màu khi hover */
        }
        .status-active {
            color: green; /* Sets the text color to green */
        }
        .status-expired {
            color: red; /* Sets the text color to red */
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
                                                <h5 class="m-b-10">Size Management</h5>
                                                <p class="m-b-0">Quản lý Size</p>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <ul class="breadcrumb">
                                                <li class="breadcrumb-item">
                                                    <a href="index.html"> <i class="fa fa-home"></i> </a>
                                                </li>
                                                <li class="breadcrumb-item">
                                                    <a href="/discount">Size Management</a>
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
                                                    <div class="d-flex justify-content-between align-items-center pt-3">
                                                        <button class="btn btn-primary waves-effect h-15" onclick="window.location.href = '/size_create'">
                                                            Add New
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
                                                <div class="card-block table-border-style">
                                                    <div class="table-responsive">
                                                        <div class="container">
                                                            <table class="table table-hover">
                                                                <tr>
                                                                    <th>#</th>
                                                                    <th>Type</th>
                                                                    <th>Description</th>
                                                                    <th class="text-right">Action</th>
                                                                </tr>
                                                                <c:forEach items="${requestScope.sizes}" var="size">
                                                                    <tr>
                                                                        <td>${size.sizeID}</td>
                                                                        <td>${size.type}</td>
                                                                        <td>${size.description}</td>
                                                                        <td class="text-right pt-3">
                                                                            <i class="fa fa-eye icon-spacing" aria-hidden="true" data-toggle="tooltip" data-placement="left" title="View" 
                                                                               onclick="window.location.href = '/size_view_detail?sizeID=${size.sizeID}'"></i>
                                                                            <i class="fa fa-pencil-square-o icon-spacing" aria-hidden="true" 
                                                                               data-toggle="tooltip" data-placement=left title="Edit"
                                                                               onclick="window.location.href = '/size_update?sizeID=${size.sizeID}'"></i>
                                                                            <i class="fa fa-trash-o icon-spacing" aria-hidden="true" 
                                                                               data-toggle="tooltip" data-placement="left" title="Delete"
                                                                               onclick="deleteSize(${size.sizeID})"></i>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </table>
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

        <script type="text/javascript">
    function deleteSize(sizeID, type) {
        if (confirm('Are you sure you want to delete this size ?')) {
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'size_delete'; // Replace with your servlet URL for deleting size

            var hiddenField = document.createElement('input');
            hiddenField.type = 'hidden';
            hiddenField.name = 'sizeID';
            hiddenField.value = sizeID;
            form.appendChild(hiddenField);

            document.body.appendChild(form);
            form.submit();
        }
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
