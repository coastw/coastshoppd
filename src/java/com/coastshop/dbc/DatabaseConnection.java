/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 通过读取配置文件访问数据库
 *
 * @author Coast
 */
public class DatabaseConnection {

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(DatabaseConnection.class.getClassLoader().getResourceAsStream("dbc.properties"));
            Class.forName(properties.getProperty("driver"));
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    

    public static Connection getConnection() throws SQLException{
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("pwd"));
        } catch (SQLException e) {
            throw e;
        }
        return conn;
    }

}
