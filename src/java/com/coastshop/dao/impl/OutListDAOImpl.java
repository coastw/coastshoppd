/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao.impl;

import com.coastshop.dao.IOutListDAO;
import com.coastshop.vo.OutList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Coast
 */
public class OutListDAOImpl implements IOutListDAO {

    private Connection conn;
    private PreparedStatement pstmt;

    public OutListDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<OutList> getAll(String limitString) throws Exception {
        List<OutList> list = new ArrayList<OutList>();
        String sql = "SELECT `id`,`brandid`,`shopid`,`userid`,`date` FROM `tbstoreoutlist` ORDER BY `id` DESC " + limitString;
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            OutList outList = new OutList();
            outList.setId(rs.getInt(1));
            outList.setBrandid(rs.getInt(2));
            outList.setShopid(rs.getInt(3));
            outList.setUserid(rs.getInt(4));
            outList.setDate(rs.getDate(5));
            list.add(outList);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public int doCreate(OutList outList) throws Exception {
        int flag = 0;
        String sql = "INSERT INTO `tbstoreoutlist`(`brandid`,`shopid`,`userid`,`date`) VALUES (?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        this.pstmt.setInt(1, outList.getBrandid());
        this.pstmt.setInt(2, outList.getShopid());
        this.pstmt.setInt(3, outList.getUserid());
        this.pstmt.setDate(4, new java.sql.Date(new Date().getTime()));
        if (this.pstmt.executeUpdate() == 1) {
            ResultSet rs = this.pstmt.getGeneratedKeys();
            if (rs.next()) {
                flag = rs.getInt(1);
            }
            rs.close();
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public boolean doUpdate(OutList outList) throws Exception {
        boolean flag = false;
        String sql = "UPDATE `tbstoreoutlist` SET `brandid`=?,`shopid`=?,`userid`=?,`date`=? WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, outList.getBrandid());
        this.pstmt.setInt(2, outList.getShopid());
        this.pstmt.setInt(3, outList.getUserid());
        this.pstmt.setDate(4, new java.sql.Date(new Date().getTime()));
        this.pstmt.setInt(5, outList.getId());
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public boolean doDelete(int id) throws Exception {
        boolean flag = false;
        String sql = "DELETE FROM `tbstoreoutlist` WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public OutList getById(int id) throws Exception {
        OutList outList = null;
        String sql = "SELECT `id`,`brandid`,`shopid`,`userid`,`date` FROM `tbstoreoutlist` WHERE id=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            outList = new OutList();
            outList.setId(rs.getInt(1));
            outList.setBrandid(rs.getInt(2));
            outList.setShopid(rs.getInt(3));
            outList.setUserid(rs.getInt(4));
            outList.setDate(rs.getDate(5));
        }
        rs.close();
        this.pstmt.close();
        return outList;
    }

    @Override
    public List<OutList> getAll() throws Exception {
        List<OutList> list = new ArrayList<OutList>();
        String sql = "SELECT `id`,`brandid`,`shopid`,`userid`,`date` FROM `tbstoreoutlist` ORDER BY `id` DESC";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            OutList outList = new OutList();
            outList.setId(rs.getInt(1));
            outList.setBrandid(rs.getInt(2));
            outList.setShopid(rs.getInt(3));
            outList.setUserid(rs.getInt(4));
            outList.setDate(rs.getDate(5));
            list.add(outList);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public List<OutList> getByBrandId(int brandid) throws Exception {
        List<OutList> list = new ArrayList<OutList>();
        String sql = "SELECT `id`,`brandid`,`shopid`,`userid`,`date` FROM `tbstoreoutlist` WHERE brandid=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, brandid);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            OutList outList = new OutList();
            outList.setId(rs.getInt(1));
            outList.setBrandid(rs.getInt(2));
            outList.setShopid(rs.getInt(3));
            outList.setUserid(rs.getInt(4));
            outList.setDate(rs.getDate(5));
            list.add(outList);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public List<OutList> getByUserId(int userid) throws Exception {
        List<OutList> list = new ArrayList<OutList>();
        String sql = "SELECT `id`,`brandid`,`shopid`,`userid`,`date` FROM `tbstoreoutlist` WHERE userid=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, userid);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            OutList outList = new OutList();
            outList.setId(rs.getInt(1));
            outList.setBrandid(rs.getInt(2));
            outList.setShopid(rs.getInt(3));
            outList.setUserid(rs.getInt(4));
            outList.setDate(rs.getDate(5));
            list.add(outList);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public List<OutList> getByShopId(int shopid) throws Exception {
        List<OutList> list = new ArrayList<OutList>();
        String sql = "SELECT `id`,`brandid`,`shopid`,`userid`,`date` FROM `tbstoreoutlist` WHERE shopid=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, shopid);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            OutList outList = new OutList();
            outList.setId(rs.getInt(1));
            outList.setBrandid(rs.getInt(2));
            outList.setShopid(rs.getInt(3));
            outList.setUserid(rs.getInt(4));
            outList.setDate(rs.getDate(5));
            list.add(outList);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public List<OutList> getByShopId(int shopid, String limitString) throws Exception {
        List<OutList> list = new ArrayList<OutList>();
        String sql = "SELECT `id`,`brandid`,`shopid`,`userid`,`date` FROM `tbstoreoutlist` WHERE shopid=? ORDER BY `id` DESC " + limitString;
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, shopid);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            OutList outList = new OutList();
            outList.setId(rs.getInt(1));
            outList.setBrandid(rs.getInt(2));
            outList.setShopid(rs.getInt(3));
            outList.setUserid(rs.getInt(4));
            outList.setDate(rs.getDate(5));
            list.add(outList);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public List<OutList> getByDate(Date date) throws Exception {
        List<OutList> list = new ArrayList<OutList>();
        String sql = "SELECT `id`,`brandid`,`shopid`,`userid`,`date` FROM `tbstoreoutlist` WHERE `date`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setDate(1, new java.sql.Date(date.getTime()));
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            OutList outList = new OutList();
            outList.setId(rs.getInt(1));
            outList.setBrandid(rs.getInt(2));
            outList.setShopid(rs.getInt(3));
            outList.setUserid(rs.getInt(4));
            outList.setDate(rs.getDate(5));
            list.add(outList);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public List<OutList> getFromDateShopIDBrandId(Date begindate, Date enddate, int shopid, int brandid) throws Exception {
        String sql = "SELECT `id`,`brandid`,`shopid`,`userid`,`date` FROM `tbstoreoutlist` "
                + "WHERE `date` BETWEEN ? AND ? "
                + "AND `shopid`=? AND `brandid`=?";
        this.pstmt = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.pstmt.setDate(1, new java.sql.Date(begindate.getTime()));
        this.pstmt.setDate(2, new java.sql.Date(enddate.getTime()));
        this.pstmt.setInt(3, shopid);
        this.pstmt.setInt(4, brandid);
        ResultSet rs = this.pstmt.executeQuery();
        rs.last();
        ArrayList<OutList> list = new ArrayList<OutList>(rs.getRow());
        rs.beforeFirst();
        while (rs.next()) {
            OutList outList = new OutList();
            outList.setId(rs.getInt(1));
            outList.setBrandid(rs.getInt(2));
            outList.setShopid(rs.getInt(3));
            outList.setUserid(rs.getInt(4));
            outList.setDate(rs.getDate(5));
            list.add(outList);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public int getCount() throws Exception {
        int count = 0;
        String sql = "SELECT count(*) FROM `tbstoreoutlist`";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        this.pstmt.close();
        return count;
    }

    @Override
    public int getCountByShopId(int shopid) throws Exception {
        int count = 0;
        String sql = "SELECT count(*) FROM `tbstoreoutlist` WHERE `shopid`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, shopid);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        this.pstmt.close();
        return count;
    }

    @Override
    public OutList getLast() throws Exception {
        OutList outList = null;
        String sql = "SELECT `id`,`brandid`,`shopid`,`userid`,`date` FROM `tbstoreoutlist` ORDER BY `id` DESC LIMIT 1";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            outList = new OutList();
            outList.setId(rs.getInt(1));
            outList.setBrandid(rs.getInt(2));
            outList.setShopid(rs.getInt(3));
            outList.setUserid(rs.getInt(4));
            outList.setDate(rs.getDate(5));
        }
        rs.close();
        this.pstmt.close();
        return outList;
    }

    @Override
    public List<OutList> getFromDate(Date begindate, Date enddate) throws Exception {
        String sql = "SELECT `id`,`brandid`,`shopid`,`userid`,`date` FROM `tbstoreoutlist` "
                + "WHERE `date` BETWEEN ? AND ? "
                + "AND `brandid`=?";
        int brandid = 1;
        this.pstmt = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.pstmt.setDate(1, new java.sql.Date(begindate.getTime()));
        this.pstmt.setDate(2, new java.sql.Date(enddate.getTime()));
        this.pstmt.setInt(3, brandid);
        ResultSet rs = this.pstmt.executeQuery();
        rs.last();
        ArrayList<OutList> list = new ArrayList<OutList>(rs.getRow());
        rs.beforeFirst();
        while (rs.next()) {
            OutList outList = new OutList();
            outList.setId(rs.getInt(1));
            outList.setBrandid(rs.getInt(2));
            outList.setShopid(rs.getInt(3));
            outList.setUserid(rs.getInt(4));
            outList.setDate(rs.getDate(5));
            list.add(outList);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }
}
