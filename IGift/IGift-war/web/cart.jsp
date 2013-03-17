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
        <script type="text/javascript" src="js/validation.js"></script>
        <script type="text/javascript" src="js/cart.js"></script>
        <script type="text/javascript" >
            dojo.addOnLoad(init);
        </script>
        <title>Cart Page</title>
    </head>
    <body class="tundra">
        <div id="global">
            <div id="header"> <%@include file="header.jsp" %>  </div>
            <div id="menu"> <%@include file="menu.jsp" %> </div>
            <div id="center">
                <div class="cartDiv">
                    <h2>Votre panier </h2>
                    <form action="" id="CartForm">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Produit</th>
                                    <th>Prix<br> Euro</th>
                                    <th>Quantité</th>
                                    <th>Total<br> Euro</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="lineItem" items="${lineItemList}">
                                    <tr>
                                        <td><img src="images/${lineItem.product.image}" alt=""><br>
                                        ${lineItem.product.name}</td>
                                        <td>${lineItem.product.price}</td>
                                        <td id="lineItem_${lineItem.id}">
                                            <input id="${lineItem.id}" name="${lineItem.id}" type="text"
                                                   dojoType="dijit.form.ValidationTextBox"
                                                   value="${lineItem.quantity}"
                                                   constraints="{min:1,max:${lineItem.product.quantity},places:0}"
                                                   promptMessage= "Entrer une quantité entre 1 et ${lineItem.product.quantity}"
                                                   required= "true"
                                                   invalidMessage= "Valeur Invalide."
                                                   />
                                            <br><h4>
                                                Quantité restante: ${lineItem.product.quantity}
                                                <input type="hidden" value=${lineItem.product.quantity}>
                                            </h4> <br>
                                            <div class="error"> ${typeError} <br> ${quantityError} <br> ${remainQuantityError}</div>
                                        </td>
                                        <td>${lineItem.subTotal}</td>
                                        <td>
                                            <input id="${lineItem.id}" name="DELETE" type="image" src="images/Shopping_Cart_Remove.png" title="Supprimer l'article du panier">
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tbody>
                                <tr>
                                    <td>Total<br> Euro</td>
                                    <td>${totalItemsPrice}</td>
                                </tr>
                                <tr>
                                    <td><input type="button" id="update" name="UPDATE_LINE_ITEM" value="Mettre à jour"/></td>
                                    <td><input type="button" id="order" value="Commander" /></td>
                                    <td><input type="button" id="returnToHome" value="Retourner au Catalogue"/> </td>
                                </tr>
                            </tbody>
                        </table>
                        <input type="hidden" id="actionUpdate" name="actionUpdate" value=""/>
                    </form>
                </div>
            </div>
        </div>

    </body>
    <form id="UpdateCartForm" action="UpdateCartServlet">
        <input id="DELETE_LINE_ITEM" type="hidden" name="DELETE_LINE_ITEM"/>
        <input type="hidden" id="actionDelete" name="actionDelete" value=""/>
    </form>
    <%--form id="OrderForm" action="login.jsp"></form--%>
    <form id="LoadForm" action="LoadServlet"></form>

