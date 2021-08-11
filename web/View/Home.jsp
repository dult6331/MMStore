<%-- 
    Document   : Home
    Created on : Jun 25, 2021, 8:57:49 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="./public/css/Home.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <%@include file="/View/Header.jsp" %>


        <!-- Navbar -->
        <div class="wrap-all">
            <!--Carousel Wrapper-->
            <div id="carousel-example-1z" class="carousel slide carousel-fade pt-4" data-ride="carousel">

                <!--Indicators-->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-1z" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-1z" data-slide-to="1"></li>
                    <li data-target="#carousel-example-1z" data-slide-to="2"></li>
                </ol>
                <!--/.Indicators-->

                <!--Slides-->
                <div class="carousel-inner " role="listbox" >

                    <!--First slide-->
                    <div class="carousel-item active" id="firstimage">
                        <div class="view">

                            <!-- Mask & flexbox options-->
                            <div class="mask rgba-black-strong d-flex justify-content-center align-items-center">

                                <!-- Content -->
                                <div class="text-center white-text mx-5 wow fadeIn">

                                </div>
                                <!-- Content -->

                            </div>
                            <!-- Mask & flexbox options-->

                        </div>
                    </div>
                    <!--/First slide-->

                    <!--Second slide-->
                    <div class="carousel-item" id="secondimage">
                        <div class="view" >

                            <!-- Mask & flexbox options-->
                            <div class="mask rgba-black-strong d-flex justify-content-center align-items-center">

                                <!-- Content -->
                                <div class="text-center white-text mx-5 wow fadeIn">

                                </div>
                                <!-- Content -->

                            </div>
                            <!-- Mask & flexbox options-->

                        </div>
                    </div>
                    <!--/Second slide-->

                    <!--Third slide-->
                    <div class="carousel-item" id="thirdimage">
                        <div class="view" >

                            <!-- Mask & flexbox options-->
                            <div class="mask rgba-black-strong d-flex justify-content-center align-items-center">

                                <!-- Content -->
                                <div class="text-center white-text mx-5 wow fadeIn">

                                </div>
                                <!-- Content -->

                            </div>
                            <!-- Mask & flexbox options-->

                        </div>
                    </div>
                    <!--/Third slide-->

                </div>
                <!--/.Slides-->

                <!--Controls-->
                <a class="carousel-control-prev" href="#carousel-example-1z" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carousel-example-1z" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
                <!--/.Controls-->

            </div>
            <!--/.Carousel Wrapper-->


            <!--/CATEGORIES-->
            <hr class="my-5" />

            <div class="product">



                <!--/.Navbar-->
                <!--Section: Products v.3-->
                <section class="text-center mb-4">

                    <!--Grid row-->

                    <div class="row">
                        <c:forEach  var="i" begin="0" end="8" >
                            <div class="col-md-4">
                                <figure class="card card-product-grid">
                                    <div class="img-wrap"> 
                                        <span class="badge badge-danger"> NEW </span>
                                        <a href="ProductDetail?productid=${requestScope.listP.get(i).getProductId()}"> <img src="${requestScope.proimgs.get(i).getImageUrl()}"  alt="" class="img-fluid"/> </a>
                                        <div class="col-lg-4 col-md-12 mb-4">
                                        </div>

                                    </div> <!-- img-wrap.// -->
                                    <figcaption class="info-wrap">
                                        <div class="fix-height">
                                            <div id="productnamefix">
                                                <a href="ProductDetail?productid=${requestScope.listP.get(i).getProductId()}" class="title">${requestScope.listP.get(i).getProductName()}</a>
                                            </div>

                                            <div class="price-wrap mt-2">
                                                <span class="price">${requestScope.listP.get(i).formatPrice()}₫</span>

                                            </div> <!-- price-wrap.// -->
                                        </div>
                                    </figcaption>
                                </figure>
                            </div> <!-- col.// -->

                        </c:forEach>
                    </div> <!-- row end.// -->
                    <!--Grid row-->

                </section>


                <!--/CATEGORIES-->


                <!--Section: Products v.3-->



            </div>
        </div>
        <!--Main layout-->
        <%@include file="/View/Footer.jsp" %>
    </body>
</html>
