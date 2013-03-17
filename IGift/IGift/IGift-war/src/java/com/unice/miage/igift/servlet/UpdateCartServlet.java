/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.servlet;

import com.unice.miage.igift.entity.LineItem;
import com.unice.miage.igift.session.CartLocal;
import java.io.IOException;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Root
 */
public class UpdateCartServlet extends HttpServlet {
    public static final String DELETE_LINE_ITEM = "DELETE_LINE_ITEM";
    public static final String UPDATE_LINE_ITEM = "UPDATE_LINE_ITEM";
    public static final String LINE_ITEM_ID = "LINE_ITEM_ID";
    public static final String QUANTITY_ERROR = "Veuillez saisir la quantité.";
    public static final String TYPE_ERROR = "Champ invalide";
    public static final String REMAIN_QUANTITY_ERROR = "Quantité supperieure au stock";
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

        cartProcessBean = (CartLocal) request.getSession(true).getAttribute("MyStatefullCartProcessBean");
        boolean isUpdateAction =  request.getParameter("actionUpdate") != null ;
        boolean isDeleteAction =  request.getParameter("actionDelete") != null ;
        
        String lineItemIdValue = request.getParameter(DELETE_LINE_ITEM);

        Integer quantity = null;
        Long lineItemId = null;
        if (lineItemIdValue != null) {
            lineItemId = Long.valueOf(lineItemIdValue);
        }

        if (isDeleteAction) {
            cartProcessBean.deleteLineItem(lineItemId);
        } else if (isUpdateAction) {
            for (Iterator it = cartProcessBean.getLineItemList().iterator(); it.hasNext();) {
                LineItem lineItem = (LineItem) it.next();
                String itemLineIdStr = String.valueOf(lineItem.getId());

                String quantityStr = request.getParameter(itemLineIdStr);
                try {
                    quantity = Integer.valueOf(quantityStr);
                    if (quantity <= lineItem.getProduct().getQuantity()) {
                        cartProcessBean.updateLineItem(lineItem, quantity);
                    } else {
                        request.setAttribute("remainQuantityError", REMAIN_QUANTITY_ERROR);
                    }
                } catch (Exception e) {
                    request.setAttribute("typeError", TYPE_ERROR);
                }
            }
        }

        request.setAttribute("totalItemsPrice", cartProcessBean.getTotalPriceLineItemList());

        RequestDispatcher dp;
        dp = request.getRequestDispatcher("cart.jsp");
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
