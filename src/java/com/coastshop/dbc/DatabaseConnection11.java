/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 通过读取配置文件访问数据库
 *
 * @author Coast
 */
public class DatabaseConnection11 {

    private static final Properties properties = new Properties();

    static {
        InputStream fis = DatabaseConnection.class.getClassLoader().getResourceAsStream("dbc.properties");
        try {
            properties.load(fis);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fis.close();
        } catch (IOException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn= DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("pwd"));
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

   
}
