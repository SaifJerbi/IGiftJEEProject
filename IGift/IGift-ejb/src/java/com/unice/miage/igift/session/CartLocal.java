/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unice.miage.igift.session;

import javax.ejb.Local;

/**
 *
 * @author Root
 */
@Local
public interface CartLocal {

    public java.util.Collection<com.unice.miage.igift.entity.LineItem> getLineItemList();

    public com.unice.miage.igift.entity.LineItem getLineItemByProduct(java.lang.Long productId);

    public com.unice.miage.igift.entity.PurchaseOrder getPurchaseOrder();

    public void setLineItemList(java.util.Collection<com.unice.miage.igift.entity.LineItem> lineItemList);

    public void addLineItem(java.lang.Long productId, int quantity);

    public void updateLineItem(com.unice.miage.igift.entity.LineItem lineItem, int quantity);

    public void deleteLineItem(Long lineItemId);

    public double getTotalPriceLineItemList();




    
}
