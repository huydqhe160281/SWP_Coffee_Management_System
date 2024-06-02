<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!--Navbar start-->
<nav class="pcoded-navbar">
    <div class="sidebar_toggle">
        <a href="#"><i class="icon-close icons"></i></a>
    </div>
    <div class="pcoded-inner-navbar main-menu">
        <div class="">
            <div class="main-menu-header">
                <img
                    class="img-80 img-radius"
                    src="assets/images/avatar-4.jpg"
                    alt="User-Profile-Image"
                    />
                <div class="user-details">
                    <span id="more-details"
                          >John Doe<i class="fa fa-caret-down"></i
                        ></span>
                </div>
            </div>
            <div class="main-menu-content">
                <ul>
                    <li class="more-details">
                        <a href="user-profile.html"><i class="ti-user"></i>View Profile</a>
                        <a href="#!"><i class="ti-settings"></i>Settings</a>
                        <a href="auth-normal-sign-in.html"
                           ><i class="ti-layout-sidebar-left"></i>Logout</a
                        >
                    </li>
                </ul>
            </div>
        </div>
        <div class="pcoded-navigation-label">Navigation</div>
        <ul class="pcoded-item pcoded-left-item">
            <li class="${fn:contains(currentPath, '/index') ? 'active' : ''}">
                <a href="index.html" class="waves-effect waves-dark">
                    <span class="pcoded-micon"><i class="ti-home"></i><b>D</b></span>
                    <span class="pcoded-mtext">Dashboard</span>
                    <span class="pcoded-mcaret"></span>
                </a>
            </li>
        </ul>
        <div class="pcoded-navigation-label">Management</div>
        <ul class="pcoded-item pcoded-left-item">
            <li class="${fn:contains(currentPath, '/category') ? 'active' : ''}">
                <a href="/category" class="waves-effect waves-dark">
                    <span class="pcoded-micon"><i class="ti-receipt"></i><b>B</b></span>
                    <span class="pcoded-mtext">Category Management</span>
                    <span class="pcoded-mcaret"></span>
                </a>
            </li>
            <li class="${fn:contains(currentPath, '/product') ? 'active' : ''}">
                <a href="/product" class="waves-effect waves-dark">
                    <span class="pcoded-micon"><i class="ti-receipt"></i><b>B</b></span>
                    <span class="pcoded-mtext">Product Management</span>
                    <span class="pcoded-mcaret"></span>
                </a>
            </li>
            <li class="${fn:contains(currentPath, '/discount') ? 'active' : ''}">
                <a href="/discount" class="waves-effect waves-dark">
                    <span class="pcoded-micon"><i class="ti-receipt"></i><b>B</b></span>
                    <span class="pcoded-mtext">Discount Management</span>
                    <span class="pcoded-mcaret"></span>
                </a>
            </li>
            <li class="${fn:contains(currentPath, '/ingredient') ? 'active' : ''}">
                <a href="/ingredient" class="waves-effect waves-dark">
                    <span class="pcoded-micon"><i class="ti-receipt"></i><b>B</b></span>
                    <span class="pcoded-mtext">Ingredient Management</span>
                    <span class="pcoded-mcaret"></span>
                </a>
            </li>
        </ul>
        <div class="pcoded-navigation-label">General</div>
        <ul class="pcoded-item pcoded-left-item">
            <li class="pcoded-hasmenu active pcoded-trigger">
                <a href="javascript:void(0)" class="waves-effect waves-dark">
                    <span class="pcoded-micon"
                          ><i class="ti-layout-grid2-alt"></i><b>BC</b></span
                    >
                    <span class="pcoded-mtext">Information Page</span>
                    <span class="pcoded-mcaret"></span>
                </a>
                <ul class="pcoded-submenu">
                    <li class="${currentPath == '/general' ? 'active' : ''}">
                        <a href="/general" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                            <span class="pcoded-mtext">View General</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                    </li>
                    <li class="${currentPath == '/general_update' ? 'active' : ''}">
                        <a href="/general_update" class="waves-effect waves-dark">
                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                            <span class="pcoded-mtext">Update General</span>
                            <span class="pcoded-mcaret"></span>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
<!--Navbar end-->
