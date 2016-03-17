/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.service.IOutListService;
import com.coastshop.util.PageSeperator;
import com.coastshop.util.HtmlOutList;
import com.coastshop.vo.OutList;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Coast
 */
public class OutListServlet extends HttpServlet {

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
            String strshopid = request.getParameter("shopid");
            int shopid = 0;
            if ( strshopid != null ){
                shopid = Integer.parseInt(strshopid);
            }
            String strpage = request.getParameter("page");
            int page = 1;
            if (strpage != null) {
                page = Integer.parseInt(strpage);
            }
            
            
            IOutListService osService = ServiceFactory.getOutListServiceInstance();
            PageSeperator pageSeperator = null;
            List<HtmlOutList> list = null;
            if (shopid != 0){
                pageSeperator = new PageSeperator(osService.getCountByShop(shopid), 20, page);
                list = osService.getHtmlByShop(shopid, pageSeperator);
            }else {
                pageSeperator = new PageSeperator(osService.getCount(), 20, page);
                list = osService.getHtmlAll(pageSeperator);
            }
            request.setAttribute("shopid", shopid);
            request.setAttribute("ps", pageSeperator);
            request.setAttribute("list", list);
            request.getRequestDispatcher("/outlist.jsp").forward(request, response);
        } catch (Exception e) {
            out.println(e);
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
