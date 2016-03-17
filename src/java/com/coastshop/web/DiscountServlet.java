/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.vo.Discount;
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
public class DiscountServlet extends HttpServlet {

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
        //edit
        String method = request.getParameter("method");
        if (method != null && method.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String cgNum = request.getParameter("cgnum").trim();
            String cgChar = request.getParameter("cgchar").trim();
            String reg = request.getParameter("reg").trim();
            String pers = request.getParameter("pers").trim();

            Discount d = new Discount();
            d.setId(id);
            d.setCategorynum(cgNum);
            d.setCategorychar(cgChar);
            d.setRegex(reg);
            d.setPersent(pers);
            int update = 0;
            try {
                update = ServiceFactory.getDiscountServiceInstance().update(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
            PrintWriter out = response.getWriter();
            out.print(update);
            out.close();
            return;
        }
        //add new
        String categoryNum = request.getParameter("categorynum");
        String categoryChar = request.getParameter("categorychar");
        String regex = request.getParameter("regex");
        String persent = request.getParameter("persent");
        if (categoryNum != null && categoryChar != null && regex != null && persent != null) {
            if (categoryNum.trim().isEmpty() || categoryChar.trim().isEmpty() || regex.trim().isEmpty() || persent.trim().isEmpty()) {
                request.setAttribute("lastinsertid", 0);
            } else {
                Discount d = new Discount();
                d.setCategorynum(categoryNum);
                d.setCategorychar(categoryChar);
                d.setRegex(regex);
                d.setPersent(persent);
                try {
                    int add = ServiceFactory.getDiscountServiceInstance().add(d);
                    request.setAttribute("lastinsertid", add);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            List<Discount> list = ServiceFactory.getDiscountServiceInstance().getAll();
            request.setAttribute("list", list);
            request.getRequestDispatcher("/discount.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
