/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.service.IProductService;
import com.coastshop.vo.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Coast
 */
public class ProductEditServlet extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");
        try {
            String strId = request.getParameter("id");
            Product p = ServiceFactory.getProductServiceInstance().find(Integer.parseInt(strId));
            request.setAttribute("product", p);
            request.getRequestDispatcher("/producteditform.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String strId = request.getParameter("id");
            String strSn = request.getParameter("sn");
            String strPrice = request.getParameter("price");
            if (!strId.isEmpty() && !strPrice.isEmpty() && !strSn.isEmpty()) {
                IProductService service = ServiceFactory.getProductServiceInstance();
                Product p = service.find(Integer.parseInt(strId));
                p.setSn(strSn);
                p.setPrice(Integer.parseInt(strPrice));
                if (service.update(p)) {
                    out.print("<h3>修改成功</h3>");
                    out.print("<a href=\"productlist.do?sn="+ p.getSn() +"\">返回</a>");
                }else{
                    out.print("<h3>修改失败</h3>");
                }
            }else{
                out.print("<h3>参数不能为空</h3>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
