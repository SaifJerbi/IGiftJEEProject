/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unice.miage.igift.servlet;

import com.unice.miage.igift.dao.CategoryDaoLocal;
import com.unice.miage.igift.dao.ProductDaoLocal;
import com.unice.miage.igift.entity.Category;
import com.unice.miage.igift.entity.Product;
import com.unice.miage.igift.enumeration.CategoryEnum;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Root
 */
public class LoadServlet extends HttpServlet {

    @EJB
    private ProductProcessLocal productProcessBean;
    @EJB
    private CategoryDaoLocal categoryDaoBean;
    @EJB
    private ProductDaoLocal productDaoBean;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Collection<Category> categoryList = categoryDaoBean.getCategoryList();
        Collection<Product> productList = productDaoBean.getProductList();


        if (categoryList.isEmpty()) {
            categoryDaoBean.createCategory();
        }
        if (productList.isEmpty() && !categoryDaoBean.getCategoryList().isEmpty()) {
            productProcessBean.createProduct();
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("productList", productList);
        session.setAttribute("categoryList", categoryList);

        RequestDispatcher dp = request.getRequestDispatcher("home.jsp");
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
