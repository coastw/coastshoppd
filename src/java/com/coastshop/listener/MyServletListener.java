/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coastshop.listener;

import com.coastshop.dbc.DatabaseConnection;
import com.coastshop.dbsys.DBSystem;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Coast
 */
@WebListener()
public class MyServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //System.out.println("ServletContext Listener Init");
        ServletContext sct = sce.getServletContext();
        //String path = sct.getContextPath(); // /CoastShop
        //String path = sct.getRealPath("/"); //C:\Users\Coast\Documents\NetBeansProjects\CoastShop\build\web\
        String path = sct.getRealPath("/");
        path = path + "dbsys" + File.separator;

        Date date = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        String filename = "dbbackup" + fm.format(date) + ".sql";
        Connection conn = null;
        try {
            long start = System.currentTimeMillis();
            conn = new DatabaseConnection().getConnection();
            DBSystem dbs = new DBSystem(conn);
            StringBuffer s = dbs.dumpAll();
            File file = new File(path + filename);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

            bf.write(s.toString());
            bf.close();
            //EMail
            //
            //EMail.send(file, filename);
            //print
//            long end = System.currentTimeMillis();
//            String time = Long.toString(end - start);
//            System.out.println("<h3>导出成功，用时: " + time + " 毫秒</h3>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
