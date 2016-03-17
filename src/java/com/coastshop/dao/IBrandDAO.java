/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao;

import com.coastshop.vo.Brand;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface IBrandDAO {
    public boolean doCreate(Brand brand) throws Exception;
    public boolean doUpdate(Brand brand) throws Exception;
    public boolean doDelete(int id) throws Exception;
    public Brand getById(int id) throws Exception;
    public Brand getByName(String name) throws Exception;
    public List<Brand> getAll() throws Exception;
    public List<Brand> getAll(String keyword) throws Exception;
    public int getCount() throws Exception;
}
