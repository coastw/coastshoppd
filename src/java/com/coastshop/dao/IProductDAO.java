/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao;

import com.coastshop.util.PageSeperator;
import com.coastshop.vo.Product;
import java.util.List;
/**
 *
 * @author Coast
 */
public interface IProductDAO {
    public boolean doCreate(Product product) throws Exception;
    public boolean doUpdate(Product product) throws Exception;
    public boolean doDelete(int id) throws Exception;
    public Product getById(int id) throws Exception;
    public Product getBySn(String sn) throws Exception;
    public List<Product> getAll() throws Exception;
    public List<Product> getAll(String keyword) throws Exception;
    public List<Product> getAll(String keyword, PageSeperator pageSeperator) throws Exception;
    public int getCount(String column, String keyword) throws Exception;
    public int getCount(String keyword) throws Exception;
}
