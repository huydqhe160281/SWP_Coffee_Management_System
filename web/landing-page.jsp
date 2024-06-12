<%-- 
    Document   : landing-page
    Created on : May 28, 2024, 1:09:46 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./common/import.jsp"/>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet" />
        <!-- Required Fremwork -->
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap/css/bootstrap.min.css" />
        <!-- style css -->
        <link rel="stylesheet" type="text/css" href="assets/css/landing.css" />
        <style>
            /* Style for back to top button */
            #back-to-top {
                position: fixed;
                bottom: 20px;
                right: 20px;
                display: none;
                background-color: #007bff;
                color: white;
                width: 40px;
                height: 40px;
                border-radius: 50%;
                text-align: center;
                line-height: 40px;
                cursor: pointer;
                z-index: 99;
            }
            #back-to-top:hover {
                background-color: #0056b3;
            }
            .header_section.fixed-header {
                position: fixed;
                width: 100%;
                top: 0;
                left: 0;
                z-index: 1000;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }
        </style>
    </head>
    <body>
        <!--header section start -->
        <div class="header_section">
            <div class="container-fluid">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="logo"><a href="index.html"><img src="images/logo.png"></a></div>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav"aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="#home">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#about">About Us</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#hotproduct">Hot Products</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#services">Services</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#contact">Contact Us</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <!--header section end -->

        <!-- banner section start -->
        <div class="banner_section layout_padding">
            <div class="container">
                <div id="main_slider" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <c:if test="${not empty requestScope.discounts}">
                            <c:forEach items="${requestScope.discounts}" var="discount" varStatus="status">
                                <div class="carousel-item ${status.first ? 'active' : ''}">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="banner_taital">
                                                <h1 class="outstanding_text">Special Offer</h1>
                                                <h1 class="coffee_text">Discount: ${discount.value}%</h1>
                                                <p class="there_text">
                                                    This voucher to get a maximum discount of 
                                                    <fmt:formatNumber value="${discount.maxDiscount}" type="currency" pattern="###,### ₫" currencySymbol="₫" />
                                                    <br/>
                                                    Valid until <fmt:formatDate value="${discount.endDate}" pattern="dd/MM/yyyy" timeZone="Asia/Ho_Chi_Minh"/>.
                                                </p>
                                                <div class="learnmore_bt" onclick="copyToClipboard('${discount.code}')"><a href="#">Get Now</a></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <c:choose>
                        <c:when test="${not empty requestScope.discounts and fn:length(requestScope.discounts) > 1}">
                            <a class="carousel-control-prev" href="#main_slider" role="button" data-slide="prev">
                                <i class="fa fa-angle-left" aria-hidden="true"></i>
                            </a>
                            <a class="carousel-control-next" href="#main_slider" role="button" data-slide="next">
                                <i class="fa fa-angle-right" aria-hidden="true"></i>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <!-- Nothing to show -->
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
        <!-- banner section end -->

        <!-- about section start -->
        <div class="about_section layout_padding">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <div class="about_taital_main" id="about">
                            <div class="about_taital">About Us</div>
                            <p class="about_text">Full cleaning and housekeeping services for companies and households.</p>
                            <p class="about_text">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text.Lorem Ipsum is simply</p>
                            <div class="read_bt"><a href="#">Read More</a></div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="about_img"><img src="assets/images/about-img.png"/></div>
                    </div>
                </div>
            </div>
        </div>
        <!-- about section end -->

        <!-- Hot Products section start -->
        <div class="container">
            <h2 class="gallery_taital my-5" id="hotproduct">Hot Products</h2>
            <div class="row mb-5">
                <c:forEach items="${hotProducts}" var="product">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100">
                            <img src="${empty product.image ? '/assets/images/noimage.jpg' : product.image}" class="card-img-top w-75 mx-auto" alt="${product.productName}">
                            <div class="card-body">
                                <h5 class="card-title">${product.productName}</h5>
                                <p class="card-text text-truncate">${product.description}</p>
                            </div>
                            <div class="card-footer text-center">
                                <a href="#" class="btn btn-primary w-50">Details</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- Hot Products section end-->

        <!-- services section start -->
        <div class="services_section layout_padding">
            <div class="container" id="services">
                <div class="row">
                    <div class="col-sm-12">
                        <h1 class="services_taital">Services</h1>
                        <p class="services_text">Welcome to our cafe. Discover the services and products we offer!</p>
                    </div>
                </div>
                <div class="services_section_2">
                    <div class="row">
                        <div class="col-lg-4 col-sm-12 col-md-4">
                            <div class="box_main active">
                                <div class="house_icon">
                                    <img src="assets/images/icon1.png" class="image_1" alt="Icon Coffee"/>
                                    <img src="assets/images/icon1.png" class="image_2" alt="Icon Coffee"/>
                                </div>
                                <h3 class="decorate_text">High-Quality Coffee</h3>
                                <p class="tation_text">Providing pure coffee from famous producing regions.</p>
                                <div class="readmore_bt"><a href="#">Read More</a></div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-12 col-md-4">
                            <div class="box_main">
                                <div class="house_icon">
                                    <img src="assets/images/icon2.png" class="image_1" alt="Icon Drinks"/>
                                    <img src="assets/images/icon2.png" class="image_2" alt="Icon Drinks"/>
                                </div>
                                <h3 class="decorate_text">Diverse Beverages</h3>
                                <p class="tation_text">In addition to coffee, we offer a variety of delicious beverages.</p>
                                <div class="readmore_bt"><a href="#">Read More</a></div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-12 col-md-4">
                            <div class="box_main">
                                <div class="house_icon">
                                    <img src="assets/images/icon3.png" class="image_1" alt="Icon Ambient"/>
                                    <img src="assets/images/icon3.png" class="image_2" alt="Icon Ambient"/>
                                </div>
                                <h3 class="decorate_text">Relaxing Ambience</h3>
                                <p class="tation_text">Relax and enjoy our cozy and tranquil atmosphere.</p>
                                <div class="readmore_bt"><a href="#">Read More</a></div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- services section end -->


        <!-- testimonial section start -->
        <div class="client_section layout_padding">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <h1 class="client_taital">Testimonial</h1>
                        <p class="client_text">Eeven slightly believable. If you are going to use a passage of Lorem Ipsum, you need to</p>
                    </div>
                </div>
            </div>
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="client_section_2">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="testimonial_section_2">
                                            <h4 class="client_name_text">Monila <span class="quick_icon"><img src="images/quick-icon.png"></span></h4>
                                            <p class="customer_text">many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All themany variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some embarrassing hidden in the middle of text. All the</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="client_section_2">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="testimonial_section_2">
                                            <h4 class="client_name_text">Monila <span class="quick_icon"><img src="images/quick-icon.png"></span></h4>
                                            <p class="customer_text">many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All themany variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some embarrassing hidden in the middle of text. All the</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="client_section_2">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="testimonial_section_2">
                                            <h4 class="client_name_text">Monila <span class="quick_icon"><img src="images/quick-icon.png"></span></h4>
                                            <p class="customer_text">many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All themany variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some embarrassing hidden in the middle of text. All the</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- testimonial section end -->
        <!-- contact section start -->
        <div class="contact_section layout_padding">
            <div class="container">
                <h1 class="contact_text">Contact Us</h1>
            </div>
        </div>
        <div class="contact_section_2 layout_padding">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-6 padding_0">
                        <div class="mail_section">
                            <div class="email_text">
                                <div class="form-group">
                                    <input type="text" class="email-bt" placeholder="Name" name="Email">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="email-bt" placeholder="Email" name="Email">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="email-bt" placeholder="Phone Numbar" name="Email">
                                </div>
                                <div class="form-group">
                                    <textarea class="massage-bt" placeholder="Massage" rows="5" id="comment" name="Massage"></textarea>
                                </div>
                                <div class="send_btn">
                                    <div type="text" class="main_bt"><a href="#">SEND</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 padding_0">
                        <div class="map-responsive">
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.506341942525!2d105.52271427498033!3d21.0124166806328!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135abc60e7d3f19%3A0x2be9d7d0b5abcbf4!2sFPT%20University!5e0!3m2!1sen!2sus!4v1716880638880!5m2!1sen!2sus" 
                                    width="600"
                                    height="501"
                                    frameborder="0"
                                    style="border: 0; width: 100%"
                                    allowfullscreen="" 
                                    loading="lazy" 
                                    referrerpolicy="no-referrer-when-downgrade"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- contact section end -->
        <!-- footer section start -->
        <div class="footer_section layout_padding">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-sm-6">
                        <h3 class="useful_text">About</h3>
                        <p class="footer_text">consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation u</p>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <h3 class="useful_text">Menu</h3>
                        <div class="footer_menu">
                            <ul>
                                <li><a href="index.html">Home</a></li>
                                <li><a href="about.html">About Us</a></li>
                                <li><a href="gallery.html">Gallery</a></li>
                                <li><a href="services.html">Services</a></li>
                                <li><a href="contact.html">Contact Us</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <h1 class="useful_text">Useful Link</h1>
                        <p class="dummy_text">Adipiscing Elit, sed do Eiusmod Tempor incididunt </p>
                    </div>
                    <div class="col-lg-3 col-sm-6">
                        <h1 class="useful_text">Contact Us</h1>
                        <div class="location_text">
                            <ul>
                                <li>
                                    <a href="#">
                                        <i class="fa fa-map-marker" aria-hidden="true"></i><span class="padding_left_10">Address : Loram Ipusm</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fa fa-phone" aria-hidden="true"></i><span class="padding_left_10">Call : +01 1234567890</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fa fa-envelope" aria-hidden="true"></i><span class="padding_left_10">Email : demo@gmail.com</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- footer section end -->

        <!-- Back to Top Button start -->
        <div id="back-to-top" title="Back to top"><i class="fa fa-angle-up"></i></div>
        <!-- Back to Top Button end-->




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
        <!--<script type="text/javascript" src="assets/js/script.js"></script>-->  
        <script>
                                                    $(document).ready(function () {
                                                        $(window).scroll(function () {
                                                            if ($(this).scrollTop() > 1000) {
                                                                $('#back-to-top').fadeIn(200);
                                                            } else {
                                                                $('#back-to-top').fadeOut(200);
                                                            }
                                                        });

                                                        // Animate the scroll to top
                                                        $('#back-to-top').click(function (event) {
                                                            event.preventDefault();

                                                            $('html, body').animate({
                                                                scrollTop: 0
                                                            }, 300);
                                                        });
                                                    });
                                                    $(document).ready(function () {
                                                        var navbar = $('.header_section');

                                                        $(window).scroll(function () {
                                                            if ($(window).scrollTop() > 50) {
                                                                navbar.addClass('fixed-header');
                                                            } else {
                                                                navbar.removeClass('fixed-header');
                                                            }
                                                        });
                                                    });
                                                    function copyToClipboard(code) {
                                                        navigator.clipboard.writeText(code).then(function () {
                                                            alert('Voucher code ' + code + ' copied to clipboard!');
                                                        }, function (err) {
                                                            console.error('Could not copy text: ', err);
                                                        });
                                                    }

        </script>
    </body>
</html>
