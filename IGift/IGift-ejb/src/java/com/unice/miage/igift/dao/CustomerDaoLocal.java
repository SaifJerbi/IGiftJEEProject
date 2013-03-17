/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unice.miage.igift.dao;

import com.unice.miage.igift.entity.Customer;
import javax.ejb.Local;

/**
 *
 * @author Root
 */
@Local
public interface CustomerDaoLocal {

    public void persist(java.lang.Object object);

    public com.unice.miage.igift.entity.Customer findCustomerById(java.lang.Long customerId);

    public Customer getCustomerByEmail(String mail);

    public void mergeCustomer(Customer customer);
    
}
