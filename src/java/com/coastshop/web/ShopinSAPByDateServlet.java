/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.util.SAPUtil;
import com.coastshop.util.SapWorkbook;
import com.coastshop.util.StoreOutProductInfo;
import com.coastshop.vo.OutList;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Coast
 */
public class ShopinSAPByDateServlet extends HttpServlet {

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
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        OutputStream os = null;
        InputStream is = null;
        try {
            String tmpfile = request.getServletContext().getRealPath("/") + "tmp.xlsx";
            os = response.getOutputStream();
            is = new FileInputStream(tmpfile);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(new Date());
            Date begindate = df.parse(request.getParameter("date1"));
            Date enddate = df.parse(request.getParameter("date2"));
            List<OutList> outlists = ServiceFactory.getOutListServiceInstance().getFromDate(begindate, enddate);
            List<StoreOutProductInfo> findSAPByLists = ServiceFactory.getStoreOutServiceInstance().findSAPByLists(outlists);
            SapWorkbook swb = SAPUtil.getWorkbook(findSAPByLists, request.getServletContext().getRealPath("/"));
            Workbook wb = swb.getWorkbook();
            //download
            String brand = "片断";
            String filename = URLEncoder.encode(brand + "_SAP_" + date + ".xlsx", "UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os!=null) {
                os.close();
            }
            if (is!=null) {
                is.close();
            }
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
