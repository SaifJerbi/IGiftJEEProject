/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unice.miage.igift.session;

import com.unice.miage.igift.entity.Customer;
import javax.ejb.Local;

/**
 *
 * @author Root
 */
@Local
public interface CustomerProcessLocal {

    public Customer createCustomer(java.lang.String firstName, java.lang.String lastName, java.lang.String email, java.lang.String password, java.lang.String billingAddress, java.lang.String shipingAddress, int billingZipCode, int shipingZipCode, java.lang.String billingCity, java.lang.String shipingCity, int phone);

    public Customer updateCustomer(Long customerId, String firstName, String lastName, String email, String password, String billingAddress, String shippingAddress, int billingZipCode, int shippingZipCode, String billingCity, String shippingCity, int phone);

    public Customer getCustomerByEmailAndPassword(String mail, String password);
    
}
