<%-- 
    Document   : newjsp
    Created on : Jul 7, 2021, 5:17:48 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Admin editor</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            .topheader img {
                float: left;
                display: block;
                width: 60px;
                height: 60px;
                margin: 5px 5px;

            }

            .body-button form{
                display: inline-block;
            }
            
            #btn :hover {
                background-color: #3A3B3C;
                color: white;
                font-weight: bold
            }
            
            #btn {
                color: black;
                text-align: center;
                width: 150px;
                height: 50px;
                text-decoration: none;
                border-radius: 20px;
                margin-top: 20px;
                margin-left: 300px ;
            }
        </style>
    </head>

        <div id="adcontainner">
            <div class="topheader">
                <img src="./public/images/web/MMstreo.jpg" alt="logo">
                <p>Admin:${sessionScope.accountsession.getUserName()}</p>
            </div>

            <div class="body-button">
                <form action="addproduct" method="GET">
                    <input id="btn" type="submit" value="Add" />
                </form>
                <form action="AdminHome" method="GET">
                    <input id="btn" type="submit" value="Home" />
                </form>
                <form action="Logout" method="GET">
                    <input id="btn" type="submit" value="Logout" />
                </form>
            </div>
        </div>
        <hr>
</html>
