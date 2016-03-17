/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao.impl;

import com.coastshop.dao.IDiscountDAO;
import com.coastshop.vo.Discount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Coast
 */
public class DiscountDAOImpl implements IDiscountDAO {

    private Connection conn;

    public DiscountDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int add(Discount discount) throws Exception {
        int id = 0;
        String sql = "INSERT INTO discount(categorynum,categorychar,regex,persent) VALUES(?,?,?,?)";
        PreparedStatement pstmt = this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, discount.getCategorynum());
        pstmt.setString(2, discount.getCategorychar());
        pstmt.setString(3, discount.getRegex());
        pstmt.setString(4, discount.getPersent());
        if (pstmt.executeUpdate() == 1) {
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
        }
        pstmt.close();
        return id;
    }

    @Override
    public int delete(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Discount discount) throws Exception {
        int id = 0;
        String sql = "UPDATE discount SET categorynum=?, categorychar=?, regex=?, persent=? WHERE id=?";
        PreparedStatement pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, discount.getCategorynum());
        pstmt.setString(2, discount.getCategorychar());
        pstmt.setString(3, discount.getRegex());
        pstmt.setString(4, discount.getPersent());
        pstmt.setInt(5, discount.getId());
        if (pstmt.executeUpdate() == 1) {
            id = discount.getId();
        }
        pstmt.close();
        return id;
    }

    @Override
    public Discount getById(int id) throws Exception {
        Discount d = null;
        String sql = "SELECT id, categorynum, categorychar, regex, persent FROM discount WHERE id=?";
        PreparedStatement pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            d = new Discount();
            d.setId(rs.getInt(1));
            d.setCategorynum(rs.getString(2));
            d.setCategorychar(rs.getString(3));
            d.setRegex(rs.getString(4));
            d.setPersent(rs.getString(5));
        }
        rs.close();
        pstmt.close();
        return d;
    }

    @Override
    public List<Discount> getAll() throws Exception {
        String sql = "SELECT id, categorynum, categorychar, regex, persent FROM discount";
        PreparedStatement pstmt = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = pstmt.executeQuery();
        rs.last();
        ArrayList<Discount> list = new ArrayList<Discount>(rs.getRow());
        rs.beforeFirst();
        while (rs.next()) {
            Discount d = new Discount();
            d.setId(rs.getInt(1));
            d.setCategorynum(rs.getString(2));
            d.setCategorychar(rs.getString(3));
            d.setRegex(rs.getString(4));
            d.setPersent(rs.getString(5));
            list.add(d);
        }
        rs.close();
        pstmt.close();
        return list;
    }

}
