/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao.impl;

import com.coastshop.dao.IStoreOutDAO;
import com.coastshop.vo.StoreOut;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StoreOutDAOImpl implements IStoreOutDAO {

    private Connection conn;
    private PreparedStatement pstmt;

    public StoreOutDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int doCreate(StoreOut so) throws Exception {
        int key = 0;
        String sql = "INSERT INTO `tbstoreout`(`sn`,`color`,`size`,`amount`,`outlistid`) VALUES(?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        this.pstmt.setString(1, so.getSn());
        this.pstmt.setString(2, so.getColor());
        this.pstmt.setString(3, so.getSize());
        this.pstmt.setInt(4, so.getAmount());
        this.pstmt.setInt(5, so.getOutlistid());
        if (this.pstmt.executeUpdate() == 1) {
            ResultSet rs = this.pstmt.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
            }
            rs.close();
        }
        this.pstmt.close();
        return key;
    }

    @Override
    public int doUpdate(StoreOut so) throws Exception {
        int key = 0;
        String sql = "UPDATE`tbstoreout` SET `sn`=?, `color`=?,`size`=?,`amount`=?,`outlistid`=? WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, so.getSn());
        this.pstmt.setString(2, so.getColor());
        this.pstmt.setString(3, so.getSize());
        this.pstmt.setInt(4, so.getAmount());
        this.pstmt.setInt(5, so.getOutlistid());
        this.pstmt.setInt(6, so.getId());
        if (this.pstmt.executeUpdate() == 1) {
            key = so.getId();
        }
        this.pstmt.close();
        return key;
    }

    @Override
    public int doDelete(int id) throws Exception {
        int key = 0;
        String sql = "DELETE FROM `tbstoreout` WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        if (this.pstmt.executeUpdate() == 1) {
            key = id;
        }
        this.pstmt.close();
        return key;
    }

    @Override
    public StoreOut getById(int id) throws Exception {
        StoreOut so = null;
        String sql = "SELECT `id`,`sn`,`color`,`size`,`amount`,`outlistid` FROM `tbstoreout` WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            so = new StoreOut();
            so.setId(rs.getInt(1));
            so.setSn(rs.getString(2));
            so.setColor(rs.getString(3));
            so.setSize(rs.getString(4));
            so.setAmount(rs.getInt(5));
            so.setOutlistid(rs.getInt(6));
        }
        rs.close();
        this.pstmt.close();
        return so;
    }

    /**
     * TODO 试用ArrayList
     *
     * @param listid
     * @return
     * @throws Exception
     */
    @Override
    public List<StoreOut> getByListId(int listid) throws Exception {
        String sql = "SELECT `id`,`sn`,`color`,`size`,`amount`,`outlistid` FROM `tbstoreout` WHERE `outlistid`=?";
        this.pstmt = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.pstmt.setInt(1, listid);
        ResultSet rs = this.pstmt.executeQuery();
        rs.last();
        ArrayList<StoreOut> list = new ArrayList<StoreOut>(rs.getRow());
        rs.beforeFirst();
        while (rs.next()) {
            StoreOut so = new StoreOut();
            so.setId(rs.getInt(1));
            so.setSn(rs.getString(2));
            so.setColor(rs.getString(3));
            so.setSize(rs.getString(4));
            so.setAmount(rs.getInt(5));
            so.setOutlistid(rs.getInt(6));
            list.add(so);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    /**
     * TODO 试用ArrayList
     * 根据order排序结果
     * @param listid
     * @param order
     * @return
     * @throws Exception
     */
    @Override
    public List<StoreOut> getByListId(int listid, String order) throws Exception {
        String sql = "SELECT `id`,`sn`,`color`,`size`,`amount`,`outlistid` FROM `tbstoreout` WHERE `outlistid`=? " + order;
        this.pstmt = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.pstmt.setInt(1, listid);
        ResultSet rs = this.pstmt.executeQuery();
        rs.last();
        ArrayList<StoreOut> list = new ArrayList<StoreOut>(rs.getRow());
        rs.beforeFirst();
        while (rs.next()) {
            StoreOut so = new StoreOut();
            so.setId(rs.getInt(1));
            so.setSn(rs.getString(2));
            so.setColor(rs.getString(3));
            so.setSize(rs.getString(4));
            so.setAmount(rs.getInt(5));
            so.setOutlistid(rs.getInt(6));
            list.add(so);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }
    
    @Override
    public List<StoreOut> getAll() throws Exception {
        String sql = "SELECT `id`,`sn`,`color`,`size`,`amount`,`outlistid` FROM `tbstoreout` ORDER BY `id` ASC";
        this.pstmt = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = this.pstmt.executeQuery();
        rs.last();
        ArrayList<StoreOut> list = new ArrayList<StoreOut>(rs.getRow());
        rs.beforeFirst();
        while (rs.next()) {
            StoreOut so = new StoreOut();
            so.setId(rs.getInt(1));
            so.setSn(rs.getString(2));
            so.setColor(rs.getString(3));
            so.setSize(rs.getString(4));
            so.setAmount(rs.getInt(5));
            so.setOutlistid(rs.getInt(6));
            list.add(so);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public int getCount() throws Exception {
        int count = 0;
        String sql = "SELECT count(*) FROM `tbstoreout`";
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
    public StoreOut getBySnColorSizeList(String sn, String color, String size, int listid) throws Exception {
        StoreOut so = null;
        String sql = "SELECT `id`,`sn`,`color`,`size`,`amount`,`outlistid` "
                + "FROM `tbstoreout` "
                + "WHERE `sn`=? AND `color`=? AND `size`=? AND `outlistid`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, sn);
        this.pstmt.setString(2, color);
        this.pstmt.setString(3, size);
        this.pstmt.setInt(4, listid);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            so = new StoreOut();
            so.setId(rs.getInt(1));
            so.setSn(rs.getString(2));
            so.setColor(rs.getString(3));
            so.setSize(rs.getString(4));
            so.setAmount(rs.getInt(5));
            so.setOutlistid(rs.getInt(6));
        }
        rs.close();
        this.pstmt.close();
        return so;
    }

    @Override
    public int getSumAmount(int outlistid) throws Exception {
        int count = 0;
        String sql = "SELECT SUM(amount) FROM `tbstoreout` WHERE `outlistid`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, outlistid);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        this.pstmt.close();
        return count;
    }
}
