<%--
    Document   : productDetail
    Created on : May 20, 2024, 8:38:20 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./common/import.jsp"/>
        <title>Product Detail</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <style>
            .detail-section {
                margin-top: 20px;
            }
            .card-custom {
                border: 1px solid #dee2e6;
                border-radius: 10px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }
            .product-image {
                border-radius: 10px;
            }
            .card-header-custom {
                background-color: #f8f9fa;
                border-bottom: 1px solid #dee2e6;
                border-radius: 10px 10px 0 0;
            }
            .product-info {
                padding: 20px;
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
                                        <div class="col-md-4">
                                            <ul class="breadcrumb">
                                                <li class="breadcrumb-item">
                                                    <a href="index.html"> <i class="fa fa-home"></i> </a>
                                                </li>
                                                <li class="breadcrumb-item">
                                                    <a href="/product">Product Management</a>
                                                </li>
                                                <li class="breadcrumb-item">
                                                    <a href="/product_detail">Product Detail</a>
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
                                                        <h5>Product Detail</h5>
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

                                                <!-- Sub content start -->

                                                <div class="card w-75 mx-auto my-3 card-custom">
                                                    <div class="card-body product-info">
                                                        <c:choose>
                                                            <c:when test="${product != null}">
                                                                <div class="d-flex flex-row align-items-center">
                                                                    <div class="col-md-6 d-flex justify-content-center">
                                                                        <img src="${product.image ? product.image :  "/assets/images/noimage.jpg"}" class="img-fluid rounded product-image w-75" alt="${product.productName}">
                                                                    </div>
                                                                    <div class="col-md-6">
                                                                        <h3 class="text-primary"><strong>${product.productName}</strong></h3>
                                                                        <h5>Cost Price:</h5>
                                                                        <h5>Price:</h5>
                                                                        <h5>Status: <c:choose>
                                                                                <c:when test="${product.status}"><span class="badge badge-success">Đang bán</span></c:when>
                                                                                <c:otherwise><span class="badge badge-danger">Dừng bán</span></c:otherwise>
                                                                            </c:choose>
                                                                        </h5>
                                                                        <h5>Danh mục: ${product.category.categoryName}</h5>
                                                                        <h5>Chi tiết danh mục:</h5> <p>${product.category.detail}</p>
                                                                    </div>
                                                                </div>
                                                                <div class="row detail-section">
                                                                    <div class="col-md-12">
                                                                        <h5>Description</h5>
                                                                        <p>${product.description}</p>
                                                                        <h5>Recipe</h5>
                                                                        <p>${product.recipe}</p>
                                                                    </div>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <jsp:include page="./common/noDataLog.jsp"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </div>

                                                <!-- Sub content end -->
                                            </div>
                                        </div>
                                        <!-- Page-body end -->
                                    </div>
                                </div>
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
