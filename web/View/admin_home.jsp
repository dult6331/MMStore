<%-- 
    Document   : admin_home
    Created on : Jul 18, 2021, 2:21:08 PM
    Author     : Hfyl
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/View/admin_header.jsp" %>
        <div style="margin: 0 auto; width: 80%;">
            <form method="GET" action="AdminHome" style="margin: 0 auto; width: 80%;">
                <input type="text" name="search" value="${requestScope.name}" />
                <input type="submit" value="Search" />
            </form>
            <table border="1" style="margin: 0 auto; width: 80%;">
                <thead>
                    <tr>
                        <th>ProductID</th>
                        <th>ProductName</th>
                        <th>UnitsPrice</th>
                        <th>CategoryID</th>
                        <th>UnitsInStock</th>
                        <th>ManufacturerID</th>
                        <th>IsContinue</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${requestScope.prolst}">
                        <tr>
                            <td>${product.getProductId()}</td>
                            <td>${product.getProductName()}</td>
                            <td>${product.formatPrice()}</td>
                            <td>${product.getCategoryId()}</td>
                            <td>${product.getUnitsInStock()}</td>
                            <td>${product.getManufacturerID()}</td>
                            <td>${product.isIsContinues()}</td>
                            <td><a href="AdminEdit?pid=${product.getProductId()}">Edit</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
