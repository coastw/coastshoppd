/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service;

import com.coastshop.vo.Shop;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface IShopService {
    public boolean insert(Shop shop) throws Exception;
    public boolean update(Shop shop) throws Exception;
    public boolean delete(int id) throws Exception;
    public Shop find(int id) throws  Exception;
    public Shop find(String name) throws  Exception;
    public List<Shop> findLikeName(String keyword) throws Exception;
    public List<Shop> findAll() throws Exception; 
}
