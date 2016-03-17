/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service;

import com.coastshop.util.PageSeperator;
import com.coastshop.vo.Product;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface IProductService {

    /**
     *
     * @param product com.coastshop.vo.Product对象
     * @return 成功返回true，如果product已经存在或增加失败则返回false
     * @throws Exception
     */
    public boolean insert(Product product) throws Exception;
    
    /**
     * 
     * @param product com.coastshop.vo.Product对象
     * @return 成功返回true，如果product不存在或修改失败则返回false
     * @throws Exception 
     */
    public boolean update(Product product) throws Exception;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public boolean delete(int id) throws Exception;
    
    /**
     * 
     * @param id
     * @return Product
     * @throws Exception 
     */
    public Product find(int id) throws Exception;
    
    /**
     * 
     * @param sn
     * @return Product
     * @throws Exception 
     */
    public Product find(String sn) throws Exception;
    
    /**
     * 
     * @param keyword select * from tbproductqs where sn LIKE %keyword%
     * @return List<Product>
     * @throws Exception 
     */
    public List<Product> findLikeSn(String keyword) throws Exception;
    
    
    /**
     *
     * @param keyword
     * @param pageSeperator
     * @return
     * @throws Exception
     */
    public List<Product> findLikeSn(String keyword, PageSeperator pageSeperator) throws Exception;
    
    
    
    /**
     *
     * @param keyword
     * @return
     */
    public int getCount(String keyword) throws Exception;
    
}
