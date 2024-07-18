<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Material Able bootstrap admin template by Codedthemes</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet">
        <link rel="stylesheet" href="assets/pages/waves/css/waves.min.css" type="text/css" media="all">
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/icon/themify-icons/themify-icons.css">
        <link rel="stylesheet" type="text/css" href="assets/icon/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="assets/icon/icofont/css/icofont.css">
        <link rel="stylesheet" type="text/css" href="assets/css/morris.js/css/morris.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <link rel="stylesheet" type="text/css" href="assets/css/jquery.mCustomScrollbar.css">
        <script src="assets/js/jquery/jquery.min.js"></script>
        <script src="assets/js/jquery-ui/jquery-ui.min.js"></script>
        <script src="assets/js/popper.js/popper.min.js"></script>
        <script src="assets/js/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/pages/waves/js/waves.min.js"></script>
        <script src="assets/js/jquery-slimscroll/jquery.slimscroll.js"></script>
        <script src="assets/js/raphael/raphael.min.js"></script>
        <script src="assets/js/morris.js/morris.js"></script>
        <script src="assets/js/pcoded.min.js"></script>
        <script src="assets/js/vertical/vertical-layout.min.js"></script>
        <script src="assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="assets/js/script.js"></script>
    </head>

    <body>
        <div class="pcoded-content">
            <div class="page-header">
                <div class="page-block">
                    <div class="row align-items-center">
                        <div class="col-md-8">
                            <div class="page-header-title">
                                <h5 class="m-b-10">Morris Chart</h5>
                                <p class="m-b-0">Lorem Ipsum is simply dummy text of the printing</p>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item">
                                    <a href="index.html"> <i class="fa fa-home"></i> </a>
                                </li>
                                <li class="breadcrumb-item"><a href="#!">Chart</a>
                                </li>
                                <li class="breadcrumb-item"><a href="#!">Morris Chart</a>
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
                            <div class="row">
                                <!-- Area Chart start -->
                                <div class="col-lg-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5>Doanh thu</h5>
                                            <span>Doanh thu theo ngày</span>
                                        </div>
                                        <div class="card-block">
                                            <div id="area-example"></div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Area Chart Ends -->
                                <!-- LINE CHART start -->
                                <div class="col-md-12 col-lg-6">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5>Sản phẩm bán được</h5>
                                            <span>Số lượng sản phẩm bán được theo ngày</span>
                                        </div>
                                        <div class="card-block">
                                            <div id="line-example"></div>
                                        </div>
                                    </div>
                                </div>
                                <!-- LINE CHART Ends -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $(document).ready(function () {
                // Revenue chart data
                var revenueData = [
            <c:forEach var="data" items="${revenueData}">
                    {y: '${data.orderDate}', a: ${data.revenue}},
            </c:forEach>
                ];

                console.log("Revenue Data: ", JSON.stringify(revenueData, null, 2)); // Debugging line

                // Sales chart data
                var salesData = [
            <c:forEach var="data" items="${salesData}">
                    {y: '${data.orderDate}', a: ${data.quantity}},
            </c:forEach>
                ];

                console.log("Sales Data: ", JSON.stringify(salesData, null, 2)); // Debugging line

                if (revenueData.length > 0) {
                    new Morris.Area({
                        element: 'area-example',
                        data: revenueData,
                        xkey: 'y',
                        ykeys: ['a'],
                        labels: ['Revenue']
                    });
                } else {
                    console.error("No revenue data available to display the chart.");
                }

                if (salesData.length > 0) {
                    new Morris.Line({
                        element: 'line-example',
                        data: salesData,
                        xkey: 'y',
                        ykeys: ['a'],
                        labels: ['Quantity']
                    });
                } else {
                    console.error("No sales data available to display the chart.");
                }
            });
        </script>
        <script type="text/javascript" src="assets/js/jquery/jquery.min.js "></script>
        <script type="text/javascript" src="assets/js/jquery-ui/jquery-ui.min.js "></script>
        <script type="text/javascript" src="assets/js/popper.js/popper.min.js"></script>
        <script type="text/javascript" src="assets/js/bootstrap/js/bootstrap.min.js "></script>
        <!-- waves js -->
        <script src="assets/pages/waves/js/waves.min.js"></script>
        <!-- jquery slimscroll js -->
        <script type="text/javascript" src="assets/js/jquery-slimscroll/jquery.slimscroll.js"></script>
        <!-- Morris Chart js -->
        <script src="assets/js/raphael/raphael.min.js"></script>
        <script src="assets/js/morris.js/morris.js"></script>
        <!-- Custom js -->
        <script src="assets/pages/chart/morris/morris-custom-chart.js"></script>
        <script src="assets/js/pcoded.min.js"></script>
        <script src="assets/js/vertical/vertical-layout.min.js"></script>
        <script src="assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script type="text/javascript" src="assets/js/script.js"></script>
    </body>

</html>
