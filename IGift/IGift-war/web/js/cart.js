/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function init(){
    var b1 = dojo.byId('returnToHome');
    var b2 = dojo.byId('order');
    var b3 = dojo.byId('update');
    var b4 = dojo.byId('add');

    if (b1 != null){
        dojo.addOnLoad(function(){
            dojo.connect(b1,"onclick",backHome);
        });
    }
    if (b2 != null){
        dojo.addOnLoad(function(){
            dojo.connect(b2,"onclick",orderItem);
        });
    }
    if (b3 != null){
        dojo.addOnLoad(function(){
            dojo.connect(b3,"onclick",updateItem);
        });
    }
    if (b4 != null){
        dojo.addOnLoad(function(){
            dojo.connect(b4,"onclick",addItem);
        });
    }
    var deleteButton = document.getElementsByName("DELETE");
    if(deleteButton!=null){
        for (i = 0; i < deleteButton.length; i++) {
            deleteButton[i].addEventListener('click', deleteItem, false);
        }
    }

/*
    var b2 = document.getElementById("order");
    var b3 = document.getElementById("update");

     dojo.addOnLoad(function(){
        dojo.connect(node,"onclick",submit);
    });



    var b1 = document.getElementById("returnToHome");
    var b2 = document.getElementById("order");
    var b3 = document.getElementById("update");
    if (b1 != null){
        b1.addEventListener('click', backHome, false);
    }
     if (b2 != null){
        b2.addEventListener('click', orderItem, false);
    }
     if (b3 != null){
        b3.addEventListener('click', updateItem, false);
    }

    var deleteButton = document.getElementsByName("DELETE");
    if(deleteButton!=null){
        for (i = 0; i < deleteButton.length; i++) {
            deleteButton[i].addEventListener('click', deleteItem, false);
        }
    }
*/
}

function deleteItem(){
    var form = dojo.byId("UpdateCartForm");
    var lineItemIdToDelete = dojo.byId("DELETE_LINE_ITEM")
    var buttonId = this.id;
    lineItemIdToDelete.value = buttonId;
    form.submit();
}

function backHome(){
    var form = dojo.byId("LoadForm");
    form.action = "LoadServlet";
    form.submit();
}

function updateItem(){
    if(checkData()){
        var form = dojo.byId("CartForm");
        form.action = "UpdateCartServlet";
        form.submit();
    }
}

function addItem(){
    if(checkData()){
        var form = dojo.byId("AddCartForm");
        form.action="CartServlet";
        //alert("I'm submiting");
        form.submit();
    }
}

function orderItem(){ 
    if(checkData()){
        var form = dojo.byId("CartForm");
        form.action = "login.jsp";
        form.submit();
    }
}

function checkData(){
    var inputs = document.getElementsByTagName("input");
    var val = 0;
    for (i = 0; i < inputs.length; i++) {
        if(inputs[i].type == "text"){
            val = val + 1;
        }
    }
    if (val == 0){
        alert("Le panier est vide.");
        return false;
    } else if (!verifyForm()) {
        return false;
    }
    return true;
}


