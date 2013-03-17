<%--
    Document   : addItem
    Created on : 19 oct. 2009, 19:40:44
    Author     : Root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Add Item</title>
    </head>
    <body class="tundra" >
        <div id="global">
            <div id="header"> <%@include file="header.jsp" %>  </div>
            <div id="menu"> <%@include file="menu.jsp" %> </div>
            <div id="center">
                <div class="cart"> <a href="CartServlet"><img src="images/Shopping_Cart.png" alt="" title="Panier"></a></div>
                <div class="add">
                    <h2>Ajouter un article </h2>
                    <form id="AddCartForm">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Produit</th>
                                    <th>Prix <br> Euro
                                    </th>
                                    <th>Quantité</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <img src="images/${product.image}" alt=""><br>
                                        <br> ${product.name}
                                        <br> ${product.description}
                                    </td>
                                    <td>${product.price}</td>
                                    <td id="lineItem_${product.id}">
                                        <input id="${product.id}" name="quantity" type="text"
                                               dojoType="dijit.form.ValidationTextBox"
                                               constraints="{min:1,max:${product.quantity},places:0}"
                                               promptMessage= "Entrer une quantité entre 1 et ${product.quantity}"
                                               required= "true"
                                               invalidMessage= "Valeur Invalide."
                                               />
                                        <br>
                                        <h4>
                                            Quantité restante: ${product.quantity}
                                            <input type="hidden" value=${product.quantity}>
                                        </h4></td>
                                </tr>
                            </tbody>
                            <tbody>
                                <tr>
                                    <td>
                                        <input type="hidden" name="produitId" value=${product.id}>
                                        <input id="add" class=CartMgt" type="image" src="images/Shopping_Cart_Add.png" title="Ajouter au panier">
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <c:set var="errorText">
                            <c:if test="${errorCode == 1}">Veuillez saisir la quantité.</c:if>
                            <c:if test="${errorCode == 2}">Champ invalide</c:if>
                            <c:if test="${errorCode == 3}">Quantité supperieure au stock</c:if>
                        </c:set>
                        <div class="error"><h3>${errorText}</h3> </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>





