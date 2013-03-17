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
        <script type="text/javascript" src="js/validationCustomer.js"></script>
        <script type="text/javascript" >
            dojo.addOnLoad(init);
        </script>
        <title>Order Page</title>
    </head>
    <body class="tundra" >
        <div id="global">
            <div id="header"> <%@include file="header.jsp" %>  </div>
            <div id="menu"> <%@include file="menu.jsp" %> </div>
            <div id="center">
                <div class="order">
                    <h2>Commander</h2>
                    <div class="confirm">
                        <form action="OrderServlet">
                            <div>
                                <table border="1">
                                    <thead>
                                        <tr>
                                            <th>Produit</th>
                                            <th>Prix<br> Euro</th>
                                            <th>Quantité</th>
                                            <th>Total <br> Euro</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="lineItem" items="${lineItemList}">
                                            <tr>
                                                <td>${lineItem.product.name}</td>
                                                <td>${lineItem.product.price}</td>
                                                <td>
                                                    ${lineItem.quantity}
                                                </td>
                                                <td>${lineItem.subTotal}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tbody>
                                        <tr>
                                            <td>Total <br> Euro</td>
                                            <td>${totalItemsPrice}</td>
                                        </tr>
                                        <tr>
                                            <td><input id="confirmB" type="submit" value="Confirmer"/></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </form>
                    </div>
                    <div class="update">
                        <form id="createCustomer" action="CreateAndUpdateCustomerServlet" method="post" onsubmit="return verifyForm(this);" target="_self" name="reginfo">
                            <table border="0">
                                <tbody>
                                    <tr>
                                        <td>Nom</td>
                                        <td>
                                            <input id="firstNameId" type="text" name="FIRSTNAME" value="${customer.firstName}"
                                                   dojoType="dijit.form.ValidationTextBox"
                                                   propercase="true"
                                                   required="true"
                                                   promptMessage="Entrer votre nom"
                                                   />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Prenom</td>
                                        <td>
                                            <input id="lastNameId" type="text" name="LASTNAME" value="${customer.lastName}"
                                                   dojoType="dijit.form.ValidationTextBox"
                                                   propercase="true"
                                                   required="true"
                                                   promptMessage="Entrer votre Prénom" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>E-mail</td>
                                        <td>
                                            <input id="emailId" type="text" name="EMAIL"  value="${customer.email}"
                                                   dojoType="dijit.form.ValidationTextBox"
                                                   required="true"
                                                   promptMessage="Entrer votre email" />

                                            <br>
                                            <div class="error">${mailError}</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Mot de passe</td>
                                        <td>
                                            <input id="passwordId" type="password" name="PASSWORD"  value="${customer.password}"
                                                   dojoType="dijit.form.ValidationTextBox"
                                                   required="true"
                                                   promptMessage="Entrer votre mot de passe" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h4>Facturation</h4></td>
                                    </tr>
                                    <tr>
                                        <td>Adresse de facturation</td>
                                        <td>
                                            <input id="billingAddressId" type="text" name="BILLING_ADDRESS" value="${customer.billingAddress}"
                                                   dojoType="dijit.form.ValidationTextBox"
                                                   lowercase="false"
                                                   required="true"
                                                   promptMessage="Entrer votre adresse de facturation" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Code postal</td>
                                        <td>
                                            <input type="text" id="billingZipCodeId" name="BILLING_ZIPCODE" value="${customer.billingZipCode}"
                                                   dojoType="dijit.form.ValidationTextBox"
                                                   size="5"
                                                   maxlength="9"
                                                   required="true"
                                                   places="9"
                                                   promptMessage="Entrer votre code postal"
                                                   invalidMessage= "Code postal invalid."
                                                   />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Ville</td>
                                        <td>
                                            <input type="text" id="billingCityId" name="BILLING_CITY" value="${customer.billingCity}"
                                                   dojoType="dijit.form.ValidationTextBox"
                                                   uppercase="true"
                                                   required="true"
                                                   promptMessage="Entrer votre ville" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h4>Livraison</h4></td>
                                    </tr>
                                    <tr>
                                        <td>Adresse de livraison</td>
                                        <td>
                                            <input id="shippingAddressId" type="text" name="SHIPPIMG_ADDRESS"
                                                   value="${customer.shipingAddress}"

                                                   dojoType="dijit.form.ValidationTextBox"
                                                   lowercase="false"
                                                   required="true"
                                                   promptMessage="Entrer votre adresse de livraison" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Code postal</td>
                                        <td>
                                            <input type="text" id="shippingZipCodeId" name="SHIPPING_ZIPCODE" value="${customer.shipingZipCode}"
                                                   dojoType="dijit.form.ValidationTextBox"
                                                   size="5"
                                                   maxlength="9"
                                                   required="true"
                                                   places="9"
                                                   promptMessage="Entrer votre code postal"
                                                   invalidMessage= "Code postal invalid."
                                                   />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Ville</td>
                                        <td>
                                            <input type="text" id="shippingCityId" name="SHIPPING_CITY" value="${customer.shipingCity}"
                                                   dojoType="dijit.form.ValidationTextBox"
                                                   uppercase="true"
                                                   required="true"
                                                   promptMessage="Entrer votre ville" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Telephone</td>
                                        <td>
                                            <input type="text" id="phoneId" name="PHONE" value="${customer.phone}"
                                                   dojoType="dijit.form.ValidationTextBox"
                                                   lowercase="false"
                                                   required="true"
                                                   promptMessage="Entrer votre numero de telephone"
                                                   invalidMessage= "Code postal invalid."
                                                   />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <div class="error"> ${errorMessage} <br> ${typeErrorMessage}</div><br>
                                            <input type="hidden" name="CUSTOMER_ID" value="${customer.id}">
                                            <input id="submit" type="submit" value="Mettre à jour le client">
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
