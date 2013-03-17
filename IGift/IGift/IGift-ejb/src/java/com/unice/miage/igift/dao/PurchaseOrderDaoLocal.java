/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unice.miage.igift.dao;

import javax.ejb.Local;

/**
 *
 * @author Root
 */
@Local
public interface PurchaseOrderDaoLocal {

    public void persist(java.lang.Object object);

    public void executeOrder(com.unice.miage.igift.entity.PurchaseOrder order);
    
}
