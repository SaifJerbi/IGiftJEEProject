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
public interface OrderProcessLocal {

    public com.unice.miage.igift.entity.PurchaseOrder order(java.lang.Long customerId, com.unice.miage.igift.session.CartLocal cartProcessBean);

    
}
