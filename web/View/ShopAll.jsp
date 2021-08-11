
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="max-age=604800">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Shop All</title>

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

    </head>
    <body>
        <%@include file="/View/Header.jsp" %>


        <!-- ========================= SECTION INTRO END// ========================= -->

        <!-- ========================= SECTION CONTENT ========================= -->
        <form action="ShopAll" method="get" name="frGate">

            <section class="section-content padding-y">
                <div class="container">

                    <div class="row">
                        <aside class="col-md-3">

                            <div class="card">
                                <article class="filter-group">
                                    <header class="card-header">
                                        <a href="#" data-toggle="collapse" data-target="#collapse_1" aria-expanded="true" class="" >
                                            <i class="icon-control fa fa-chevron-down"></i>
                                            <h6 class="title">Product type</h6>
                                        </a>
                                    </header>
                                    <div class="filter-content collapse show" id="collapse_1" style="">
                                        <div class="card-body">
                                            <div class="input-group">
                                                <input name="search" type="text" class="form-control" placeholder="Search" value="${requestScope.searchname}">
                                                <div class="input-group-append">
                                                    <button class="btn btn-light" type="submit"><i class="fa fa-search"></i></button>
                                                </div>
                                            </div>

                                            <div class="list-menu">
                                                <c:forEach var="carte" items="${requestScope.Category}">
                                                    <c:if test="${carte.getCategoryID() eq requestScope.carte}">
                                                        <label class="custom-control custom-checkbox">
                                                            <input type="radio" name="carte" value="${carte.getCategoryID()}" class="custom-control-input" checked> 
                                                            <div class="custom-control-label">${carte.getCategoryName()}  </div>
                                                        </label>
                                                    </c:if>
                                                    <c:if test="${carte.getCategoryID() != requestScope.carte}">
                                                        <label class="custom-control custom-checkbox">
                                                            <input type="radio" name="carte" value="${carte.getCategoryID()}" class="custom-control-input"> 
                                                            <div class="custom-control-label">${carte.getCategoryName()}  </div>
                                                        </label>
                                                    </c:if>
                                                </c:forEach>
                                            </div>

                                        </div> <!-- card-body.// -->
                                    </div>
                                </article> <!-- filter-group  .// -->
                                <article class="filter-group">
                                    <header class="card-header">
                                        <a href="#" data-toggle="collapse" data-target="#collapse_2" aria-expanded="true" class="">
                                            <i class="icon-control fa fa-chevron-down"></i>
                                            <h6 class="title">Trademark</h6>
                                        </a>
                                    </header>
                                    <div class="filter-content collapse show" id="collapse_2" style="">
                                        <div class="card-body">
                                            <c:forEach var="trade" items="${requestScope.trademarks}">
                                                <c:if test="${trade.getManufacturerID() eq checkedid}">
                                                    <label class="custom-control custom-checkbox">
                                                        <input type="radio" name="trademark" value="${trade.getManufacturerID()}" class="custom-control-input" checked> 
                                                        <div class="custom-control-label">${trade.getManufacturerName()}  </div>
                                                    </label>
                                                </c:if>
                                                <c:if test="${trade.getManufacturerID() != checkedid}">
                                                    <label class="custom-control custom-checkbox">
                                                        <input type="radio" name="trademark" value="${trade.getManufacturerID()}" class="custom-control-input"> 
                                                        <div class="custom-control-label">${trade.getManufacturerName()}  </div>
                                                    </label>
                                                </c:if>
                                            </c:forEach>
                                        </div> <!-- card-body.// -->
                                    </div>
                                </article> <!-- filter-group .// -->
                                <article class="filter-group">
                                    <header class="card-header">
                                        <a href="#" data-toggle="collapse" data-target="#collapse_3" aria-expanded="true" class="">
                                            <i class="icon-control fa fa-chevron-down"></i>
                                            <h6 class="title">Price range </h6>
                                        </a>
                                    </header>
                                    <div class="filter-content collapse show" id="collapse_3" style="">
                                        <div class="card-body">
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label>Min</label>
                                                    <input name="beginp" class="form-control" placeholder="$0" type="number" min="0" value="${requestScope.beginprice}">
                                                </div>
                                                <div class="form-group text-right col-md-6">
                                                    <label>Max</label>
                                                    <input name="endp" class="form-control" placeholder="$1,0000" type="number" min="0" value="${requestScope.endprice}">
                                                </div>
                                            </div> <!-- form-row.// -->
                                            <button class="btn btn-block btn-light" type="submit">Apply</button>
                                        </div><!-- card-body.// -->
                                    </div>
                                </article> <!-- filter-group .// -->
                                <script type="text/javascript">
                                    function chGate()
                                    {
                                        frGate.submit();
                                    }
                                </script>

                            </div> <!-- card.// -->

                        </aside> <!-- col.// -->
                        <main class="col-md-9">

                            <header class="border-bottom mb-4 pb-3">
                                <div class="form-inline">
                                    <span class="mr-md-auto"><a href="Home" style="color: #000">Trang Chủ</a></span>
                                    <select class="mr-2 form-control" name="sort" onchange="chGate()">
                                        <option value="1" ${sortname}>A-Z</option>
                                        <option value="3" ${sortdesc}>Price: High-Low</option>
                                        <option value="2" ${sortasc}>Price: Low-High</option>
                                        <option value="0" ${sortnew}>Newest</option>
                                    </select>
                                </div>
                            </header><!-- sect-heading -->

                            <div class="row">
                                <c:if test="${lstzsize>-1}">
                                    <c:forEach  var="i" begin="0" end="${lstzsize}" >
                                        <div class="col-md-4">
                                            <figure class="card card-product-grid">
                                                <div class="img-wrap"> 
                                                    <span class="badge badge-danger"> NEW </span>
                                                    <a href="ProductDetail?productid=${requestScope.listP.get(i).getProductId()}">
                                                        <img src="${requestScope.proimgs.get(i).getImageUrl()}"  alt="" class="img-fluid"/>
                                                    </a>
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
                                </c:if>
                            </div> <!-- row end.// -->

                            <c:if test="${numberPage >1}">
                                <nav class="mt-4" aria-label="Page navigation sample">
                                    <ul class="pagination">

                                        <!-- fist page-->
                                        <c:if test="${pageCurrent ==1}">
                                            <li class="page-item"><p class="page-link">First</p></li>
                                            </c:if>
                                            <c:if test="${pageCurrent !=1}">
                                            <li class="page-item"><a class="page-link" href="ShopAll?carte=${requestScope.carte}&trademark=${requestScope.checkedid}&page=1&sort=${requestScope.sortabc}&search=${requestScope.searchname}">First</a></li>
                                            </c:if>

                                        <!--page-->
                                        <c:forEach var="page" begin="${beginPage}" end="${endPage}">
                                            <c:if test="${pageCurrent == page}">
                                                <li class="page-item active"><p class="page-link">${page}</p></li>
                                                </c:if>
                                                <c:if test="${pageCurrent != page}">
                                                <li class="page-item"><a class="page-link" href="ShopAll?carte=${requestScope.carte}&trademark=${requestScope.checkedid}&page=${page}&sort=${requestScope.sortabc}&search=${requestScope.searchname}">${page}</a></li>
                                                </c:if>
                                            </c:forEach>

                                        <!--last page-->        
                                        <c:if test="${pageCurrent ==numberPage}">
                                            <li class="page-item"><p class="page-link">Last</p></li>
                                            </c:if>
                                            <c:if test="${pageCurrent !=numberPage}">
                                            <li class="page-item"><a class="page-link" href="ShopAll?carte=${requestScope.carte}&trademark=${requestScope.checkedid}&page=${numberPage}&sort=${requestScope.sortabc}&search=${requestScope.searchname}">Last</a></li>
                                            </c:if>
                                    </ul>
                                </nav>
                            </c:if>


                        </main> <!-- col.// -->

                    </div>

                </div> <!-- container .//  -->
            </section>
        </form>

        <!-- ========================= SECTION CONTENT END// ========================= -->

        <!-- ========================= FOOTER ========================= -->
        <footer class="section-footer border-top padding-y">
            <div class="container">
                <p class="float-md-right"> 
                    &copy Copyright 2019 All rights reserved
                </p>
                <p>
                    <a href="#">Terms and conditions</a>
                </p>
            </div><!-- //container -->
        </footer>
        <!-- ========================= FOOTER END // ========================= -->
    </body>
</html>
