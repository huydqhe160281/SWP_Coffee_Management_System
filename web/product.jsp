<%--
    Document   : product
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
    </head>
    <style>
        .limit-detail {
            max-width: 200px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .icon-spacing {
            padding-right: 5px;
            font-size: 1.6em; /* Tăng kích thước của icon, bạn có thể thay đổi giá trị này để phù hợp */
            cursor: pointer;
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
                                                <h5 class="m-b-10">Product Management</h5>
                                                <p class="m-b-0">Quản lý sản phẩm</p>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <ul class="breadcrumb">
                                                <li class="breadcrumb-item">
                                                    <a href="index.jsp"> <i class="fa fa-home"></i> </a>
                                                </li>
                                                <li class="breadcrumb-item">
                                                    <a href="/product">Product Management</a>
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
                                                        <div class="p-15 w-50 d-flex align-items-center">
                                                            <form class="form-material mr-2" action="search_product" method="get">
                                                                <div class="form-group form-primary">
                                                                    <input type="text" name="text_search" class="form-control" value="${requestScope.text_search}"/>
                                                                    <span class="form-bar"></span>
                                                                    <label class="float-label"><i class="fa fa-search m-r-10"></i>Search Field</label>
                                                                </div>
                                                            </form>
                                                            <form id="productForm" method="get" action="product" class="mr-2">
                                                                <select class="custom-select " id="inputGroupSelect01" name="categoryID" onchange="submitCategoryForm()">
                                                                    <option value="" ${empty category.categoryID ? 'selected' : ''}>All Category</option>
                                                                    <c:forEach items="${requestScope.cList}" var="c">
                                                                        <option value="${c.categoryID}" ${categoryID == c.categoryID ? 'selected' : ''}>
                                                                        <a href="/category_detail?categoryID=${c.categoryID}">${c.categoryName}</a>
                                                                        </option>
                                                                    </c:forEach>
                                                                </select>
                                                            </form>
                                                        </div>
                                                        <button class="btn btn-primary waves-effect h-15" onclick="window.location.href = '/product_create'">
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
                                                                        <th scope="col" class="font-weight-bold">
                                                                            <a href="?sortType=${sortType == 'asc' ? 'desc' : 'asc'}&indexPage=${indexPage}&sizePage=${sizePage}" style="text-decoration: none; color: inherit;">
                                                                                #<i class="fa fa-long-arrow-${sortType == 'asc' ? 'up' : 'down'}" aria-hidden="true"></i>
                                                                            </a>
                                                                        </th>
                                                                        <th>Product Name</th>
                                                                        <th>Description</th>
                                                                        <th>Recipe</th>
                                                                        <th class="text-center">Status</th>
                                                                        <th class="text-center">IsHot</th>
                                                                        <th class="text-right">Action</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach items="${requestScope.pList}" var="p">
                                                                        <tr>
                                                                            <th scope="row">${p.productID}</th>
                                                                            <td>${p.productName}</td>
                                                                            <td class="limit-detail" style="cursor: pointer" data-toggle="tooltip" data-placement="top"
                                                                                title="${p.description}"
                                                                                data-template='<div class="tooltip" role="tooltip"><div class="arrow"></div><div class="tooltip-inner" style="max-width: 400px; white-space: pre-wrap;"></div></div>'>
                                                                                ${p.description}
                                                                            </td>
                                                                            <td class="limit-detail" style="cursor: pointer" data-toggle="tooltip" data-placement="top"
                                                                                title="${p.recipe}"
                                                                                data-template='<div class="tooltip" role="tooltip"><div class="arrow"></div><div class="tooltip-inner" style="max-width: 400px; white-space: pre-wrap;"></div></div>'>
                                                                                ${p.recipe}
                                                                            </td>
                                                                            <td class="text-center">
                                                                                <span class="${p.status ? 'badge badge-pill badge-success' : 'badge badge-pill badge-danger'}" style="cursor: pointer"
                                                                                      onclick="toggleStatus(${p.productID}, 'status', ${p.status ? 'false' : 'true'})">
                                                                                    ${p.status ? 'On sale' : 'Stopped selling'}
                                                                                </span>
                                                                            </td>
                                                                            <td class="text-center">
                                                                                <span class="${p.isHot ? 'badge badge-pill badge-success' : 'badge badge-pill badge-danger'}" style="cursor: pointer"
                                                                                      onclick="toggleStatus(${p.productID}, 'isHot', ${p.isHot ? 'false' : 'true'})">
                                                                                    ${p.isHot ? 'Active' : 'Deactive'}
                                                                                </span>
                                                                            </td>
                                                                            <td class="text-right pt-3">
                                                                                <i class="fa fa-eye icon-spacing" aria-hidden="true" data-toggle="tooltip" data-placement="left" title="View" 
                                                                                   onclick="window.location.href = '/product_detail?productID=${p.productID}'"></i>
                                                                                <i class="fa fa-pencil-square-o icon-spacing" aria-hidden="true" 
                                                                                   data-toggle="tooltip" data-placement=left title="Edit"
                                                                                   onclick="window.location.href = '/product_update?productID=${p.productID}'"></i>
                                                                            </td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>

                                                            <div class="d-flex justify-content-end">
                                                                <form id="sizeForm" method="get" action="product" class="mr-2">
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
                                                                                <a class="page-link" href="?indexPage=${indexPage - 1}&sizePage=${sizePage}&categoryID=${categoryID}" tabindex="-1">Previous</a>
                                                                            </li>
                                                                        </c:if>
                                                                        <c:forEach var="i" begin="1" end="${endPage}">
                                                                            <li class="page-item ${i == indexPage ? 'active' : ''}">
                                                                                <a class="page-link" href="?indexPage=${i}&sizePage=${sizePage}&categoryID=${categoryID}">${i} <c:if test="${i == indexPage}"><span class="sr-only">(current)</span></c:if></a>
                                                                                </li>
                                                                        </c:forEach>
                                                                        <c:if test="${indexPage < endPage}">
                                                                            <li class="page-item">
                                                                                <a class="page-link" href="?indexPage=${indexPage + 1}&sizePage=${sizePage}&categoryID=${categoryID}">Next</a>
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

                                <!-- Modal -->
                                <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Xác nhận cập nhật trạng thái</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                Bạn có chắc chắn muốn cập nhật trạng thái sản phẩm này không?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                                <button type="button" class="btn btn-primary" id="confirmBtn">Xác nhận</button>
                                            </div>
                                        </div>
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
            function submitCategoryForm() {
                document.getElementById('productForm').submit();
            }
            function toggleStatus(productId, field, newValue) {
                const xhr = new XMLHttpRequest();
                xhr.open("POST", "/product_status", true); 
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        location.reload();
                    }
                };

                xhr.send("productId=" + productId + "&field=" + field + "&newValue=" + newValue);
            }

            document.addEventListener('DOMContentLoaded', function () {
                var confirmModal = document.getElementById('confirmModal');
                var confirmBtn = document.getElementById('confirmBtn');
                var productId;

                confirmModal.addEventListener('show.bs.modal', function (event) {
                    var button = event.relatedTarget;
                    productId = button.getAttribute('data-product-id');
                });

                confirmBtn.addEventListener('click', function () {
                    var form = document.createElement('form');
                    form.method = 'POST';
                    form.action = 'product';

                    var input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = 'productID';
                    input.value = productId;

                    form.appendChild(input);
                    document.body.appendChild(form);
                    form.submit();
                });
            });
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

