/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao.impl;

import com.coastshop.dao.IBrandDAO;
import com.coastshop.vo.Brand;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Coast
 */
public class BrandDAOImpl implements IBrandDAO {

    private Connection conn;
    private PreparedStatement pstmt;

    public BrandDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean doCreate(Brand brand) throws Exception {
        boolean flag = false;
        String sql = "INSERT INTO `tbbrand`(`name`) VALUES (?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, brand.getName());
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public boolean doUpdate(Brand brand) throws Exception {
        boolean flag = false;
        String sql = "UPDATE `tbbrand` SET `name`=? WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, brand.getName());
        this.pstmt.setInt(2, brand.getId());
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public boolean doDelete(int id) throws Exception {
        boolean flag = false;
        String sql = "DELETE FROM `tbbrand` WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public Brand getById(int id) throws Exception {
        Brand brand = null;
        String sql = "SELECT `id`,`name` FROM `tbbrand` WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            brand = new Brand();
            brand.setId(rs.getInt(1));
            brand.setName(rs.getString(2));
        }
        rs.close();
        this.pstmt.close();
        return brand;
    }

    @Override
    public Brand getByName(String name) throws Exception {
        Brand brand = null;
        String sql = "SELECT `id`,`name` FROM `tbbrand` WHERE `name`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, name);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            brand = new Brand();
            brand.setId(rs.getInt(1));
            brand.setName(rs.getString(2));
        }
        rs.close();
        this.pstmt.close();
        return brand;
    }

    @Override
    public List<Brand> getAll() throws Exception {
        List<Brand> list = new ArrayList<Brand>();
        String sql = "SELECT `id`,`name` FROM `tbbrand` ORDER BY `id`";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            Brand brand = new Brand();
            brand.setId(rs.getInt(1));
            brand.setName(rs.getString(2));
            list.add(brand);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public List<Brand> getAll(String keyword) throws Exception {
         List<Brand> list = new ArrayList<Brand>();
        String sql = "SELECT `id`,`name` FROM `tbbrand` WHERE `name` LIKE ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, "%" + keyword + "%");
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            Brand brand = new Brand();
            brand.setId(rs.getInt(1));
            brand.setName(rs.getString(2));
            list.add(brand);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public int getCount() throws Exception {
        int count = 0;
        String sql = "SELECT count(*) FROM `tbbrand`";
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
