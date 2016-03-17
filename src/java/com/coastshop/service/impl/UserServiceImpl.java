/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service.impl;

import com.coastshop.dao.IUserDAO;
import com.coastshop.dbc.DatabaseConnection;
import com.coastshop.factory.DAOFactory;
import com.coastshop.service.IUserService;
import com.coastshop.vo.User;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Coast
 */
public class UserServiceImpl implements IUserService {

    @Override
    public boolean insert(User user) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IUserDAO dao = DAOFactory.getUserDAOInstance(conn);
            boolean flag = false;
            //TODO
            if (dao.getByName(user.getName()) == null) {
                flag = dao.doCreate(user);
            }
            return flag;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public boolean update(User user) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IUserDAO dao = DAOFactory.getUserDAOInstance(conn);
            boolean flag = false;
            //TODO
            if (dao.getById(user.getId()) != null) {
                return dao.doUpdate(user);
            }
            return flag;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IUserDAO dao = DAOFactory.getUserDAOInstance(conn);
            boolean flag = false;
            //TODO
            if (dao.getById(id) != null) {
                return dao.doDelete(id);
            }
            return flag;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public User find(int id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IUserDAO dao = DAOFactory.getUserDAOInstance(conn);
            return dao.getById(id);
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public User find(String name) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IUserDAO dao = DAOFactory.getUserDAOInstance(conn);
            return dao.getByName(name);
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public List<User> findLikeName(String name) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IUserDAO dao = DAOFactory.getUserDAOInstance(conn);
            List<User> list = dao.getAll(name);
            if (list.isEmpty()){
                return null;
            }else{
                return list;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
