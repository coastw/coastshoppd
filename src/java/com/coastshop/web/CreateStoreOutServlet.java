/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.vo.StoreOut;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Coast
 */
public class CreateStoreOutServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
        int id = 0;
        try {
            /* TODO output your page here. You may use following sample code. */
            String outlistid = request.getParameter("outlistid");
            String sno = request.getParameter("sno");
            if (outlistid == null || sno == null || sno.trim().isEmpty()) {
                out.println("<h1>款号为空</h1>");
            } else {
                int listid = Integer.parseInt(outlistid);
                String sn = sno.substring(0, 9);
                String color = sno.substring(9,14);
                String size = sno.substring(14, 15);
                //todo
                StoreOut so = new StoreOut();
                so.setSn(sn);
                so.setColor(color);
                so.setSize(size);
                so.setAmount(1);
                so.setOutlistid(listid);
                synchronized (this) {
                    id = ServiceFactory.getStoreOutServiceInstance().add(so);
                    if (id != 0) {
                        HttpSession session = request.getSession();
                        session.setAttribute("lastid", id);
                    }
                }
                //System.out.println(key);
                out.print(id);
            }
        } catch (Exception e) {
            out.print(id);
        }  finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
