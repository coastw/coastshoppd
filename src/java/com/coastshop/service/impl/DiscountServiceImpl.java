/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service.impl;

import com.coastshop.dao.IDiscountDAO;
import com.coastshop.dbc.DatabaseConnection;
import com.coastshop.factory.DAOFactory;
import com.coastshop.service.IDiscountService;
import com.coastshop.vo.Discount;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Coast
 */
public class DiscountServiceImpl implements IDiscountService {

    @Override
    public int add(Discount discount) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IDiscountDAO dao = DAOFactory.getDiscountDAOInstance(conn);
            return dao.add(discount);
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public int update(Discount discount) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IDiscountDAO dao = DAOFactory.getDiscountDAOInstance(conn);
            return dao.update(discount);
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public Discount getById(int id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IDiscountDAO dao = DAOFactory.getDiscountDAOInstance(conn);
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
    public List<Discount> getAll() throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IDiscountDAO dao = DAOFactory.getDiscountDAOInstance(conn);
            return dao.getAll();
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

}
