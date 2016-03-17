/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao.impl;

import com.coastshop.dao.IShopinSAPDAO;
import com.coastshop.vo.ShopinSAP;
import java.sql.*;
import java.util.*;


public class ShopinSAPDAOImpl implements IShopinSAPDAO {
    private Connection conn;
    private PreparedStatement pstmt;

    public ShopinSAPDAOImpl(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public int doCreate(ShopinSAP shopinSAP) throws Exception {
        int id = 0;
        String sql = "INSERT INTO `shopinsap`(`outlistid`,`uploaddate`) VALUES (?,?)";
        this.pstmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        this.pstmt.setInt(1, shopinSAP.getOutlistid());
        this.pstmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
        if (this.pstmt.executeUpdate() == 1) {
            ResultSet rs = this.pstmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
        }
        this.pstmt.close();
        return id;
    }

    @Override
    public boolean doDelete(int id) throws Exception {
        boolean flag = false;
        String sql = "DELETE FROM `shopinsap` WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public boolean doUpdate(ShopinSAP shopinSAP) throws Exception {
        boolean flag = false;
        String sql = "UPDATE `shopinsap` SET `outlistid`=?, `uploaddate`=? WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, shopinSAP.getOutlistid());
        this.pstmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
        this.pstmt.setInt(3, shopinSAP.getId());
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public ShopinSAP getById(int id) throws Exception {
        ShopinSAP sap = null;
        String sql = "SELECT `outlistid`, `uploaddate` FROM `shopinsap` WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        if ( rs.next() ) {
            sap = new ShopinSAP();
            sap.setId(id);
            sap.setOutlistid(rs.getInt(1));
            sap.setUploadDate(rs.getDate(2));
        }
        rs.close();
        this.pstmt.close();
        return sap;
    }

    @Override
    public ShopinSAP getByOutListId(int outlistid) throws Exception {
        ShopinSAP sap = null;
        String sql = "SELECT `id`, `outlistid`, `uploaddate` FROM `shopinsap` WHERE `outlistid`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, outlistid);
        ResultSet rs = this.pstmt.executeQuery();
        if ( rs.next() ) {
            sap = new ShopinSAP();
            sap.setId(rs.getInt(1));
            sap.setOutlistid(rs.getInt(2));
            sap.setUploadDate(rs.getDate(3));
        }
        rs.close();
        this.pstmt.close();
        return sap;
    }

    @Override
    public List<ShopinSAP> getAll() throws Exception {
        List<ShopinSAP> list = new ArrayList<ShopinSAP>();
        String sql = "SELECT `id`, `outlistid`, `uploaddate` FROM `shopinsap`";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while(rs.next()){
            ShopinSAP sap = new ShopinSAP();
            sap.setId(rs.getInt(1));
            sap.setOutlistid(rs.getInt(2));
            sap.setUploadDate(rs.getDate(3));
            list.add(sap);
        }
        rs.close();
        this.pstmt.close();
        return list;
    }
    
}
