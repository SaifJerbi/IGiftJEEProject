/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function verifyForm(){
    var tds = document.getElementsByTagName("td");
    for (i = 0; i < tds.length; i++) {
        if (tds[i].id.indexOf("lineItem_") >= 0){
            
            var remainingQtyInput =  tds[i].getElementsByTagName("input")[1];
            var enteredQtyInput =  tds[i].getElementsByTagName("input")[0];

            tds[i].getElementsByTagName("input")[0].value = enteredQtyInput.value.split(' ').join('');
            enteredQty = enteredQtyInput.value;
            if ((!exists(enteredQty))){
                alert("Tout les champs doivent etre rempli.");
                return false;
            }
            else if (!verifyNumber(enteredQty)) {
                //alert("Quantité non valide.");
                return false;
            }else if (!verifyQuantity(remainingQtyInput, enteredQtyInput)) {
                alert("Quantité supperieur au stock");
                return false;
            }
        }
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

function verifyNumber(inputField) {
    var result = true;
    integerRe = /^\d*\.{0,1}\d+$/;
    if (!integerRe.test(inputField)){
        alert('La quantité '+inputField + ' est invalid');
        result = false;
    }
    return result;
}

function verifyQuantity(remainingQtyInput, enteredQtyInput) {
    var result = false;
    var remainingQty =  parseInt(remainingQtyInput.value);
    var enteredQty =  parseInt(enteredQtyInput.value);
    if (enteredQty > 0 && enteredQty <= remainingQty) {
        result = true;
    }
    return result;
}

























