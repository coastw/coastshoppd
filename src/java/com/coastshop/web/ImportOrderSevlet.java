/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.util.ImportOrderUtil;
import com.coastshop.vo.OutList;
import com.coastshop.vo.User;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Coast
 */
public class ImportOrderSevlet extends HttpServlet {

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

        InputStream inputStream = null;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        String serverPath = servletContext.getRealPath("/importorder") + File.separator + "order.xls";
        ServletFileUpload upload = new ServletFileUpload(factory);
        File serverFile = null;
        int shopid = 0;
        int brandid = 0;
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {   //普通表单
                    String name = item.getFieldName();
                    String value = item.getString();
                    if (name.equals("shopid")) {
                        shopid = Integer.valueOf(value);
                    }
                    if (name.equals("brandid")) {
                        brandid = Integer.valueOf(value);
                    }
                } else {  //上传的文件
                    String name = item.getName();
                    inputStream = new BufferedInputStream(item.getInputStream());
                    serverFile = new File(serverPath);
                    FileUtils.copyInputStreamToFile(inputStream, serverFile);
                }
            }

            //创建订单
            int userid = ((User) request.getSession().getAttribute("user")).getId();
            OutList outList = new OutList();
            outList.setBrandid(brandid);
            outList.setShopid(shopid);
            outList.setUserid(userid);
            outList.setDate(new Date());
            int listid = ServiceFactory.getOutListServiceInstance().insert(outList);
            if (listid != 0) {
                //执行导入
                int count = ImportOrderUtil.doImport(serverFile,listid);

                out.println("<html>");
                out.println("<body>");
                out.println("<h1>导入订单</h1>");
                out.println("<h3>导入: " + count + " 件</h3>");
                out.println("<a href='" + servletContext.getContextPath() + "/index.jsp'>Home</a>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception e) {
            System.out.println("*****");
            e.printStackTrace();
            inputStream.close();
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
