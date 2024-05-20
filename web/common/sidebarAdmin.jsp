<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--Navbar start-->          
<nav class="pcoded-navbar">
    <div class="sidebar_toggle">
        <a href="#"><i class="icon-close icons"></i></a>
    </div>
    <div class="pcoded-inner-navbar main-menu">
        <div class="">
            <div class="main-menu-header">
                <img class="img-80 img-radius" src="assets/images/avatar-4.jpg" alt="User-Profile-Image" />
                <div class="user-details">
                    <span id="more-details">John Doe<i class="fa fa-caret-down"></i></span>
                </div>
            </div>
            <div class="main-menu-content">
                <ul>
                    <li class="more-details">
                        <a href="user-profile.html"><i class="ti-user"></i>View Profile</a>
                        <a href="#!"><i class="ti-settings"></i>Settings</a>
                        <a href="auth-normal-sign-in.html"><i class="ti-layout-sidebar-left"></i>Logout</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="pcoded-navigation-label">Navigation</div>
        <ul class="pcoded-item pcoded-left-item">
            <li class="<c:if test="${currentPath == '/index.html'}">active</c:if>">
                    <a href="index.html" class="waves-effect waves-dark">
                        <span class="pcoded-micon"><i class="ti-home"></i><b>D</b></span>
                        <span class="pcoded-mtext">Dashboard</span>
                        <span class="pcoded-mcaret"></span>
                    </a>
            </li>
        </ul>
        <div class="pcoded-navigation-label">Management</div>
        <ul class="pcoded-item pcoded-left-item">
                <li class="<c:if test="${currentPath == '/category'}">active</c:if>">
                    <a href="/category" class="waves-effect waves-dark">
                        <span class="pcoded-micon"><i class="ti-receipt"></i><b>B</b></span>
                        <span class="pcoded-mtext">Category Management</span>
                        <span class="pcoded-mcaret"></span>
                    </a>
                </li>
                <li class="<c:if test="${currentPath == '/product'}">active</c:if>">
                <a href="another-path.html" class="waves-effect waves-dark">
                    <span class="pcoded-micon"><i class="ti-receipt"></i><b>B</b></span>
                    <span class="pcoded-mtext">Product Management</span>
                    <span class="pcoded-mcaret"></span>
                </a>
            </li>
        </ul>
    </div>
</nav>
<!--Navbar end-->          
