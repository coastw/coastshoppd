/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service.impl;

import com.coastshop.dao.IBrandDAO;
import com.coastshop.dbc.DatabaseConnection;
import com.coastshop.factory.DAOFactory;
import com.coastshop.service.IBrandService;
import com.coastshop.vo.Brand;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Coast
 */
public class BrandServiceImpl implements IBrandService {

    @Override
    public boolean insert(Brand brand) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IBrandDAO dao = DAOFactory.getBrandDAOInstance(conn);
            boolean flag = false;
            if ( dao.getByName(brand.getName()) == null ){
                flag = dao.doCreate(brand);
            }
            return flag;
        }catch (Exception e){
            throw e;
        }finally{
            if( conn != null ){
                conn.close();
            }
        }
    }

    @Override
    public boolean update(Brand brand) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IBrandDAO dao = DAOFactory.getBrandDAOInstance(conn);
            boolean flag = false;
            if (dao.getById(brand.getId()) != null) {
                flag = dao.doUpdate(brand);
            }
            return flag;
        }catch (Exception e){
            throw e;
        }finally{
            if( conn != null ){
                conn.close();
            }
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IBrandDAO dao = DAOFactory.getBrandDAOInstance(conn);
            boolean flag = false;
            if ( dao.getById(id) != null ){
                flag = dao.doDelete(id);
            }
            return flag;
        }catch (Exception e){
            throw e;
        }finally{
            if( conn != null ){
                conn.close();
            }
        }
    }

    @Override
    public Brand find(int id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IBrandDAO dao = DAOFactory.getBrandDAOInstance(conn);
            return dao.getById(id);
        }catch (Exception e){
            throw e;
        }finally{
            if( conn != null ){
                conn.close();
            }
        }
    }

    @Override
    public Brand find(String name) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IBrandDAO dao = DAOFactory.getBrandDAOInstance(conn);
            return dao.getByName(name);
        }catch (Exception e){
            throw e;
        }finally{
            if( conn != null ){
                conn.close();
            }
        }
    }

    @Override
    public List<Brand> findAll() throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IBrandDAO dao = DAOFactory.getBrandDAOInstance(conn);
            List<Brand> list = dao.getAll();
            if (list.isEmpty()){
                return null;
            }else{
                return list;
            }
        }catch (Exception e){
            throw e;
        }finally{
            if( conn != null ){
                conn.close();
            }
        }
    }

    @Override
    public List<Brand> findAll(String keyword) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IBrandDAO dao = DAOFactory.getBrandDAOInstance(conn);
            List<Brand> list = dao.getAll(keyword);
            if (list.isEmpty()){
                return null;
            }else{
                return list;
            }
        }catch (Exception e){
            throw e;
        }finally{
            if( conn != null ){
                conn.close();
            }
        }
    }
}
