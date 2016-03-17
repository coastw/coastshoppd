/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao.impl;

import com.coastshop.dao.IProductDAO;
import com.coastshop.util.PageSeperator;
import com.coastshop.vo.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Coast
 */
public class ProductDAOImpl implements IProductDAO {

    private Connection conn;
    private PreparedStatement pstmt;

    public ProductDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean doCreate(Product product) throws Exception {
        boolean flag = false;
        String sql = "INSERT INTO `tbproductqs` (sn,price) VALUES (?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, product.getSn());
        this.pstmt.setInt(2, product.getPrice());
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public boolean doUpdate(Product product) throws Exception {
        boolean flag = false;
        String sql = "Update `tbproductqs` SET sn=?, price=? WHERE id=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, product.getSn());
        this.pstmt.setInt(2, product.getPrice());
        this.pstmt.setInt(3, product.getId());
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public boolean doDelete(int id) throws Exception {
        boolean flag = false;
        String sql = "DELETE FROM `tbproductqs` WHERE id=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public Product getById(int id) throws Exception {
        Product p = null;
        String sql = "SELECT id,sn,price,brandid,org_price FROM `tbproductqs` WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            p = new Product();
            p.setId(rs.getInt(1));
            p.setSn(rs.getString(2));
            p.setPrice(rs.getInt(3));
            p.setBrandid(rs.getInt(4));
            p.setOrg_price(rs.getInt(5));
        }
        rs.close();
        this.pstmt.close();
        return p;
    }

    @Override
    public Product getBySn(String sn) throws Exception {
        Product p = null;
        String sql = "SELECT id,sn,price,brandid,org_price FROM `tbproductqs` WHERE `sn`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, sn);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            p = new Product();
            p.setId(rs.getInt(1));
            p.setSn(rs.getString(2));
            p.setPrice(rs.getInt(3));
            p.setBrandid(rs.getInt(4));
            p.setOrg_price(rs.getInt(5));
        }
        rs.close();
        this.pstmt.close();
        return p;
    }

    @Override
    public List<Product> getAll() throws Exception {
        String sql = "SELECT id,sn,price,brandid,org_price FROM `tbproductqs`";
        this.pstmt = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = this.pstmt.executeQuery();
        rs.last();
        ArrayList<Product> list = new ArrayList<Product>(rs.getRow());
        rs.beforeFirst();
        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt(1));
            p.setSn(rs.getString(2));
            p.setPrice(rs.getInt(3));
            p.setBrandid(rs.getInt(4));
            p.setOrg_price(rs.getInt(5));
            list.add(p);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public List<Product> getAll(String keyword) throws Exception {
        String sql = "SELECT id,sn,price,brandid,org_price FROM `tbproductqs` WHERE `sn` LIKE ? ORDER BY `sn` ASC";
        this.pstmt = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.pstmt.setString(1, "%" + keyword + "%");
        ResultSet rs = this.pstmt.executeQuery();
        rs.last();
        ArrayList<Product> list = new ArrayList<Product>(rs.getRow());
        rs.beforeFirst();
        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt(1));
            p.setSn(rs.getString(2));
            p.setPrice(rs.getInt(3));
            p.setBrandid(rs.getInt(4));
            p.setOrg_price(rs.getInt(5));
            list.add(p);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }
    
    @Override
    public List<Product> getAll(String keyword, PageSeperator pageSeperator) throws Exception {
        String pageLimitString = pageSeperator.getLimitparam();
        String sql = "SELECT id,sn,price,brandid,org_price FROM `tbproductqs` WHERE `sn` LIKE ? ORDER BY `id` DESC " + pageLimitString;
        this.pstmt = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.pstmt.setString(1, "%" + keyword + "%");
        ResultSet rs = this.pstmt.executeQuery();
        rs.last();
        ArrayList<Product> list = new ArrayList<Product>(rs.getRow());
        rs.beforeFirst();
        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt(1));
            p.setSn(rs.getString(2));
            p.setPrice(rs.getInt(3));
            p.setBrandid(rs.getInt(4));
            p.setOrg_price(rs.getInt(5));
            list.add(p);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }

    @Override
    public int getCount(String column, String keyword) throws Exception {
        int i = 0;
        String sql = "SELECT count(" + column + ") FROM tbproductqs WHERE sn LIKE ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, "%" + keyword + "%");
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            i = rs.getInt(1);
        }
        rs.close();
        this.pstmt.close();
        return i;
    }

    @Override
    public int getCount(String keyword) throws Exception {
        int i = 0;
        String sql = "SELECT count(*) FROM tbproductqs WHERE sn LIKE ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, "%" + keyword + "%");
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            i = rs.getInt(1);
        }
        rs.close();
        this.pstmt.close();
        return i;
    }
}
