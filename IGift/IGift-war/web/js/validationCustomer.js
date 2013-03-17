/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function init(){
    dojo.addOnLoad(function(){
        var node = dojo.byId('submit');
        dojo.connect(node,"onclick",submit);
    });
}
function submit(){
    var form = dojo.byId('createCustomer');

/*if(verifyForm()){
    form.submit();
}*/
}
function verifyForm(reginfo)
{
    reginfo.firstNameId.value = reginfo.firstNameId.value.split(' ').join('');
    reginfo.lastNameId.value = reginfo.lastNameId.value.split(' ').join('');
    reginfo.passwordId.value = reginfo.passwordId.value.split(' ').join('');
    reginfo.emailId.value = reginfo.emailId.value.split(' ').join('');
    reginfo.billingAddressId.value = reginfo.billingAddressId.value.split(' ').join('');
    reginfo.billingZipCodeId.value = reginfo.billingZipCodeId.value.split(' ').join('');
    reginfo.billingCityId.value = reginfo.billingCityId.value.split(' ').join('');
    reginfo.shippingAddressId.value = reginfo.shippingAddressId.value.split(' ').join('');
    reginfo.shippingZipCodeId.value = reginfo.shippingZipCodeId.value.split(' ').join('');
    reginfo.shippingCityId.value = reginfo.shippingCityId.value.split(' ').join('');
    reginfo.phoneId.value = reginfo.phoneId.value.split(' ').join('');
  
    if ((!exists(reginfo.firstNameId.value)) ||
        (!exists(reginfo.lastNameId.value)) ||
        (!exists(reginfo.passwordId.value)) ||
        (!exists(reginfo.emailId.value)) ||
        (!exists(reginfo.billingAddressId.value)) ||
        (!exists(reginfo.billingZipCodeId.value)) ||
        (!exists(reginfo.billingCityId.value)) ||
        (!exists(reginfo.shippingAddressId.value)) ||
        (!exists(reginfo.shippingZipCodeId.value)) ||
        (!exists(reginfo.shippingCityId.value)) ||
        (!exists(reginfo.phoneId.value)))
        {
        alert("Tout les champs doivent etre rempli.");
        return false;
    }
    else if (!verifyEmail(reginfo.emailId.value.split(' ').join(''))) {
        alert("E-mail invalid.");
        return false;
    }
  
    else if (!verifyZip(reginfo.billingZipCodeId.value.split(' ').join('')) || !verifyZip(reginfo.shippingZipCodeId.value.split(' ').join(''))) {
        //alert("Code postal invalid.");
        return false;
    }
    else if (!verifyPhone(reginfo.phoneId.value.split(' ').join(''))) {
        // alert("Telephone invalid.");
        return false;
    }
    return true;
}

function exists(inputVal)
{
    var result = false;
    for (var i = 0; i <= inputVal.length; i++) {
        if ((inputVal.charAt(i) != " ") && (inputVal.charAt(i) != "")) {
            result = true;
            break;
        }
    }
    return result;
}

function verifyEmail(emailVal)
{
    var result = true;
    var foundAt = false;
    var foundDot = false;
    var atPos = -1;
    var dotPos = -1;
    for (var i = 0; i < emailVal.length; i++) {
        if (emailVal.charAt(i) == "@") {
            foundAt = true;
            atPos = i;
        }
        else if (emailVal.charAt(i) == ".") {
            foundDot = true;
            dotPos= i;
        }
    }
    if ((!foundAt) || (!foundDot) || (dotPos < atPos)) {
        result = false;
    }
    return result;
}

function verifyZip(zipVal)
{
    var result = true;
    //integerRe = /^\d*$/;
    integerRe = /^\d*\.{0,1}\d+$/;
    if (!integerRe.test(zipVal)){
        alert('Le code postal: '+zipVal + ' est invalid');
        result = false;
    }
    return result;
}

function verifyPhone(phoneVal)
{
    var result = true;
    //phoneRe = /^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$/
    //phoneRe = /^0[1-6]{1}(([0-9]{2}){4})|((\s[0-9]{2}){4})|((-[0-9]{2}){4})$/;
    phoneRe = /^\d*\.{0,1}\d+$/;
    if (!phoneRe.test(phoneVal)){
        alert('Le telephone: '+phoneVal + ' est invalid');
        result = false;
    }
    return result;
}

function verifyNumber(inputField) {
    var result = false;
    var inpVal = parseInt(inputField, 10);
    if (!isNaN(inpVal) && inputField != 0) {
        result = true;
    }
    return result;
}


