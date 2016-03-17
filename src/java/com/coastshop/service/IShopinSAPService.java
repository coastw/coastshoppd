/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service;

import com.coastshop.vo.ShopinSAP;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface IShopinSAPService {
    public int insert(ShopinSAP shopinSAP) throws Exception;
    public boolean delete(int id) throws Exception;
    public boolean update(ShopinSAP shopinSAP) throws Exception;
    public ShopinSAP getById(int id) throws Exception;
    public ShopinSAP getByOutListId(int outlistid) throws Exception;
    public List<ShopinSAP> getAll() throws Exception;
}
