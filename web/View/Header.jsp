<%-- 
    Document   : Header
    Created on : Jun 25, 2021, 9:49:12 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../public/css/Home.css" rel="stylesheet" type="text/css"/>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    </head>
    <body>
        <header>


            <!-- Navbar -->
            <nav class="navbar navbar-expand-lg navbar-light bg-ligh" id="navbar">
                <div class="container">


                    <a class="home-icon-link" href="Home" id="home">
                        <img  height="90" weight="100"  src="./public/images/web/MMstreo.jpg" >
                    </a>



                    <button class="navbar-toggler collapse" type="button" data-toggle="collapse" data-target="#navbarmain" aria-controls="navbarmain" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="icofont-navigation-menu"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarmain">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="Home">Home</a>
                            </li>
                            <li class="nav-item "><a class="nav-link" href="ShopAll">ShopAll</a></li>
                            <li class="nav-item "><a class="nav-link" href="Cart">Cart</a></li>
                            <c:if test="${sessionScope.accountsession!=null}">
                                <li class="nav-item "><a class="nav-link" href="UserOrder">${sessionScope.accountsession.getUserName()}</a></li>
                                <li class="nav-item "><a class="nav-link" href="Logout">Logout</a></li> 
                            </c:if>
                            <c:if test="${sessionScope.accountsession eq null}">
                                <li class="nav-item "><a class="nav-link" href="Login">Login</a></li> 
                            </c:if>
                        </ul>
                    </div>  
                </div>
            </nav>
        </header>
    </body>
</html>
