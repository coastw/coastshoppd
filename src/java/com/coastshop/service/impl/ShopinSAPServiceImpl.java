/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service.impl;

import com.coastshop.dao.IShopinSAPDAO;
import com.coastshop.dbc.DatabaseConnection;
import com.coastshop.factory.DAOFactory;
import com.coastshop.service.IShopinSAPService;
import com.coastshop.vo.ShopinSAP;
import java.sql.Connection;
import java.util.List;

public class ShopinSAPServiceImpl implements IShopinSAPService {

    @Override
    public int insert(ShopinSAP shopinSAP) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IShopinSAPDAO dao = DAOFactory.getShopinSAPDAOInstance(conn);
            ShopinSAP existSAP = dao.getByOutListId(shopinSAP.getOutlistid());
            if (existSAP != null) {
                return existSAP.getId();
            } else {
                return dao.doCreate(shopinSAP);
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
    public boolean delete(int id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IShopinSAPDAO dao = DAOFactory.getShopinSAPDAOInstance(conn);
            if (dao.getById(id) != null) {
                return dao.doDelete(id);
            } else {
                return false;
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
    public boolean update(ShopinSAP shopinSAP) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IShopinSAPDAO dao = DAOFactory.getShopinSAPDAOInstance(conn);
            if (dao.getById(shopinSAP.getId()) != null) {
                return dao.doUpdate(shopinSAP);
            } else {
                return false;
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
    public ShopinSAP getById(int id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IShopinSAPDAO dao = DAOFactory.getShopinSAPDAOInstance(conn);
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
    public ShopinSAP getByOutListId(int outlistid) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IShopinSAPDAO dao = DAOFactory.getShopinSAPDAOInstance(conn);
            return dao.getByOutListId(outlistid);
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public List<ShopinSAP> getAll() throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IShopinSAPDAO dao = DAOFactory.getShopinSAPDAOInstance(conn);
            List<ShopinSAP> list = dao.getAll();
            if (list.isEmpty()) {
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
