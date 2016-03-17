/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service.impl;

import com.coastshop.dao.IProductDAO;
import com.coastshop.dbc.DatabaseConnection;
import com.coastshop.factory.DAOFactory;
import com.coastshop.service.IProductService;
import com.coastshop.util.PageSeperator;
import com.coastshop.vo.Product;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Coast
 */
public class ProductServiceImpl implements IProductService {

    @Override
    public boolean insert(Product product) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IProductDAO dao = DAOFactory.getProductDAOInstance(conn);
            boolean flag = false;
            if (dao.getBySn(product.getSn()) == null) {
                flag = dao.doCreate(product);
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
    public boolean update(Product product) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IProductDAO dao = DAOFactory.getProductDAOInstance(conn);
            boolean flag = false;
            if (dao.getById(product.getId()) != null) {
                flag = dao.doUpdate(product);
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
            IProductDAO dao = DAOFactory.getProductDAOInstance(conn);
            boolean flag = false;
            if (dao.getById(id) != null) {
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
    public Product find(int id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IProductDAO dao = DAOFactory.getProductDAOInstance(conn);
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
    public Product find(String sn) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IProductDAO dao = DAOFactory.getProductDAOInstance(conn);
            return dao.getBySn(sn);
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public List<Product> findLikeSn(String keyword) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IProductDAO dao = DAOFactory.getProductDAOInstance(conn);
            return dao.getAll(keyword);
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public List<Product> findLikeSn(String keyword, PageSeperator pageSeperator) throws Exception {
        Connection conn = null;
        try {
            
            conn = DatabaseConnection.getConnection();
            IProductDAO dao = DAOFactory.getProductDAOInstance(conn);
            return dao.getAll(keyword, pageSeperator);
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public int getCount(String keyword) throws Exception{
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IProductDAO dao = DAOFactory.getProductDAOInstance(conn);
            return dao.getCount(keyword);
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
}