/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service;

import com.coastshop.util.HtmlOutList;
import com.coastshop.util.PageSeperator;
import com.coastshop.vo.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface IOutListService {
    public int insert(OutList outList) throws Exception;
    public boolean update(OutList outList) throws Exception;
    public boolean delete(int id) throws Exception;
    public OutList getById(int id) throws Exception;
    public HtmlOutList getHtmlById(int id) throws Exception;
    public List<OutList> getAll() throws Exception;
    public List<OutList> getAll(PageSeperator ps) throws Exception;
    public List<HtmlOutList> getHtmlAll(PageSeperator ps) throws Exception;
    public List<HtmlOutList> getHtmlByShop(int shopid, PageSeperator ps) throws Exception;
    public List<OutList> getByBrandId(int brandid) throws Exception;
    public List<OutList> getByUserId(int userid) throws Exception;
    public List<OutList> getByShopId(int shopid) throws Exception;
    public List<OutList> getByDate(Date date) throws Exception;
    public List<OutList> getFromDateShopIdBrandId(Date begindate,Date enddate, int shopid, int brandid) throws Exception;
    public List<OutList> getFromDate(Date begindate,Date enddate) throws Exception;
    public int getCount() throws Exception;
    public int getCountByShop(int shopid) throws Exception;
}
