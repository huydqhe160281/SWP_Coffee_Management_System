<%@ page import="model.Account" %>
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
    </head>
    <style>
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
            transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
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

        /* Alert style */
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
                                    <div class="row align-items-center">
                                        <div class="col-md-4">
                                            <ul class="breadcrumb">
                                                <li class="breadcrumb-item">
                                                    <a href="index.html"> <i class="fa fa-home"></i> </a>
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
                                </div>
                            </div>

                            <div class="pcoded-inner-content">
                                <div class="main-body">
                                    <div class="page-wrapper">
                                        <div class="page-body">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h3 class="text-center">Update Account</h3>
                                                    <% if (request.getAttribute("error") != null) { %>
                                                    <div style="color: red;"><%= request.getAttribute("error").toString() %></div>
                                                    <% } %>

                                                    <%
                                                        Account account = (Account) request.getAttribute("account");
                                                        if (account != null) {
                                                    %>
                                                    <form action="account_update" method="post">
                                                        <input type="hidden" name="accountID" value="<%= account.getAccountID() %>">
                                                        Username: <input type="text" name="username" value="<%= account.getUsername() %>" readonly required><br>
                                                        Password: <input type="password" name="password" value="<%= account.getPassword() %>" required><br>
                                                        Name: <input type="text" name="name" value="<%= account.getName() %>" required><br>
                                                        Phone: <input type="tel" name="phone" value="<%= account.getPhone() %>" required><br>
                                                        Email: <input type="email" name="email" value="<%= account.getEmail() %>" required><br>
                                                        Address: <input type="text" name="address" value="<%= account.getAddress() %>" required><br>
                                                        Status: 
                                                        <select name="status" required>
                                                            <option value="true" <%= account.isStatus() ? "selected" : "" %>>Active</option>
                                                            <option value="false" <%= !account.isStatus() ? "selected" : "" %>>Inactive</option>
                                                        </select><br>
                                                        <input type="submit" value="Update">
                                                    </form>
                                                    <% } else { %>
                                                    <div style="color: red;">Account not found.</div>
                                                    <% } %>
                                                    <a href="account" class="btn btn-secondary mt-3" style="float: right;">Back</a>
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

        <script type="text/javascript" src="assets/js/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="assets/js/jquery-ui/jquery-ui.min.js"></script>
        <script type="text/javascript" src="assets/js/popper.js/popper.min.js"></script>
        <script type="text/javascript" src="assets/js/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/pages/waves/js/waves.min.js"></script>
        <script type="text/javascript" src="assets/js/jquery-slimscroll/jquery.slimscroll.js"></script>
        <script src="assets/js/pcoded.min.js"></script>
        <script src="assets/js/vertical/vertical-layout.min.js"></script>
        <script src="assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script type="text/javascript" src="assets/js/script.js"></script>
    </body>
</html>
