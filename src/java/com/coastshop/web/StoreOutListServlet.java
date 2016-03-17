/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.util.HtmlOutList;
import com.coastshop.util.StoreOutProductInfo;
import com.coastshop.util.StoreOutProductInfoComparator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Coast
 */
public class StoreOutListServlet extends HttpServlet {

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
            String strid = request.getParameter("outlistid");
            if (strid == null) {
                out.println("<h1>参数不能为空</h1>");
            } else {
                int outlistid = Integer.parseInt(strid);
                //show SAP Upload button
                if (ServiceFactory.getShopinSAPServiceInstance().getByOutListId(outlistid) == null) {
                    request.setAttribute("showUploadSAPButton", true);
                    //上传完成在数据库中添加一条shopinsap记录
                } else {
                    request.setAttribute("showUploadSAPButton", false);
                }
                //data
                List<StoreOutProductInfo> list = ServiceFactory.getStoreOutServiceInstance().findProductInfosByList(outlistid);
                HtmlOutList htmloutlist = ServiceFactory.getOutListServiceInstance().getHtmlById(outlistid);
                //sort
                if (list != null && htmloutlist != null) {
                    Collections.sort(list, new StoreOutProductInfoComparator());    //error null
                    request.setAttribute("sopilist", list);
                    request.setAttribute("outlist", htmloutlist);
                    request.getRequestDispatcher("/storeoutlist.jsp").forward(request, response);
                }else{
                    out.print("<h1>结果为空</h1>");
                }
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
