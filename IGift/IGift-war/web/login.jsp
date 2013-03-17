<%--
    Document   : order
    Created on : 19 oct. 2009, 20:00:53
    Author     : Root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="home.css"/>
        <%@include file="baseInclude.jsp"%>
        <title>Login Page</title>
    </head>
    <body class="tundra">
        <div id="global">
            <div id="header"> <%@include file="header.jsp" %>  </div>
            <div id="menu"> <%@include file="menu.jsp" %> </div>
            <div id="center">
                <div class="cart"> <a href="CartServlet"><img src="images/Shopping_Cart.png" alt="" title="Panier"></a></div>
                <div class="login">
                    <h2>Votre Compte IGift:</h2>
                    <form action="LoginServlet" method="post">
                        <table border="0">
                            <tbody>
                                <tr>
                                    <td>Adresse e-mail</td>
                                    <td>
                                        <input id="emailId" type="text" name="MAIL_ADDRESS"
                                               dojoType="dijit.form.ValidationTextBox"
                                               required="true"
                                               promptMessage="Entrer votre email" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Mot de passe</td>
                                    <td>
                                        <input id="passwordId" type="password" name="PASSWORD"
                                               dojoType="dijit.form.ValidationTextBox"
                                               required="true"
                                               promptMessage="Entrer votre mot de passe" />
                                        <br>
                                        <div class="error"> ${errorMessage}
                                        ${passwordErrorMessage}</div>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input id="loginB" type="submit" value="Login"></td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                    <div class="createButton"><form action="createCustomer.jsp"><input id="createCustomerB" type="submit" value="Nouveau client" title="Créer un nouveau client"></form></div>
                </div>
            </div>
        </div>
    </body>
</html>
