/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service;

import com.coastshop.vo.Brand;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface IBrandService {
    public boolean insert(Brand brand) throws Exception;
    public boolean update(Brand brand) throws Exception;
    public boolean delete(int id) throws Exception;
    public Brand find(int id) throws Exception;
    public Brand find(String name) throws Exception;
    public List<Brand> findAll() throws Exception;
    public List<Brand> findAll(String keyword) throws Exception;
}
