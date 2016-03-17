/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao;

import com.coastshop.vo.ShopinSAP;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface IShopinSAPDAO {
    public int doCreate(ShopinSAP shopinSAP) throws Exception;
    public boolean doDelete(int id) throws Exception;
    public boolean doUpdate(ShopinSAP shopinSAP) throws Exception;
    public ShopinSAP getById(int id) throws Exception;
    public ShopinSAP getByOutListId(int outlistid) throws Exception;
    public List<ShopinSAP> getAll() throws Exception;
}
