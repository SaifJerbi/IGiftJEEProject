<%--
    Document   : createCustomer
    Created on : 12 nov. 2009, 19:44:06
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
        <script type="text/javascript" src="js/validationCustomer.js"></script>
        <script type="text/javascript" >
            dojo.addOnLoad(init);
        </script>
        <title>Créer un Nouveau Client</title>
    </head>
    <body class="tundra" onload="init();">
        <div id="global">
            <div id="header"> <%@include file="header.jsp" %>  </div>
            <div id="menu"> <%@include file="menu.jsp" %> </div>
            <div id="center">
                <div class="cart"> <a href="CartServlet"><img src="images/Shopping_Cart.png" alt="" title="Panier"></a></div>
                <div class="CreateCustomer">
                    <h3> Créer un nouveau client</h3>
                    <form id="createCustomer" action="CreateAndUpdateCustomerServlet" method="post" onsubmit="return verifyForm(this);" target="_self" name="reginfo">
                        <table border="0">
                            <tbody>
                                <tr>
                                    <td>Nom</td>
                                    <td>
                                        <input id="firstNameId" type="text" name="FIRSTNAME"
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
                                        <input id="lastNameId" type="text" name="LASTNAME"
                                               dojoType="dijit.form.ValidationTextBox"
                                               propercase="true"
                                               required="true"
                                               promptMessage="Entrer votre Prénom" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>E-mail</td>
                                    <td>
                                        <input id="emailId" type="text" name="EMAIL"
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
                                        <input id="passwordId" type="password" name="PASSWORD"
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
                                        <input id="billingAddressId" type="text" name="BILLING_ADDRESS"
                                               dojoType="dijit.form.ValidationTextBox"
                                               lowercase="false"
                                               required="true"
                                               promptMessage="Entrer votre adresse de facturation" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Code postal</td>
                                    <td>
                                        <input type="text" id="billingZipCodeId" name="BILLING_ZIPCODE"
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
                                        <input type="text" id="billingCityId" name="BILLING_CITY"
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
                                               dojoType="dijit.form.ValidationTextBox"
                                               lowercase="false"
                                               required="true"
                                               promptMessage="Entrer votre adresse de livraison" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Code postal</td>
                                    <td>
                                        <input type="text" id="shippingZipCodeId" name="SHIPPING_ZIPCODE"
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
                                        <input type="text" id="shippingCityId" name="SHIPPING_CITY"
                                               dojoType="dijit.form.ValidationTextBox"
                                               uppercase="true"
                                               required="true"
                                               promptMessage="Entrer votre ville" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Telephone</td>
                                    <td>
                                        <input type="text" id="phoneId" name="PHONE"
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
                                        <input id="submit" type="submit" value="Créer un nouveau compte">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
