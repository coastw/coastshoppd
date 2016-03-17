/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.util.HtmlOutList;
import com.coastshop.vo.OutList;
import com.coastshop.vo.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Coast
 */
public class CreateOutListServlet extends HttpServlet {

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
            int brandid = Integer.parseInt(request.getParameter("brandid"));
            int shopid = Integer.parseInt(request.getParameter("shopid"));
            int userid = ((User)request.getSession().getAttribute("user")).getId();
            OutList outList = new OutList();
            outList.setBrandid(brandid);
            outList.setShopid(shopid);
            outList.setUserid(userid);
            outList.setDate(new Date());
            int listid = ServiceFactory.getOutListServiceInstance().insert(outList);
            if (listid != 0) {
                //跳转到扫描条码页
                //TODO 将listid存到session？还是request
                HtmlOutList htmlOutList = ServiceFactory.getOutListServiceInstance().getHtmlById(listid);
                HttpSession session = request.getSession();
                session.setAttribute("listid", listid);
                session.setAttribute("currentlist", htmlOutList);
                session.removeAttribute("lastid");
                response.sendRedirect("scan.jsp");
            }else{
                out.print( "<h3>创建失败</h3>" );
            }
        } catch (Exception e) {
            e.printStackTrace();
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
