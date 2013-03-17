/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.session;

import com.unice.miage.igift.dao.ProductDaoLocal;
import com.unice.miage.igift.entity.LineItem;
import com.unice.miage.igift.entity.Product;
import com.unice.miage.igift.entity.PurchaseOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Root
 */
@Stateful
public class CartBean implements CartLocal {

    @EJB
    private ProductDaoLocal productDaoBean;
    private PurchaseOrder order;
    private Collection<LineItem> lineItemList;

    public CartBean() {
        this.lineItemList = new ArrayList<LineItem>();
    }

    public void addLineItem(Long productId, int quantity) {
        LineItem lineItem = getLineItemByProduct(productId);
        Product product = productDaoBean.getProductByProductId(productId);
        double price = product.getPrice();
        if (lineItem == null) {
            lineItem = getPurchaseOrder().addPurchaseAndLineItem(product, quantity, price);
            if (!lineItemList.contains(lineItem)) {
                lineItemList.add(lineItem);
            }
        } else {
            getPurchaseOrder().addPurchase(lineItem, quantity, price);
        }
    }

    public void updateLineItem(LineItem lineItem, int quantity) {
        double price = lineItem.getProduct().getPrice();
        getPurchaseOrder().updatePurchaseAndUpdateLineItem(lineItem, quantity, price);
    }

    public void deleteLineItem(Long lineItemId) {
        Collection<LineItem> lineItemListTest = new ArrayList<LineItem>();
        for (Iterator<LineItem> it = lineItemList.iterator(); it.hasNext();) {
            LineItem lineItem = it.next();
            if (lineItem.getId().equals(lineItemId)) {
                lineItemListTest.add(lineItem);
            }
        }
        lineItemList.removeAll(lineItemListTest);
    }

    public Collection<LineItem> getLineItemList() {
        return lineItemList;
    }

    public double getTotalPriceLineItemList() {
        double totlalPrice = 0;
        for (Iterator<LineItem> it = lineItemList.iterator(); it.hasNext();) {
            LineItem lineItem = it.next();
            totlalPrice = totlalPrice + lineItem.getSubTotal();
        }
        return totlalPrice;
    }

    public void setLineItemList(Collection<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    public LineItem getLineItemByProduct(Long productId) {
        LineItem lineItem = null;
        if ((lineItemList != null) && (!lineItemList.isEmpty())) {
            for (Iterator<LineItem> it = lineItemList.iterator(); it.hasNext();) {
                LineItem item = it.next();
                if (item.getProduct().getId().equals(productId)) {
                    lineItem = item;
                }
            }
        }
        return lineItem;
    }

    public PurchaseOrder getPurchaseOrder() {
        if (order == null) {
            order = new PurchaseOrder();
        }
        return order;
    }
}
