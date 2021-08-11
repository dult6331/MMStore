<%-- 
    Document   : userorder
    Created on : Jul 18, 2021, 11:05:02 PM
    Author     : Hfyl
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="max-age=604800">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Order Detail</title>

        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon">

        <!-- jQuery -->
        <script src="js/jquery-2.0.0.min.js" type="text/javascript"></script>

        <!-- Bootstrap4 files-->
        <script src="js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <link href="../public/css/boostrap.css" rel="stylesheet" type="text/css"/>

        <!-- Font awesome 5 -->
        <link href="fonts/fontawesome/css/all.min.css" type="text/css" rel="stylesheet">

        <!-- plugin: fancybox  -->
        <script src="plugins/fancybox/fancybox.min.js" type="text/javascript"></script>
        <link href="plugins/fancybox/fancybox.min.css" type="text/css" rel="stylesheet">

        <!-- custom style -->
        <link href="./public/css/ui.css" rel="stylesheet" type="text/css"/>
        <link href="./public/css/responsive.css" rel="stylesheet" type="text/css" media="only screen and (max-width: 1200px)"/>
        <link href="css/Shop.css" rel="stylesheet" type="text/css">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

        <!-- custom javascript -->
        <script src="js/script.js" type="text/javascript"></script>

        <script type="text/javascript">
            /// some script

            // jquery ready start
            $(document).ready(function () {
                // jQuery code

            });
            // jquery end
        </script>

        <style>
            #myDIV {
                width: 80%;
                padding: 50px 0;
                margin: 0 auto;
                margin-top: 20px;
            }
            #orders{
                background-color: red;
            }
            #table_orderdetail{
                width: 80%;
            }
        </style>
    </head>
    <body>
        <%@include file="/View/Header.jsp" %>

        <div id="myDIV">
            <c:forEach var="i" begin="0" end="${requestScope.endods}">
                <article class="filter-group">
                    <header class="card-header">
                        <a href="#" data-toggle="collapse" data-target="#collapse_${i}" aria-expanded="true" class="">
                            <i class="icon-control fa fa-chevron-down"></i>
                            <h6 class="title"><strong>OrderID:${requestScope.orders.get(i).getOrderId()} </strong> &emsp;
                                <strong>Order date:${requestScope.orders.get(i).getOrderDate()}</strong></h6>
                        </a>
                    </header>
                    <div class="filter-content collapse hide" id="collapse_${i}">
                        <div class="card-body">
                            <table border="1" id="table_orderdetail">
                                <thead>
                                    <tr>
                                        <th>Product Name</th>
                                        <th>UnitsPrice</th>
                                        <th>Quantity</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="product" items="${requestScope.orders.get(i).getLst()}">
                                        <tr>
                                            <td><a href="ProductDetail?productid=${product.getProductID()}">${product.getProductName()}</a></td>
                                            <td>${product.formatPrice()}</td>
                                            <td>${product.getQuantity()}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div> <!-- card-body.// -->
                    </div>
                </article> <!-- filter-group .// -->
            </c:forEach>
        </div>
    </body>
</html>