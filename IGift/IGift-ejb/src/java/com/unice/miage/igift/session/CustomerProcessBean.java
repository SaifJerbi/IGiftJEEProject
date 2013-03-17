/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.session;

import com.unice.miage.igift.dao.CustomerDaoLocal;
import com.unice.miage.igift.entity.Customer;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Root
 */
@Stateless
public class CustomerProcessBean implements CustomerProcessLocal {

    @EJB
    private CustomerDaoLocal customerDaoBean;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    public Customer createCustomer(String firstName, String lastName, String email, String password, String billingAddress, String shipingAddress, int billingZipCode, int shipingZipCode, String billingCity, String shipingCity, int phone) {
        Customer customer = new Customer();
        setCustomerAttribute(customer, firstName, lastName, email, password, billingAddress, shipingAddress, billingZipCode, shipingZipCode, billingCity, shipingCity, phone);
        customerDaoBean.persist(customer);

        return customer;
    }

    public Customer updateCustomer(Long customerId, String firstName, String lastName, String email, String password, String billingAddress, String shippingAddress, int billingZipCode, int shippingZipCode, String billingCity, String shippingCity, int phone) {
        Customer customer = customerDaoBean.findCustomerById(customerId);
        setCustomerAttribute(customer, firstName, lastName, email, password, billingAddress, shippingAddress, billingZipCode, shippingZipCode, billingCity, shippingCity, phone);
        customerDaoBean.mergeCustomer(customer);
        return customer;
    }

    public void setCustomerAttribute(Customer customer, String firstName, String lastName, String email, String password, String billingAddress, String shipingAddress, int billingZipCode, int shipingZipCode, String billingCity, String shipingCity, int phone) {
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setBillingAddress(billingAddress);
        customer.setBillingZipCode(billingZipCode);
        customer.setBillingCity(billingCity);
        customer.setShipingAddress(shipingAddress);
        customer.setShipingZipCode(shipingZipCode);
        customer.setShipingCity(shipingCity);
        customer.setPhone(phone);
    }

    public Customer getCustomerByEmailAndPassword(String mail, String password) {
        Customer cutomer = customerDaoBean.getCustomerByEmail(mail);
        if (cutomer != null && password.equals(cutomer.getPassword())) {
            return cutomer;
        }
        return null;
    }
}
