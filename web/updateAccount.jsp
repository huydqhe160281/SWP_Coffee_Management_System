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
    <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet" />
    <link rel="stylesheet" href="assets/pages/waves/css/waves.min.css" type="text/css" media="all" />
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="assets/pages/waves/css/waves.min.css" type="text/css" media="all" />
    <link rel="stylesheet" type="text/css" href="assets/icon/themify-icons/themify-icons.css" />
    <link rel="stylesheet" type="text/css" href="assets/icon/font-awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="assets/icon/icofont/css/icofont.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/style.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/jquery.mCustomScrollbar.css" />
    <style>
        /* Include styles from updateCategory.jsp */
        .limit-detail {
            max-width: 200px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .form-material {
            max-width: 500px;
            margin: auto;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            font-weight: 500;
            margin-bottom: 5px;
        }
        .form-control {
            height: calc(2.25rem + 2px);
            padding: .375rem .75rem;
            font-size: 1rem;
            line-height: 1.5;
            color: #495057;
            background-color: #fff;
            background-clip: padding-box;
            border: 1px solid #ced4da;
            border-radius: .25rem;
            transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
        }
        .form-control:focus {
            border-color: #007bff;
            outline: 0;
            box-shadow: 0 0 0 .2rem rgba(0,123,255,.25);
        }
        textarea.form-control {
            resize: vertical;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            color: #fff;
            padding: .375rem .75rem;
            font-size: 1rem;
            line-height: 1.5;
            border-radius: .25rem;
            cursor: pointer;
        }
        .btn-primary:hover {
            background-color: #0069d9;
            border-color: #0062cc;
        }
        .alert {
            padding: .75rem 1.25rem;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: .25rem;
        }
        .alert-danger {
            color: #721c24;
            background-color: #f8d7da;
            border-color: #f5c6cb;
        }
    </style>
</head>
<body>
    <jsp:include page="./common/reloading.jsp"/>
    <div id="pcoded" class="pcoded">
        <div class="pcoded-overlay-box"></div>
        <div class="pcoded-container navbar-wrapper">
            <jsp:include page="./common/headingAdmin.jsp"/>
            <div class="pcoded-main-container">
                <div class="pcoded-wrapper">
                    <jsp:include page="./common/sidebarAdmin.jsp"/>
                    <div class="pcoded-content">
                        <div class="page-header">
                            <div class="page-block">
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item">
                                        <a href="index.jsp"> <i class="fa fa-home"></i> </a>
                                    </li>
                                    <li class="breadcrumb-item">
                                        <a href="/account">Account Management</a>
                                    </li>
                                    <li class="breadcrumb-item">
                                        <a href="/updateAccount">Update Account</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="pcoded-inner-content">
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <div class="page-body">
                                        <div class="card">
                                            <div class="card-header">
                                                <div class="card-block w-75 m-auto">
                                                    <c:set value="${requestScope.account}" var="a"/>
                                                    <h3 class="text-center m-auto pb-5">Update Account</h3>
                                                    <% if (request.getAttribute("error") != null) { %>
                                                        <div class="alert alert-danger">
                                                            <%= request.getAttribute("error") %>
                                                        </div>
                                                    <% } %>
                                                    <form class="form-material" action="account_update" method="post">
                                                        <input type="hidden" name="accountID" value="${a.accountID}">
                                                        <div class="form-group form-default">
                                                            <label class="">Username</label>
                                                            <input type="text" name="username" class="form-control" value="${a.username}" readonly required>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <label class="">Password</label>
                                                            <input type="password" name="password" class="form-control" value="${a.password}" required>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <label class="">Name</label>
                                                            <input type="text" name="name" class="form-control" value="${a.name}" required>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <label class="">Phone</label>
                                                            <input type="text" name="phone" class="form-control" value="${a.phone}" required>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <label class="">Email</label>
                                                            <input type="text" name="email" class="form-control" value="${a.email}" required>
                                                        </div>
                                                        <div class="form-group form-default">
                                                            <label class="">Address</label>
                                                            <input type="text" name="address" class="form-control" value="${a.address}" required>
                                                        </div>
                                                        <%-- Check if the user is an admin --%>
                                                        <c:choose>
                                                            <c:when test="${sessionScope.account.username == 'admin'}">
                                                                <div class="form-group form-default">
                                                                    <label class="">Status</label>
                                                                    <select name="status" class="form-control" required>
                                                                        <option value="true" ${a.status ? 'selected' : ''}>Active</option>
                                                                        <option value="false" ${!a.status ? 'selected' : ''}>Inactive</option>
                                                                    </select>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="hidden" name="status" value="${a.status}">
                                                            </c:otherwise>
                                                        </c:choose>
                                                            <input type="hidden" name="roleID" class="form-control" value="${a.roleID}" required>
                                                            <input type="hidden" name="campusID" class="form-control" value="${a.campusID}" required>
                                                        <div class="form-group form-default text-center">
                                                            <button type="submit" class="btn btn-primary">UPDATE</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="assets/pages/waves/js/waves.min.js"></script>
    <script type="text/javascript" src="assets/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="assets/js/jquery-ui/jquery-ui.min.js "></script>
    <script type="text/javascript" src="assets/js/popper.js/popper.min.js"></script>
    <script type="text/javascript" src="assets/js/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="assets/pages/waves/js/waves.min.js"></script>
    <script type="text/javascript" src="assets/js/jquery-slimscroll/jquery.slimscroll.js "></script>
    <script type="text/javascript" src="assets/js/common-pages.js"></script>
    <script type="text/javascript" src="assets/js/pcoded.min.js"></script>
    <script type="text/javascript" src="assets/js/vertical/vertical-layout.min.js"></script>
    <script type="text/javascript" src="assets/js/script.js"></script>
</body>
</html>
