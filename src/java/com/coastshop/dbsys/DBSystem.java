/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dbsys;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Coast
 */
public class DBSystem {

    private Connection conn;
    private PreparedStatement pstmt;
    private Statement stmt;

    public DBSystem(Connection conn) {
        this.conn = conn;
    }

    DBSystem() {
    }

    //获取表结构
    public List<String> getTables() throws Exception {

        List<String> tables = new ArrayList<String>();

        String sql = "show tables";

        this.stmt = this.conn.createStatement();
        ResultSet rs = this.stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                tables.add(rs.getString(1));
            }
        }
        this.stmt.close();
        return tables;
    }

    public String dumpStruct(String table) throws Exception {
        String tbStruct;
        String firstLine = "DROP TABLE IF EXISTS `" + table + "`;\n";
        String sql = "SHOW CREATE TABLE `" + table + "`;";
        this.stmt = this.conn.createStatement();
        ResultSet rs = this.stmt.executeQuery(sql);
        String rsString = "";
        if (rs != null) {
            while (rs.next()) {
//                rsString += rs.getString(1);
                rsString += rs.getString(2);
            }
        }
        tbStruct = firstLine + rsString + ";\n\n";
        this.stmt.close();
        return tbStruct;
    }

    public Boolean hasData(String tableName) throws Exception {
        boolean flag = false;
        int count = 0;
        String sql = "SELECT COUNT(*) FROM `" + tableName + "`";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        if (count != 0) {
            flag = true;
        }
        return flag;
    }

    public StringBuffer dumpData(String table) throws Exception {
        StringBuffer sb = new StringBuffer();
        String sql = "SELECT * FROM `" + table + "` order by id asc";
        this.stmt = this.conn.createStatement();
        ResultSet rs = this.stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int numcols = rsmd.getColumnCount();
        String insertStr = "INSERT INTO `" + table + "` VALUES ";
        sb.append(insertStr);
        while (rs.next()) {
            //VALUES (1,sd,12),(2,dsa,32)
            String valStr = null;

            for (int i = 1; i <= numcols; i++) {
                if (i == 1) {  //第一个参数
                    valStr = "('" + rs.getString(i) + "',";
                } else if (i == numcols) { //最后一个参数
                    if (rs.isLast()) { // boolean isLast()  获取光标是否位于此 ResultSet 对象的最后一行。
                        valStr += "'" + rs.getString(i) + "');\n";
                    } else {
                        valStr += "'" + rs.getString(i) + "'),";
                    }
                } else {
                    valStr += "'" + rs.getString(i) + "',";
                }
            }
            sb.append(valStr);
        }
        rs.close();
        this.stmt.close();
        return sb;
    }

    /**
     * Dump all table struct an data, with default database
     *
     * @return StringBuffer
     * @throws Exception
     */
    public StringBuffer dumpAll() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> tables = this.getTables();
        Iterator<String> iter = tables.iterator();
        while (iter.hasNext()) {
            sb.append(this.dumpStruct(iter.next()));
        }

        //data
        tables = this.getTables();
        iter = tables.iterator();
        while (iter.hasNext()) {
            String table = iter.next();
            if (hasData(table)) {
                String lock = "LOCK TABLES `" + table + "` WRITE;\n";
                sb.append(lock);
                sb.append(this.dumpData(table));
                sb.append("UNLOCK TABLES;\n");
                sb.append("\n");
            }
        }
        return sb;
    }

    /**
     * same as no parameter version, but create a new database with newDBname
     *
     * @return StringBuffer
     * @param newDBName String
     * @throws Exception
     */
    public StringBuffer dumpAll(String newDBName) throws Exception {
        StringBuffer sb = new StringBuffer();
        String dbStr = "DROP DATABASE IF EXISTS `" + newDBName + "`;\n";
        sb.append(dbStr);
        dbStr = "CREATE DATABASE `" + newDBName + "`;\n\n";
        sb.append(dbStr);
        dbStr = "USE `" + newDBName + "`;\n\n";
        sb.append(dbStr);
        //struct
        List<String> tables = this.getTables();
        Iterator<String> iter = tables.iterator();
        while (iter.hasNext()) {
            sb.append(this.dumpStruct(iter.next()));
        }

        //data
        tables = this.getTables();
        iter = tables.iterator();
        while (iter.hasNext()) {
            String table = iter.next();
            String lock = "LOCK TABLES `" + table + "` WRITE;\n";
            sb.append(lock);
            sb.append(this.dumpData(table));
            sb.append("UNLOCK TABLES;\n");
            sb.append("\n");
        }
        return sb;
    }

    public void restoreDB(String filename) {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(filename), "UTF-8");
            scan.useDelimiter(";");
            this.stmt = this.conn.createStatement();
            String line;
            long start = System.currentTimeMillis();
            while (scan.hasNext()) {
                line = scan.next().trim();
                if (!line.isEmpty()) {
                    this.stmt.addBatch(line);
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("读取文件用时：" + (end - start) + "毫秒");

            long startBatch = System.currentTimeMillis();
            this.conn.setAutoCommit(false);
            this.stmt.executeBatch();
            this.conn.commit();
            long endBatch = System.currentTimeMillis();
            System.out.println("还原数据库用时：" + (endBatch - startBatch) + "毫秒");
        } catch (Exception e) {
            try {
                this.conn.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        } finally {
            if (scan != null) {
                scan.close();
            }
            try {
                this.conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public void restoreDB(File file) {

        Scanner scan = null;
        try {
            scan = new Scanner(file, "UFT-8");
            scan.useDelimiter(";");
            this.stmt = this.conn.createStatement();
            String line;
            long start = System.currentTimeMillis();
            while (scan.hasNext()) {
                line = scan.next().trim();
                if (!line.isEmpty()) {
                    this.stmt.addBatch(line);
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("读取文件用时：" + (end - start) + "毫秒");

            long startBatch = System.currentTimeMillis();
            this.conn.setAutoCommit(false);
            this.stmt.executeBatch();
            this.conn.commit();
            long endBatch = System.currentTimeMillis();
            System.out.println("还原数据库用时：" + (endBatch - startBatch) + "毫秒");
        } catch (Exception e) {
            try {
                this.conn.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        } finally {
            if (scan != null) {
                scan.close();
            }
            try {
                this.conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
