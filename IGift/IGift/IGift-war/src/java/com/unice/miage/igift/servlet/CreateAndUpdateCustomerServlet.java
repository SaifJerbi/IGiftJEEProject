/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.servlet;

import com.unice.miage.igift.dao.CustomerDaoLocal;
import com.unice.miage.igift.entity.Customer;
import com.unice.miage.igift.session.CartLocal;
import com.unice.miage.igift.session.CustomerProcessLocal;
import com.unice.miage.igift.tools.HelperFunctions;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Root
 */
public class CreateAndUpdateCustomerServlet extends HttpServlet {

    
    @EJB
    private CustomerDaoLocal customerDaoBean;
    @EJB
    private CustomerProcessLocal customerProcessBean;
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String BILLING_ADDRESS = "BILLING_ADDRESS";
    public static final String BILLING_ZIPCODE = "BILLING_ZIPCODE";
    public static final String BILLING_CITY = "BILLING_CITY";
    public static final String SHIPPIMG_ADDRESS = "SHIPPIMG_ADDRESS";
    public static final String SHIPPING_ZIPCODE = "SHIPPING_ZIPCODE";
    public static final String SHIPPING_CITY = "SHIPPING_CITY";
    public static final String PHONE = "PHONE";
    public static final String CUSTOMER_ID = "CUSTOMER_ID";
    public static final String ERRORMESSAGE = "Veuillez saisir les données";
    public static final String TYPEERRORMESSAGE = "Champs invalide";
    public static final String MAIL_ERROR_MESSAGE = "Email déja utilié";

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Customer customer = null;
        RequestDispatcher dp;

        String firstName = request.getParameter(FIRSTNAME);
        String lastName = request.getParameter(LASTNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String billingAddress = request.getParameter(BILLING_ADDRESS);
        String billingZipCodeStr = request.getParameter(BILLING_ZIPCODE);
        String billingCity = request.getParameter(BILLING_CITY);
        String shippingAddress = request.getParameter(SHIPPIMG_ADDRESS);
        String shippingZipCodeStr = request.getParameter(SHIPPING_ZIPCODE);
        String shippingCity = request.getParameter(SHIPPING_CITY);
        String phoneStr = request.getParameter(PHONE);
        String customerIdStr = request.getParameter(CUSTOMER_ID);
        int error = -1;

        Long customerId = null;
        int billingZipCode = -1;
        int shippingZipCode = -1;
        int phone = -1;

        try {
            if (HelperFunctions.isSet(billingZipCodeStr)) {
                billingZipCode = Integer.valueOf(billingZipCodeStr);
            }
            if (HelperFunctions.isSet(shippingZipCodeStr)) {
                shippingZipCode = Integer.valueOf(shippingZipCodeStr);
            }
            if (HelperFunctions.isSet(phoneStr)) {
                phone = Integer.valueOf(phoneStr);
            }
        } catch (Exception e) {
            error = 1;
            request.setAttribute("typeErrorMessage", TYPEERRORMESSAGE);
        }
        if (HelperFunctions.isSet(customerIdStr)) {
            customerId = Long.valueOf(customerIdStr);
        }

        if (!HelperFunctions.isSet(billingAddress) || !HelperFunctions.isSet(billingCity) || !HelperFunctions.isSet(email) || !HelperFunctions.isSet(firstName) || !HelperFunctions.isSet(lastName) || !HelperFunctions.isSet(password) || !HelperFunctions.isSet(shippingAddress) || billingZipCode == 0 || phone == 0 || shippingZipCode == 0) {
            request.setAttribute("errorMessage", ERRORMESSAGE);
            error = 2;
        } else if (customerId != null) {
            customer = customerProcessBean.updateCustomer(customerId, firstName, lastName, email, password, billingAddress, shippingAddress, billingZipCode, shippingZipCode, billingCity, shippingCity, phone);
        } else {
            if (customerDaoBean.getCustomerByEmail(email) != null) {
                request.setAttribute("mailError", MAIL_ERROR_MESSAGE);
                error = 3;
            } else {
                if (error != 1) {
                    customer = customerProcessBean.createCustomer(firstName, lastName, email, password, billingAddress, shippingAddress, billingZipCode, shippingZipCode, billingCity, shippingCity, phone);
                }
            }
        }


        request.getSession(true).setAttribute("customer", customer);

        if (error == 1 || error == 2 || error == 3) {
            dp = request.getRequestDispatcher("createCustomer.jsp");
        } else {
            dp = request.getRequestDispatcher("order.jsp");
        }
        dp.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
