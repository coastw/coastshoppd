/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.dao;

import com.coastshop.vo.OutList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface IOutListDAO {
    public int doCreate(OutList outList) throws Exception;
    public boolean doUpdate(OutList outList) throws Exception;
    public boolean doDelete(int id) throws Exception;
    public OutList getById(int id) throws Exception;
    public OutList getLast() throws Exception;
    public List<OutList> getAll() throws Exception;
    public List<OutList> getAll(String limitString) throws Exception;
    public List<OutList> getByBrandId(int brandid) throws Exception;
    public List<OutList> getByUserId(int userid) throws Exception;
    public List<OutList> getByShopId(int shopid) throws Exception;
    public List<OutList> getByShopId(int shopid, String limitString) throws Exception;
    public List<OutList> getByDate(Date date) throws Exception;
    public List<OutList> getFromDateShopIDBrandId(Date begindate,Date enddate, int shopid, int brandid) throws Exception;
    public int getCount() throws Exception;
    public int getCountByShopId(int shopid) throws Exception;
    
}
