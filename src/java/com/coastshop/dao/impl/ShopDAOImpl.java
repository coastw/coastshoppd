/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao.impl;

import com.coastshop.dao.IShopDAO;
import com.coastshop.vo.Shop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Coast
 */
public class ShopDAOImpl implements IShopDAO {

    private Connection conn;
    private PreparedStatement pstmt;

    public ShopDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean doCreate(Shop shop) throws Exception {
        boolean flag = false;
        String sql = "INSERT INTO `tbshop` (name) VALUES (?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, shop.getName());
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public boolean doUpdate(Shop shop) throws Exception {
        boolean flag = false;
        String sql = "Update `tbshop` SET `name`=? WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, shop.getName());
        this.pstmt.setInt(2, shop.getId());
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public boolean doDelete(int id) throws Exception {
        boolean flag = false;
        String sql = "DELETE FROM `tbshop` WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public Shop getById(int id) throws Exception {
        Shop shop = null;
        String sql = "SELECT `id`,`name` FROM `tbshop` WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            shop = new Shop();
            shop.setId(rs.getInt(1));
            shop.setName(rs.getString(2));
        }
        rs.close();
        this.pstmt.close();
        return shop;
    }

    @Override
    public Shop getByName(String name) throws Exception {
        Shop shop = null;
        String sql = "SELECT `id`,`name` FROM `tbshop` WHERE `name`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, name);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            shop = new Shop();
            shop.setId(rs.getInt(1));
            shop.setName(rs.getString(2));
        }
        rs.close();
        this.pstmt.close();
        return shop;
    }

    @Override
    public List<Shop> getAll() throws Exception {
        List<Shop> list = new ArrayList<Shop>();
        String sql = "SELECT `id`,`name` FROM `tbshop` ORDER BY `id`";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            Shop shop = new Shop();
            shop.setId(rs.getInt(1));
            shop.setName(rs.getString(2));
            list.add(shop);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public List<Shop> getAll(String keyword) throws Exception {
        List<Shop> list = new ArrayList<Shop>();
        String sql = "SELECT `id`,`name` FROM `tbshop` WHERE `name` LIKE ? ORDER BY `id`";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, "%" + keyword + "%");
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            Shop shop = new Shop();
            shop.setId(rs.getInt(1));
            shop.setName(rs.getString(2));
            list.add(shop);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public int getCount() throws Exception {
        int count = 0;
        String sql = "SELECT count(*) FROM `tbshop`";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        this.pstmt.close();
        return count;
    }
}
