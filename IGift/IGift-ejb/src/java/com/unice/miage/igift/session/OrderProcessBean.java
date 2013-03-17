/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.session;

import com.unice.miage.igift.dao.CustomerDaoLocal;
import com.unice.miage.igift.dao.LineItemDaoLocal;
import com.unice.miage.igift.dao.PurchaseOrderDaoLocal;
import com.unice.miage.igift.entity.LineItem;
import com.unice.miage.igift.entity.PurchaseOrder;
import java.sql.Timestamp;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Root
 */
@Stateless
public class OrderProcessBean implements OrderProcessLocal {

    @EJB
    private LineItemDaoLocal lineItemDaoBean;
    @EJB
    private PurchaseOrderDaoLocal purchaseOrderDaoBean;
    @EJB
    private CustomerDaoLocal customerDaoBean;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    public PurchaseOrder order(Long customerId, CartLocal cartProcessBean) {
        for (Iterator it = cartProcessBean.getLineItemList().iterator(); it.hasNext();) {
            LineItem item = (LineItem) it.next();
            item.setId(null);
            lineItemDaoBean.persistLineItem(item);
        }
        PurchaseOrder order = new PurchaseOrder();
        order.setCustomer(customerDaoBean.findCustomerById(customerId));
        order.setCreationDate(new Timestamp(System.currentTimeMillis()));
        order.setLineItems(cartProcessBean.getLineItemList());
        order.setTotalPrice(cartProcessBean.getTotalPriceLineItemList());
        purchaseOrderDaoBean.executeOrder(order);

        return order;
    }
}
