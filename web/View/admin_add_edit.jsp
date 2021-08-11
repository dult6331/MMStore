<%-- 
    Document   : newjsp1
    Created on : Jul 7, 2021, 7:18:09 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="/View/admin_header.jsp" %>
    <title>Admin editor</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
    <br><br><br>
    <div class="body-button">
        <h1>Edit Product</h1>
        <form action="AdminEdit" method="post" >
            <table >
                <tbody>
                    <tr>
                        <td>Product ID :</td>
                        <td><input type="text" id="ProductID" name="ProductID" value="${product.getProductId()}" readonly="true" ></td>
                    </tr>
                    <tr>
                        <td>Product Name:</td>
                        <td><input type="text" id="ProductName" name="ProductName"  value="${product.getProductName()}" ></td>
                    </tr>
                    <tr>
                        <td>Units Price:</td>
                        <td><input type="text" id="UnitsPrice" name="UnitsPrice" value="${product.getUnitsPrice()}" ></td>
                    </tr>
                    <tr>
                        <td>Category:</td>
                        <td><input type="text" id="Category" name="Category" value="${product.getCategoryId()}" ></td>
                    </tr>
                    <tr>
                        <td>Units in Stock:</td>
                        <td>
                            <input type="text" id="UnitslnStock" name="UnitslnStock" value="${product.getUnitsInStock()}" >
                        </td>
                    </tr>
                    <tr>
                        <td>Description :</td>
                        <td>
                            <textarea  name="Description" rows="3"   >${product.getDescription()}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>manufacturer ID :</td>
                        <td>
                            <select name="Manufacturer">
                            <c:forEach items="${manufacturers}" var="manufacturer">
                                    <option value="${manufacturer.getManufacturerID()}" >${manufacturer.getManufacturerName()}</option>
                            </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Is Continue :</td>
                        <td>
                            <input type="radio"  name="IsContinue" value="Yes" checked>Yes
                            <input type="radio"  name="IsContinue" value="No">No
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <td>Star :</td>
                        <td><input type="text" id="Star" name="Star" value="${product.getStars()}" ></td>
                    </tr>
                </tbody>
            </table>

           
            <input style="color: #74BA43" type="submit" value="Submit" name="submit">
            <input  style="color: #FE0000" type="submit" value="Cancel" name="cancel">
            ${messUpdate}
        </form>
    </div>

</body>

</html>