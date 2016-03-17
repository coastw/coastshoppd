/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.factory.*;
import com.coastshop.service.*;
import com.coastshop.util.PageSeperator;
import com.coastshop.vo.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Coast
 */
public class ProductListServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //TODO
            String sn = request.getParameter("sn");
            if (sn == null) {
                sn = "";
            }
            String strpage = request.getParameter("page");
            int page = 1;
            if (strpage != null) {
                page = Integer.parseInt(strpage);
            }
            IProductService productService = ServiceFactory.getProductServiceInstance();
            PageSeperator pageSeperator = new PageSeperator(productService.getCount(sn), 20, page);
            List<Product> list = productService.findLikeSn(sn, pageSeperator);
            //TODO:List<Product> list = productService.findLikeSn(sn, new PageSeperator());
            request.setAttribute("sn", sn);
            request.setAttribute("ps", pageSeperator);
            request.setAttribute("productlist", list);
            request.getRequestDispatcher("/productlist.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
