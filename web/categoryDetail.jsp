<%--
    Document   : category
    Created on : May 20, 2024, 8:38:20 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        .marquee {
            color: red;
            font-size: 16px;
            font-weight: 600;
            display: inline-block;
            animation: bounce 2s infinite;
        }

        @keyframes bounce {
            0%, 100% {
                transform: translateX(0);
            }
            50% {
                transform: translateX(100px); /* Bạn có thể thay đổi giá trị này */
            }
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
                                        <div class="col-md-4">
                                            <ul class="breadcrumb">
                                                <li class="breadcrumb-item">
                                                    <a href="index.html"> <i class="fa fa-home"></i> </a>
                                                </li>
                                                <li class="breadcrumb-item">
                                                    <a href="/category">Category Management</a>
                                                </li>
                                                <li class="breadcrumb-item">
                                                    <a>Category Detail</a>
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
                                        <div class="page-body button-page">
                                            <div class="row">
                                                <!-- bootstrap modal start -->
                                                <div class="col-sm-12">
                                                    <c:if test="${not empty category}">
                                                        <div class="card">
                                                            <div class="card-header">
                                                                <form id="sizeForm" method="post" action="category_detail" class="mr-2">
                                                                    <select class="custom-select w-25" id="inputGroupSelect01" name="categoryID" onchange="submitSizeForm()">
                                                                        <c:forEach items="${requestScope.cList}" var="c">
                                                                            <option value="${c.categoryID}"  ${category.categoryID ==  c.categoryID ? 'selected' : ''}><a href="/category_detail?categoryID=${c.categoryID}">${c.categoryName}</a></option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </form>
                                                            </div>
                                                            <div class="card-block">
                                                                <div class="row">
                                                                    <div class="col-sm-12">
                                                                        <div class="">
                                                                            Detail: ${category.detail}
                                                                        </div>
                                                                    </div>
                                                                    <div class="card-block table-border-style w-100">
                                                                        <div class="table-responsive">
                                                                            <c:choose>
                                                                                <c:when test="${empty requestScope.pList}">
                                                                                    <p class="marquee">There are no products in this category.</p>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <table class="table table-hover w-100">
                                                                                        <thead>
                                                                                            <tr>
                                                                                                <th scope="col" class="font-weight-bold">#</th>
                                                                                                <th scope="col" class="font-weight-bold">
                                                                                                    <a href="?sortType=${sortType == 'asc' ? 'desc' : 'asc'}&indexPage=${indexPage}&sizePage=${sizePage}" style="text-decoration: none; color: inherit;">
                                                                                                        Product Name
                                                                                                        <i class="fa fa-long-arrow-${sortType == 'asc' ? 'up' : 'down'}" aria-hidden="true"></i>
                                                                                                    </a>
                                                                                                </th>
                                                                                                <th scope="col" class="font-weight-bold">Description</th>
                                                                                                <th scope="col" class="font-weight-bold">Status</th>
                                                                                            </tr>
                                                                                        </thead>
                                                                                        <tbody>
                                                                                            <c:forEach items="${requestScope.pList}" var="p">
                                                                                                <tr>
                                                                                                    <th scope="row">${p.productID}</th>
                                                                                                    <td>${p.productName}</td> 
                                                                                                    <td class="limit-detail" style="cursor: pointer" 
                                                                                                        data-toggle="tooltip" data-placement="top"
                                                                                                        title="${p.description}"
                                                                                                        data-template='<div class="tooltip" role="tooltip"><div class="arrow"></div><div class="tooltip-inner" style="max-width: 400px; white-space: pre-wrap;"></div></div>'>
                                                                                                        ${p.description}
                                                                                                    </td>   
                                                                                                    <td>
                                                                                                        <span class="${p.status ? 'text-success' : 'text-danger'}">
                                                                                                            ${p.status ? 'Đang bán' : 'Đã dừng bán'}
                                                                                                        </span>
                                                                                                    </td>
                                                                                                </tr>
                                                                                            </c:forEach>
                                                                                        </tbody>
                                                                                    </table>
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:if>
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
