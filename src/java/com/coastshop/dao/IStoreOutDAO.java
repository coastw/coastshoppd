/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao;

import com.coastshop.vo.StoreOut;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface IStoreOutDAO {
    public int doCreate(StoreOut so) throws Exception;
    public int doUpdate(StoreOut so) throws Exception;
    public int doDelete(int id) throws Exception;
    public StoreOut getById(int id) throws Exception;
    public StoreOut getBySnColorSizeList(String sn,String color,String size,int listid) throws Exception;
    public List<StoreOut> getByListId(int listid) throws Exception;
    public List<StoreOut> getByListId(int listid, String order) throws Exception;
    public List<StoreOut> getAll() throws Exception;
    public int getCount() throws Exception;
    public int getSumAmount(int outlistid) throws Exception;
}
