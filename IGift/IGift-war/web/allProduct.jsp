<%-- 
    Document   : allProductBody
    Created on : 25 oct. 2009, 14:17:20
    Author     : Root
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="home.css"/>
        <title>Home Page</title>
    </head>
    <body>
        <div id="global">
            <div id="header"> <%@include file="header.jsp" %>  </div>
            <div id="menu"> <%@include file="menu.jsp" %> </div>
            <div id="center">
                <c:forEach var="product" items="${requestScope[productList]}">
                    <div> ${product.name} </div>
            </c:forEach></div>
        </div>
    </body>
</html>
