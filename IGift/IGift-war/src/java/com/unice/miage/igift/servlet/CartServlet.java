/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.servlet;

import com.unice.miage.igift.dao.ProductDaoLocal;
import com.unice.miage.igift.entity.LineItem;
import com.unice.miage.igift.entity.Product;
import com.unice.miage.igift.session.CartLocal;
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
public class CartServlet extends HttpServlet {

    @EJB
    private ProductDaoLocal productDaoBean;
    @EJB
    private CartLocal cartProcessBean;
    public static final int QUANTITY_ERROR_CODE = 1;
    public static final int TYPE_ERROR_CODE = 2;
    public static final int REMAIN_QUANTITY_ERROR_CODE = 3;

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
        int error = -1;
        boolean redirect = false;
        String productIdStr = request.getParameter("produitId");
        String quantityStr = request.getParameter("quantity");

        if (HelperFunctions.isSet(productIdStr)) {
            Long productId = Long.valueOf(productIdStr);
            Product product = productDaoBean.getProductByProductId(productId);
            request.setAttribute("product", product);
            if (!HelperFunctions.isSet(quantityStr)) {
                error = QUANTITY_ERROR_CODE;
            } else if (!HelperFunctions.isIntegerSet(quantityStr)) {
                error = TYPE_ERROR_CODE;
            } else if (Integer.valueOf(quantityStr) > productDaoBean.getProductByProductId(productId).getQuantity()) {
                error = REMAIN_QUANTITY_ERROR_CODE;
            } else {
                redirect = true;
                int quantity = Integer.valueOf(quantityStr);
                cartProcessBean.addLineItem(productId, quantity);
            }
        }
        request.getSession(true).setAttribute("MyStatefullCartProcessBean", cartProcessBean);
        Collection<LineItem> lineItemList = cartProcessBean.getLineItemList();
        request.getSession(true).setAttribute("lineItemList", lineItemList);
        request.getSession(true).setAttribute("totalItemsPrice", cartProcessBean.getTotalPriceLineItemList());

        if (error > 0) {
            request.setAttribute("errorCode", error);
            dp = request.getRequestDispatcher("addItem.jsp");
        } else {
            dp = request.getRequestDispatcher("cart.jsp");
        }

        if (redirect) {
            response.sendRedirect(request.getContextPath() + "/CartServlet");
        } else {
            dp.forward(request, response);
        }
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
