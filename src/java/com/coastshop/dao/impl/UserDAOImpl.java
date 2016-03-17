/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao.impl;

import com.coastshop.dao.*;
import com.coastshop.vo.User;
import java.util.*;
import java.sql.*;

/**
 *
 * @author Coast
 */
public class UserDAOImpl implements IUserDAO {

    private Connection conn;
    private PreparedStatement pstmt;

    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean doCreate(User user) throws Exception {
        boolean flag = false;
        String sql = "INSERT INTO `tbuser`(`name`,`password`,`privilege`) VALUES(?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        //this.pstmt.setInt(1,user.getId());
        this.pstmt.setString(1, user.getName());
        this.pstmt.setString(2, user.getPassword());
        this.pstmt.setString(3, user.getPrivilege());
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public boolean doDelete(int id) throws Exception {
        boolean flag = false;
        String sql = "DELETE FROM `tbuser` WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public boolean doUpdate(User user) throws Exception {
        boolean flag = false;
        String sql = "UPDATE `tbuser` SET `name`=?,`password`=?,`privilege`=? WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, user.getName());
        this.pstmt.setString(2, user.getPassword());
        this.pstmt.setString(3, user.getPrivilege());
        this.pstmt.setInt(4, user.getId());
        if (this.pstmt.executeUpdate() == 1) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public User getById(int id) throws Exception {
        User user = null;
        String sql = "SELECT `id`,`name`,`password`,`privilege` FROM `tbuser` WHERE `id`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setPrivilege(rs.getString(4));
            return user;
        }
        rs.close();
        this.pstmt.close();
        return user;
    }

    @Override
    public User getByName(String name) throws Exception {
        User user = null;
        String sql = "SELECT `id`,`name`,`password`,`privilege` FROM `tbuser` WHERE `name`=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, name);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setPrivilege(rs.getString(4));
            return user;
        }
        rs.close();
        this.pstmt.close();
        return user;
    }

    @Override
    public List<User> getAll() throws Exception {
        List<User> all = new ArrayList<User>();
        String sql = "SELECT `id`,`name`,`password`,`privilege` FROM `tbuser`";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setPrivilege(rs.getString(4));
            all.add(user);
        }
        rs.close();
        this.pstmt.close();
        return all;
    }

    @Override
    public List<User> getAll(String keyword) throws Exception {
        List<User> all = new ArrayList<User>();
        String sql = "SELECT `id`,`name`,`password`,`privilege` FROM `tbuser` WHERE `name` LIKE ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, "%" + keyword + "%");
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setPrivilege(rs.getString(4));
            all.add(user);
        }
        rs.close();
        this.pstmt.close();
        return all;
    }

    @Override
    public int getCount() throws Exception {
        int count = 0;
        String sql = "SELECT count(*) FROM `tbuser`";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }
}
