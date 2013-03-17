<%-- 
    Document   : menu
    Created on : 24 oct. 2009, 18:53:52
    Author     : Root
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="option">
            <ul>
                <li><a href="ProductListServlet?categoryId=-1">Tous les produits</a></li>
                <c:forEach var="category" items="${categoryList}">
                    <li><a href="ProductListServlet?categoryId=${category.id}">${category.name}</a></li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>
