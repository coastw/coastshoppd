/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service.impl;

import com.coastshop.dao.IShopDAO;
import com.coastshop.dbc.DatabaseConnection;
import com.coastshop.factory.DAOFactory;
import com.coastshop.service.IShopService;
import com.coastshop.vo.Shop;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Coast
 */
public class ShopServiceImpl implements IShopService {

    @Override
    public boolean insert(Shop shop) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IShopDAO dao = DAOFactory.getShopDAOInstance(conn);
            boolean flag = false;
            if ( dao.getByName(shop.getName()) == null ) {    //不存在则创建
                flag = dao.doCreate(shop);
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
    public boolean update(Shop shop) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IShopDAO dao = DAOFactory.getShopDAOInstance(conn);
            boolean flag = false;
            if ( dao.getById(shop.getId()) != null ) {    
                flag = dao.doUpdate(shop);
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
            IShopDAO dao = DAOFactory.getShopDAOInstance(conn);
            boolean flag = false;
            if ( dao.getById(id) != null ) {    //不存在则
                flag = dao.doDelete(id);
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
    public Shop find(int id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IShopDAO dao = DAOFactory.getShopDAOInstance(conn);
            //TODO
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
    public Shop find(String name) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IShopDAO dao = DAOFactory.getShopDAOInstance(conn);
            //TODO
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
    public List<Shop> findLikeName(String keyword) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IShopDAO dao = DAOFactory.getShopDAOInstance(conn);
            //TODO
            List<Shop> list = dao.getAll(keyword);
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

    @Override
    public List<Shop> findAll() throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IShopDAO dao = DAOFactory.getShopDAOInstance(conn);
            //TODO
            List<Shop> list = dao.getAll();
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
