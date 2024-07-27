<%--
    Document   : updateProduct
    Created on : Jul 19, 2024, 11:50:50 AM
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
                                        <div class="col-md-4">
                                            <ul class="breadcrumb">
                                                <li class="breadcrumb-item">
                                                    <a href="index.jsp"> <i class="fa fa-home"></i> </a>
                                                </li>
                                                <li class="breadcrumb-item">
                                                    <a href="/product">Product Management</a>
                                                </li>
                                                <li class="breadcrumb-item">
                                                    <a href="/product_update?productID=${product.productID}">Update Product</a>
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
                                                    <div class="card-block w-75 m-auto">
                                                        <h3 class="text-center m-auto pb-5">Update Product</h3>
                                                        <% if (request.getAttribute("error") != null) { %>
                                                        <div class="alert alert-danger">
                                                            <%= request.getAttribute("error") %>
                                                        </div>
                                                        <% } %>
                                                        <form name="productForm" class="form-material" action="product_update" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                                                            <!-- Existing fields -->
                                                            <input type="hidden" name="productId" value="${product.productID}" />
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="productName" class="form-control" value="${product.productName}" required minlength="3" maxlength="50" />
                                                                <label class="float-label">Product Name</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <textarea name="description" class="form-control" required minlength="10" maxlength="500" style="height: 130px;">${product.description}</textarea>
                                                                <label class="float-label">Description</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <textarea name="recipe" class="form-control" required minlength="10" maxlength="1000" style="height: 130px;">${product.recipe}</textarea>
                                                                <label class="float-label">Recipe</label>
                                                            </div>
                                                            <!-- Dynamic Size and Price Fields -->
                                                            <div class="form-group form-default">
                                                                <c:choose>
                                                                    <c:when test="${not empty product.productSizes}">
                                                                        <c:forEach var="productSize" items="${product.productSizes}">
                                                                            <div class="form-group form-default form-static-label my-3">
                                                                                <input type="hidden" name="sizeId" value="${productSize.size.sizeID}">
                                                                                <input type="number" name="price_${productSize.size.sizeID}" class="form-control" placeholder="Price for size ${productSize.size.type}" value="${productSize.price}" required>
                                                                                <label class="float-label">Price of size ${productSize.size.type}</label>
                                                                            </div>
                                                                        </c:forEach>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <c:forEach var="size" items="${sList}">
                                                                            <div class="form-group form-default form-static-label my-3">
                                                                                <input type="hidden" name="sizeId_${size.sizeID}" value="${size.sizeID}">
                                                                                <input type="number" name="price_${size.sizeID}" class="form-control" placeholder="Price for size ${size.type}" required>
                                                                                <label class="float-label">Price of size ${size.type}</label>
                                                                            </div>
                                                                        </c:forEach>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                            <div class="d-flex align-items-center">
                                                                <div class="form-group form-default form-static-label w-50 mr-5">
                                                                    <div class="image-preview">
                                                                        <img src="${product.image}" alt="Product Image" style="max-width: 200px; max-height: 200px;" id="image-preview" onclick="handleImageClick('image')">
                                                                        <div class="custom-file mb-2" id="imageInputWrapper">
                                                                            <input type="file" class="custom-file-input" id="image" name="image" onchange="previewImage('image', 'image-preview')" accept="image/*">
                                                                            <label class="custom-file-label" for="image">Choose file</label>
                                                                        </div>
                                                                    </div>
                                                                    <div id="image-error" class="text-danger"></div>
                                                                </div>
                                                                <div class="d-flex flex-row w-50 ml-5 justify-content-around">
                                                                    <div class="form-group form-default form-static-label w-75 mr-5">
                                                                        <select name="category" class="form-control" required>
                                                                            <c:forEach var="category" items="${cList}">
                                                                                <option value="${category.categoryID}" ${category.categoryID == product.category.categoryID ? 'selected' : ''}>${category.categoryName}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                        <label class="float-label">Category</label>
                                                                    </div>
                                                                    <div class="checkbox-fade fade-in-primary mr-5">
                                                                        <label>
                                                                            <input name="status" type="checkbox" value="true" ${product.status ? 'checked' : ''}>
                                                                            <span class="cr"><i class="cr-icon icofont icofont-ui-check txt-primary"></i></span>
                                                                            <span class="text-inverse">Status</span>
                                                                        </label>
                                                                    </div>
                                                                    <div class="checkbox-fade fade-in-primary">
                                                                        <label>
                                                                            <input name="isHot" type="checkbox" value="true" ${product.isHot ? 'checked' : ''}>
                                                                            <span class="cr"><i class="cr-icon icofont icofont-ui-check txt-primary"></i></span>
                                                                            <span class="text-inverse">IsHot</span>
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <div class="form-group form-default">
                                                                <button class="btn btn-primary w-100" type="submit">Update Product</button>
                                                            </div>
                                                        </form>

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

        <script>
            function previewImage(inputId, previewId) {
                var input = document.getElementById(inputId);
                var preview = document.getElementById(previewId);
                var file = input.files[0];
                var reader = new FileReader();

                reader.onloadend = function () {
                    preview.src = reader.result;
                };

                if (file) {
                    reader.readAsDataURL(file);
                } else {
                    preview.src = "";
                }

                // Update the file name in the label
                var fileName = file ? file.name : 'Choose file';
                var label = input.nextElementSibling;
                label.innerHTML = fileName;

                // Show the input wrapper if file is selected
                var inputWrapper = document.getElementById(inputId + 'Wrapper');
                inputWrapper.style.display = file ? 'block' : 'none';
            }

            // Handle click on image to trigger file input click
            function handleImageClick(inputId) {
                var input = document.getElementById(inputId);
                input.click();
            }

            function validateForm() {
                // You can implement validation logic here if needed
                return true;
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
