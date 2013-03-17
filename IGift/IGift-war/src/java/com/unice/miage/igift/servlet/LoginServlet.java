/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.servlet;

import com.unice.miage.igift.entity.Customer;
import com.unice.miage.igift.entity.LineItem;
import com.unice.miage.igift.session.CartLocal;
import com.unice.miage.igift.session.CustomerProcessLocal;
import com.unice.miage.igift.tools.HelperFunctions;
import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Root
 */
public class LoginServlet extends HttpServlet {

    @EJB
    private CustomerProcessLocal customerProcessBean;
    private CartLocal cartProcessBean;
    public static final String MAIL_ADDRESS = "MAIL_ADDRESS";
    public static final String PASSWORD = "PASSWORD";
    public static final String ERROR_MEASSAGE = "Saisissez votre adresse e-mail et mot de passe";
    public static final String PASSWORD_ERROR_MEASSAGE = "L'adresse e-mail ou le mot de passe que vous avez saisi est incorrect.";

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dp;

        String email = request.getParameter(MAIL_ADDRESS);
        String password = request.getParameter(PASSWORD);

        cartProcessBean = (CartLocal) request.getSession().getAttribute("MyStatefullCartProcessBean");
        Collection<LineItem> lineItemList = cartProcessBean.getLineItemList();
        request.getSession(true).setAttribute("lineItemList", lineItemList);

        request.setAttribute("totalItemsPrice", cartProcessBean.getTotalPriceLineItemList());

        if (HelperFunctions.isSet(email) && HelperFunctions.isSet(password)) {
            Customer customer = customerProcessBean.getCustomerByEmailAndPassword(email, password);
            if (customer != null) {
                request.getSession(true).setAttribute("customer", customer);
                dp = request.getRequestDispatcher("order.jsp");
            } else {
                request.setAttribute("passwordErrorMessage", PASSWORD_ERROR_MEASSAGE);
                dp = request.getRequestDispatcher("login.jsp");
            }

        } else {
            request.setAttribute("errorMessage", ERROR_MEASSAGE);
            dp = request.getRequestDispatcher("login.jsp");
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
