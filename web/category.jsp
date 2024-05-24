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
                                                <h5 class="m-b-10">Category Management</h5>
                                                <p class="m-b-0">Quản lý danh mục của sản phẩm</p>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <ul class="breadcrumb">
                                                <li class="breadcrumb-item">
                                                    <a href="index.html"> <i class="fa fa-home"></i> </a>
                                                </li>
                                                <li class="breadcrumb-item">
                                                    <a href="/category">Category Management</a>
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
                                                        <div class="p-15 p-b-0 w-25">
                                                            <form class="form-material" action="search_category" method="get">
                                                                <div class="form-group form-primary">
                                                                    <input type="text" name="text_search" class="form-control" value="${requestScope.text_search}"/>
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label"><i class="fa fa-search m-r-10"></i>Search Friend</label>
                                                                </div>
                                                            </form>
                                                        </div>
                                                        <button class="btn btn-primary waves-effect h-15" onclick="window.location.href = '/category_create'">
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

                                                <div class="card-block table-border-style w-100">
                                                    <div class="table-responsive w-100">
                                                        <div class="container w-100">
                                                            <table class="table table-hover w-100">
                                                                <thead>
                                                                    <tr>
                                                                        <th scope="col" class="font-weight-bold">#</th>
                                                                        <th scope="col" class="font-weight-bold">
                                                                            <a href="?sortType=${sortType == 'asc' ? 'desc' : 'asc'}&indexPage=${indexPage}&sizePage=${sizePage}" style="text-decoration: none; color: inherit;">
                                                                                Category Name
                                                                                <i class="fa fa-long-arrow-${sortType == 'asc' ? 'up' : 'down'}" aria-hidden="true"></i>
                                                                            </a>
                                                                        </th>
                                                                        <th scope="col" class="font-weight-bold">Detail</th>
                                                                        <th scope="col" class="text-right font-weight-bold" >Action</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach items="${requestScope.cList}" var="c">
                                                                        <tr>
                                                                            <th scope="row">${c.categoryID}</th>
                                                                            <td>${c.categoryName}</td>
                                                                            <td class="limit-detail" style="cursor: pointer" data-toggle="tooltip" data-placement="top"
                                                                                title="${c.detail}"
                                                                                data-template='<div class="tooltip" role="tooltip"><div class="arrow"></div><div class="tooltip-inner" style="max-width: 400px; white-space: pre-wrap;"></div></div>'>
                                                                                ${c.detail}
                                                                            </td>                                                                            
                                                                            <td class="text-right pt-3">
                                                                                <button class="btn btn-primary btn-sm" onclick="window.location.href = '/category_detail?categoryID=${c.categoryID}'">View</button>
                                                                                <button class="btn btn-warning btn-sm" onclick="window.location.href = '/category_update?categoryID=${c.categoryID}'">Edit</button>
                                                                                <button class="btn btn-danger btn-sm" onclick="confirm('Không thể xóa danh mục sản phẩm vì còn liên quan đến nhiều sản phẩm khác!!!')">Delete</button>
                                                                            </td>
                                                                        </tr>
                                                                    </c:forEach>

                                                                </tbody>
                                                            </table>
                                                            <div class="d-flex justify-content-end">
                                                                <form id="sizeForm" method="get" action="category" class="mr-2">
                                                                    <div class="input-group mb-3">
                                                                        <div class="input-group-prepend">
                                                                            <label class="input-group-text" for="inputGroupSelect01">Size</label>
                                                                        </div>
                                                                        <select class="custom-select w-25" id="inputGroupSelect01" name="sizePage" onchange="submitSizeForm()">
                                                                            <option value="2" ${sizePage == 2 ? 'selected' : ''}>2</option>
                                                                            <option value="5" ${sizePage == 5 ? 'selected' : ''}>5</option>
                                                                            <option value="10" ${sizePage == 10 ? 'selected' : ''}>10</option>
                                                                            <option value="20" ${sizePage == 20 ? 'selected' : ''}>20</option>
                                                                        </select>
                                                                    </div>
                                                                    <input type="hidden" name="indexPage" value="${indexPage}" />
                                                                </form>
                                                                <nav aria-label="...">
                                                                    <ul class="pagination">
                                                                        <c:if test="${indexPage > 1}">
                                                                            <li class="page-item">
                                                                                <a class="page-link" href="category?indexPage=${indexPage - 1}&sizePage=${sizePage}" tabindex="-1">Previous</a>
                                                                            </li>
                                                                        </c:if>
                                                                        <c:forEach var="i" begin="1" end="${endPage}">
                                                                            <li class="page-item ${i == indexPage ? 'active' : ''}">
                                                                                <a class="page-link" href="category?indexPage=${i}&sizePage=${sizePage}">${i} <c:if test="${i == indexPage}"><span class="sr-only">(current)</span></c:if></a>
                                                                                </li>
                                                                        </c:forEach>
                                                                        <c:if test="${indexPage < endPage}">
                                                                            <li class="page-item">
                                                                                <a class="page-link" href="category?indexPage=${indexPage + 1}&sizePage=${sizePage}">Next</a>
                                                                            </li>
                                                                        </c:if>
                                                                    </ul>
                                                                </nav>
                                                            </div>


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
