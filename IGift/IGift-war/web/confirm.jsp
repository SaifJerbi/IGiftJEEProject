<%-- 
    Document   : confirm
    Created on : 13 nov. 2009, 18:14:53
    Author     : Root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="home.css"/>
        <title>Confirm Page</title>
    </head>
    <body>
        <div id="global">
            <div id="header"> <%@include file="header.jsp" %>  </div>
            <div id="menu"> <%@include file="menu.jsp" %> </div>
            <div id="center">
                <div class="confirm">
                    <h4>Reference de la commande: ${order.id} </h4><br>
                    <h4>Vous allez recevoir un e-mail de confirmation sur l'adresse e-mail: ${customer.email} </h4>
                </div>
            </div>
        </div>
    </body>
</html>
