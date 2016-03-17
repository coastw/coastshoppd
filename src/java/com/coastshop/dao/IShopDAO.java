/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao;

import com.coastshop.vo.Shop;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface IShopDAO {
    public boolean doCreate(Shop shop) throws Exception;
    public boolean doUpdate(Shop shop) throws Exception;
    public boolean doDelete(int id) throws Exception;
    public Shop getById(int id) throws Exception;
    public Shop getByName(String name) throws Exception;
    public List<Shop> getAll() throws Exception;
    public List<Shop> getAll(String keyword) throws Exception;
    public int getCount() throws Exception;
}
