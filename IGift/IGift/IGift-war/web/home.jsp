<%-- 
    Document   : home
    Created on : 21 oct. 2009, 22:34:45
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
        <script type="text/javascript" src="cart.js"></script>
        <title>Welcome Page</title>
    </head>
    <body>
        <div id="global">
            <div id="header"><%@include file="header.jsp" %></div>
            <div id="menu"> <%@include file="menu.jsp" %> </div>
            <div id="center">
                <div class="cart"> <a href="CartServlet"><img src="images/Shopping_Cart.png" alt="" title="Panier"></a></div>
                <div class="productList">
                    <c:forEach var="product" items="${productList}">
                        <c:set var="stockLabel" value="En Stock"/>
                        <c:set var="availabiltyCSS" value="available"/>
                        <c:if test="${not product.availability}">
                            <c:set var="availabiltyCSS" value="notAvailable"/>
                            <c:set var="stockLabel" value="Rupture de stock"/>
                        </c:if>
                        <div class="product" id="product">
                            <a href="ProductServlet?produitId=${product.id}"><img src="images/${product.image}" alt="">
                                <br> ${product.name}
                                <br> <span class="${availabiltyCSS}">${stockLabel}</span>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

    </body>
</html>
