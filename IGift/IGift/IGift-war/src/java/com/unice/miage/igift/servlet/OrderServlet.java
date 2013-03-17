/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.servlet;

import com.unice.miage.igift.entity.Customer;
import com.unice.miage.igift.entity.LineItem;
import com.unice.miage.igift.entity.PurchaseOrder;
import com.unice.miage.igift.session.CartLocal;
import com.unice.miage.igift.session.OrderProcessLocal;
import com.unice.miage.igift.session.ProductProcessLocal;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
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
public class OrderServlet extends HttpServlet {

    @EJB
    private ProductProcessLocal productProcessBean;
    @EJB
    private OrderProcessLocal orderProcessBean;
    private CartLocal cartProcessBean;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        cartProcessBean = (CartLocal) request.getSession().getAttribute("MyStatefullCartProcessBean");
        Customer customer = (Customer) request.getSession().getAttribute("customer");

        PurchaseOrder order = orderProcessBean.order(customer.getId(), cartProcessBean);

        Collection<LineItem> lineItemList = order.getLineItems();
        for (Iterator<LineItem> it = lineItemList.iterator(); it.hasNext();) {
            LineItem lineItem = it.next();
            productProcessBean.updateProductQuantity(lineItem.getProduct().getId(), lineItem.getQuantity());
        }
        request.getSession(true).setAttribute("order", order);
        cartProcessBean.getLineItemList().clear();

        RequestDispatcher dp = request.getRequestDispatcher("confirm.jsp");
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
