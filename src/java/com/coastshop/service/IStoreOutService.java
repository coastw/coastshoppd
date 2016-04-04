/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service;

import com.coastshop.util.StoreOutProductInfo;
import com.coastshop.vo.OutList;
import com.coastshop.vo.StoreOut;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface IStoreOutService {
    public int add(StoreOut so) throws Exception;
    public int subtract(int id) throws Exception;
    public StoreOut findById(int id) throws Exception;
    public List<StoreOut> findByList(int outlistid) throws Exception;
    public List<StoreOut> findByList(int outlistid, String order) throws Exception;
    public List<StoreOutProductInfo> findProductInfosByList(int outlistid) throws Exception;
    public List<StoreOutProductInfo> findProductInfosByList(int outlistid, String order) throws Exception;
    /**
     * 通过List:listids查询每一组数据，并将结果合并在一起,并根据SN排列去除重复 最后返回可读的列表
     *
     * @param List<OutList> outlists
     * @return List<StoreOutProductInfo>
     * @throws Exception
     */
    public List<StoreOutProductInfo> findProductInfosByLists(List<OutList> outlists) throws Exception;
     /**
     * 通过List:listids查询每一组数据，并将结果合并在一起,并根据SN,Color,Size排列去除重复 最后返回可读的列表
     *
     * @param List<OutList> outlists
     * @return List<StoreOutProductInfo>
     * @throws Exception
     */
    public List<StoreOutProductInfo> findDistinctProductInfosByLists(List<OutList> outlists) throws Exception;
    public List<StoreOutProductInfo> findSAPByLists(List<OutList> outlists) throws Exception;
    
    public List<StoreOutProductInfo> findDistinctAllProductInfos(String brand) throws Exception;
    
    public int getSumByList(int listid) throws Exception;
}
