<%--
    Document   : Supplier
    Created on : June 5, 2024, 8:38:20 PM
    Author     : ADMIN
--%>
<%@ page import="java.util.List" %>
<%@ page import="model.Supplier" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Supplier Management System</title>
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
            align-items: center;
            gap: 10px;
        }
        .search-form input[type="text"] {
            flex: 1;
            padding: 2px;
            margin: 0 2px;
        }
        .search-form input[type="submit"] {
            padding: 10px 20px;
            cursor: pointer;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 3px;
        }
        .search-form input[type="submit"]:hover {
            background-color: #0056b3;
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
                                                <h5 class="m-b-10">Supplier Management</h5>
                                                <p class="m-b-0">Quản lý nhà cung cấp</p>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <ul class="breadcrumb">
                                                <li class="breadcrumb-item">
                                                    <a href="index.html"> <i class="fa fa-home"></i> </a>
                                                </li>
                                                <li class="breadcrumb-item">
                                                    <a href="/supplier">Supplier Management</a>
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
                                                        <button class="btn btn-primary waves-effect h-15" onclick="window.location.href = '/supplier_create'">
                                                            Add New
                                                        </button>
                                                    </div>
                                                    <div class="p-15 p-b-0 w-25">
                                                        <form class="search-form" action="supplier_search" method="post">
                                                            Supplier Name: <input type="text" name="supplierName" />
                                                            Contact: <input type="text" name="contact" />
                                                            <button type="submit" class="search-button">
                                                                <i class="fa fa-search"></i>
                                                            </button>
                                                        </form>
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
                                                                <thead>
                                                                    <tr>
<!--                                                                        <th>#</th>-->
                                                                        <th>Name</th>
                                                                        <th>Contact</th>
                                                                        <th>Address</th>
                                                                        <th class="text-right">Action</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach items="${requestScope.suppliers}" var="supplier">
                                                                        <tr>
<!--                                                                            <th scope="row">${supplier.supplierID}</th>-->
                                                                            <td>${supplier.supplierName}</td>
                                                                            <td>${supplier.contact}</td>
                                                                            <td>${supplier.address}</td>
                                                                            <td class="text-right pt-3">
                                                                                <i class="fa fa-eye icon-spacing" aria-hidden="true" data-toggle="tooltip" data-placement="left" title="View"
                                                                                   onclick="window.location.href = '/supplier_view_detail?supplierID=${supplier.supplierID}'"></i>
                                                                                <i class="fa fa-pencil-square-o icon-spacing" aria-hidden="true" 
                                                                                   data-toggle="tooltip" data-placement="left" title="Edit"
                                                                                   onclick="window.location.href = '/supplier_update?supplierID=${supplier.supplierID}'"></i>
                                                                                <i class="fa fa-trash-o icon-spacing" aria-hidden="true" 
                                                                                   data-toggle="tooltip" data-placement="left" title="Delete"
                                                                                   onclick="deleteSupplier(${supplier.supplierID}, '${supplier.supplierName}')"></i>
                                                                            </td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
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
            function deleteSupplier(supplierID, supplierName) {
                if (confirm('Are you sure you want to delete supplier: ' + supplierName + '?')) {
                    var form = document.createElement('form');
                    form.method = 'GET';
                    form.action = 'supplier_delete';

                    var hiddenField = document.createElement('input');
                    hiddenField.type = 'hidden';
                    hiddenField.name = 'supplierID';
                    hiddenField.value = supplierID;
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
