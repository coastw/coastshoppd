/*
 * storeoutlist.do
 * 上品SAP 按鈕
 */
package com.coastshop.web;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.util.SAPUtil;
import com.coastshop.util.SapWorkbook;
import com.coastshop.util.StoreOutProductInfo;
import com.coastshop.util.StoreOutProductInfoComparator;
import com.coastshop.vo.OutList;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collections;
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
public class SoToSapServlet extends HttpServlet {

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
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        OutputStream os = response.getOutputStream();
        String tmpfile = request.getServletContext().getRealPath("/") + "tmp.xlsx";
        InputStream is = new FileInputStream(tmpfile);
        try {
            if (request.getParameter("listid") == null) {
                return;
            }
            //param
            int listid = Integer.parseInt(request.getParameter("listid"));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            
            
            OutList outlist = ServiceFactory.getOutListServiceInstance().getById(listid);
            String date = df.format( outlist.getDate() );//todo
            
            String shop = ServiceFactory.getShopServiceInstance().find(outlist.getShopid()).getName();
            String brand = ServiceFactory.getBrandServiceInstance().find(outlist.getBrandid()).getName();
            List<StoreOutProductInfo> soiflist = ServiceFactory.getStoreOutServiceInstance().findProductInfosByList(listid);
            Collections.sort(soiflist, new StoreOutProductInfoComparator());
            //POI
            SapWorkbook swb = SAPUtil.getWorkbook(soiflist, request.getServletContext().getRealPath("/"));
            Workbook wb = swb.getWorkbook();
            //download
            String filename = URLEncoder.encode(shop + "_" + brand + "_SAP_" + date + ".xlsx", "UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            wb.write(os);
            //end try{}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
            is.close();
            Runtime.getRuntime().gc();
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
