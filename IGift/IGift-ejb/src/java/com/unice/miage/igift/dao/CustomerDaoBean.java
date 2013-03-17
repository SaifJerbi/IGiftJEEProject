/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.dao;

import com.unice.miage.igift.entity.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Root
 */
@Stateless
public class CustomerDaoBean implements CustomerDaoLocal {

    @PersistenceContext
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    public Customer findCustomerById(Long customerId) {
        return (Customer) em.createNamedQuery("Customer.findByCustomerId").setParameter("customerId", customerId).getSingleResult();
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public Customer getCustomerByEmail(String email) {
        Customer customer = null;
        try {
            customer = (Customer) em.createNamedQuery("Customer.findByCustomerMail").setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Ce client n'existe pas.");
        }
        return customer;
    }

    public void mergeCustomer(Customer customer) {
        em.merge(customer);
    }
}
