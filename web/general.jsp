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
        <jsp:include page="./common/import.jsp"/>
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
                                                        <button class="btn btn-primary waves-effect h-15" onclick="window.location.href = '/general_update'">
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
                                                            <form name="generalForm" class="form-material" action="general_update" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="hidden" name="generalID" class="form-control" value="${generalInfo.generalID}" />
                                                                    <span class="form-bar"></span>
                                                                </div>
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="email" name="email" class="form-control" placeholder="Enter Email" value="${generalInfo.email}" disabled/>
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Email (exa@gmail.com)</label>
                                                                </div>
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="number" name="phone" class="form-control" placeholder="Enter Phone Number" value="${generalInfo.phone}" disabled/>
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Phone Number</label>
                                                                </div>
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="nameApp" class="form-control" placeholder="Enter Name of App/Web" value="${generalInfo.nameApp}" disabled/>
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Name of App/Web</label>
                                                                </div>
                                                                <div class="form-group form-default form-static-label">
                                                                    <input type="text" name="address" class="form-control" placeholder="Enter Address" value="${generalInfo.address}" disabled/>
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label">Address</label>
                                                                </div>
                                                                <div class="d-flex flex-row mb-3">
                                                                    <div class="d-flex flex-column mr-3">
                                                                        <div class="mb-2">Logo Image</div>
                                                                        <img src="assets/images/${generalInfo.logoImage}" alt="Logo ${generalInfo.logoImage}" style="max-width: 200px; max-height: 200px;" disabled>
                                                                    </div>
                                                                    <div class="d-flex flex-column mr-3">
                                                                        <div class="mb-2">Favicon Image</div>
                                                                        <img src="assets/images/${generalInfo.fivicoImage}" alt="Favicon ${generalInfo.fivicoImage}" style="max-width: 200px; max-height: 200px;" disabled>
                                                                    </div>
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
