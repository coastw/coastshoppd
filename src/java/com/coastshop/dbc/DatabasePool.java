/*
 * 通过数据库连接池访问数据库
 */
package com.coastshop.dbc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Coast
 */
public class DatabasePool {
    private static final String DSNAME = "java:comp/env/jdbc/dbcompanypd";
    private Connection conn;

    public DatabasePool() {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup(DSNAME);
            this.conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void close() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
