/*
 * 最传统的方法连接数据库
 */
package com.coastshop.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Coast
 */
public class DatabaseConnection1 {

    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/dbcompanypd?rewriteBatchedStatements=true";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "coast0414";
    private Connection conn;

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public DatabaseConnection1() throws Exception {
        Class.forName(DBDRIVER);
        this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void close() throws Exception {
        if (this.conn != null) {
            this.conn.close();
        }
    }
}
