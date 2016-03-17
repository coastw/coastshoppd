/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.dbc.DatabaseConnection;
import com.coastshop.dbsys.DBSystem;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Coast
 */
public class DBBackupServlet extends HttpServlet {

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

        ServletContext sct = request.getServletContext();
        //String path = sct.getContextPath(); // /CoastShop
        //String path = sct.getRealPath("/"); //C:\Users\Coast\Documents\NetBeansProjects\CoastShop\build\web\
        String path = sct.getRealPath("/");
        path = path + "dbsys" + File.separator;

        Date date = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        String filename = "pd_dbbackup_" + fm.format(date) + ".sql";
        Connection conn = null;
        try {
            long start = System.currentTimeMillis();
            conn = DatabaseConnection.getConnection();
            DBSystem dbs = new DBSystem(conn);
            StringBuffer s = dbs.dumpAll();
//            conn.close();
            //out.println(path+filename);
            File file = new File(path + filename);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

            bf.write(s.toString());
            bf.close();
            //EMail
            //EMail.send(file, filename);
            //print
            long end = System.currentTimeMillis();
            String time = Long.toString(end - start);
            out.println("<html>");
            out.println("<body>");
            out.println("<h3>导出成功，用时: " + time + " 毫秒</h3>");
            out.println("<a href='" + filename + "'>");
            out.println(filename + "</a>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
