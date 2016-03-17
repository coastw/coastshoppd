/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service.impl;

import com.coastshop.dao.IProductDAO;
import com.coastshop.dao.IStoreOutDAO;
import com.coastshop.dbc.DatabaseConnection;
import com.coastshop.factory.DAOFactory;
import com.coastshop.service.IStoreOutService;
import com.coastshop.util.StoreOutComparator;
import com.coastshop.util.StoreOutProductInfo;
import com.coastshop.util.StoreOutProductUtil;
import com.coastshop.util.StoreOutSimpleComparator;
import com.coastshop.vo.OutList;
import com.coastshop.vo.Product;
import com.coastshop.vo.SimpleStoreOut;
import com.coastshop.vo.StoreOut;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class StoreOutServiceImpl implements IStoreOutService {

    @Override
    /**
     * 在同一个list下，判断sn & color & size，如果没有就新建，有就amount+1
     */
    public int add(StoreOut so) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IStoreOutDAO dao = DAOFactory.getStoreOutDAOInstance(conn);
            int key = 0;
            StoreOut storeOut = dao.getBySnColorSizeList(so.getSn(), so.getColor(), so.getSize(), so.getOutlistid());
            if (storeOut != null) {
                storeOut.setAmount(storeOut.getAmount() + so.getAmount());
                key = dao.doUpdate(storeOut);
            } else {
                key = dao.doCreate(so);
            }
            return key;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    /**
     * 先判断是否存在，不存在返回false 如果存在，则判断数量是否大于1 大于1则数量减1，否则删除
     */
    public int subtract(int id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IStoreOutDAO dao = DAOFactory.getStoreOutDAOInstance(conn);
            int key = 0;
            StoreOut so = dao.getById(id);
            if (so != null) {
                if (so.getAmount() == 1) {
                    key = dao.doDelete(id);
                } else {
                    so.setAmount(so.getAmount() - 1);
                    key = dao.doUpdate(so);
                }
            }
            return key;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public StoreOut findById(int id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IStoreOutDAO dao = DAOFactory.getStoreOutDAOInstance(conn);
            return dao.getById(id);
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public List<StoreOut> findByList(int outlistid) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IStoreOutDAO dao = DAOFactory.getStoreOutDAOInstance(conn);
            List<StoreOut> list = dao.getByListId(outlistid);
            if (list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public List<StoreOutProductInfo> findProductInfosByList(int outlistid) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String brand = DAOFactory.getBrandDAOInstance(conn).getById(DAOFactory.getOutListDAOInstance(conn).getById(outlistid).getBrandid()).getName();
            IStoreOutDAO dao = DAOFactory.getStoreOutDAOInstance(conn);
            List<StoreOut> list = dao.getByListId(outlistid);
            if (list.isEmpty()) {
                return null;
            } else {
                IProductDAO productDAO = DAOFactory.getProductDAOInstance(conn);
                List<StoreOutProductInfo> newlist = new ArrayList<StoreOutProductInfo>();
                Iterator<StoreOut> iter = list.iterator();
                while (iter.hasNext()) {
                    StoreOut so = iter.next();
                    //storeout.id
                    //price
                    int price = 0;
                    try {
                        price = productDAO.getBySn(so.getSn()).getPrice();
                    } catch (NullPointerException ne) {
                        System.out.println("Null:" + so.getSn());
                    }
                    //amout
                    int amount = so.getAmount();
                    StoreOutProductInfo pinfo = StoreOutProductUtil.getStoreOutProductInfo(so.getId(), so.getSn(), so.getColor(), so.getSize(), price, amount, brand);
                    if (pinfo != null) {
                        newlist.add(pinfo);
                    } else {
                        StoreOutProductInfo errinfo = new StoreOutProductInfo();
                        errinfo.setSn(so.getSn());
                        errinfo.setColor(so.getColor());
                        errinfo.setSize(so.getSize());
                        newlist.add(errinfo);
                    }
                }
                return newlist;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public List<StoreOutProductInfo> findProductInfosByList(int outlistid, String order) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String brand = DAOFactory.getBrandDAOInstance(conn).getById(DAOFactory.getOutListDAOInstance(conn).getById(outlistid).getBrandid()).getName();
            IStoreOutDAO dao = DAOFactory.getStoreOutDAOInstance(conn);
            List<StoreOut> list = dao.getByListId(outlistid, order);
            if (list.isEmpty()) {
                return null;
            } else {
                IProductDAO productDAO = DAOFactory.getProductDAOInstance(conn);
                List<StoreOutProductInfo> newlist = new ArrayList<StoreOutProductInfo>();
                Iterator<StoreOut> iter = list.iterator();
                while (iter.hasNext()) {
                    StoreOut so = iter.next();
                    //storeout.id
                    //price
                    int price = productDAO.getBySn(so.getSn()).getPrice();
                    //amout
                    int amount = so.getAmount();
                    StoreOutProductInfo pinfo = StoreOutProductUtil.getStoreOutProductInfo(so.getId(), so.getSn(), so.getColor(), so.getSize(), price, amount, brand);
                    if (pinfo != null) {
                        newlist.add(pinfo);
                    } else {
                        StoreOutProductInfo errinfo = new StoreOutProductInfo();
                        errinfo.setSn(so.getSn());
                        errinfo.setColor(so.getColor());
                        errinfo.setSize(so.getSize());
                        newlist.add(errinfo);
                    }
                }
                return newlist;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * 通过listids查询每一组数据，并将结果合并在一起并排列去除重复 最后返回可读的列表
     *
     * @param outlists
     * @return List<StoreOutProductInfo>
     * @throws Exception
     */
    @Override
    public List<StoreOutProductInfo> findProductInfosByLists(List<OutList> outlists) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            //
            List<StoreOutProductInfo> sopilist = null;  //准备最终返回的结果
            String brand = null;    //准备要获取的品牌
            List<StoreOut> solist = new ArrayList<StoreOut>(); //准备汇总list

            Iterator<OutList> iter = outlists.iterator();
            while (iter.hasNext()) {
                OutList outlist = iter.next();
                int listid = outlist.getId();   //获得listid
                if (brand == null) {    //只获取一次brand
                    brand = DAOFactory.getBrandDAOInstance(conn).getById(outlist.getBrandid()).getName();
                }
                //通过id获取List<StoreOut>,放在solist中
                List<StoreOut> onelist = DAOFactory.getStoreOutDAOInstance(conn).getByListId(listid);
                if (onelist != null) {
                    solist.addAll(onelist);
                }
            }
            //去除重复: 只比较sn,合并:amount
            solist = singleSn(solist);
            //排序: 只比较sn
            Collections.sort(solist, new StoreOutSimpleComparator());
            //变成可读形式
            if (solist != null) {
                sopilist = new ArrayList<StoreOutProductInfo>();
                Iterator<StoreOut> iter2 = solist.iterator();
                while (iter2.hasNext()) {
                    StoreOut so = iter2.next();
                    //价格可能为空
                    int price = 0;
                    try {
                        price = DAOFactory.getProductDAOInstance(conn).getBySn(so.getSn()).getPrice();
                    } catch (Exception e) {
                        System.out.println("!257行,No price: id=" + so.getId() + ",sn=" + so.getSn());
                    }
                    StoreOutProductInfo sopi = StoreOutProductUtil.getStoreOutProductInfo(so.getId(), so.getSn(), so.getColor(), so.getSize(), price, so.getAmount(), brand);
                    if (sopi != null) {
                        sopilist.add(sopi);
                    }
                }
            }
            return sopilist;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * @param outlists
     * @return List<StoreOutProductInfo>
     * @throws Exception
     */
    @Override
    public List<StoreOutProductInfo> findDistinctProductInfosByLists(List<OutList> outlists) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            //
            List<StoreOutProductInfo> sopilist = null;  //准备最终返回的结果
            String brand = null;    //准备要获取的品牌
            List<StoreOut> solist = new ArrayList<StoreOut>(); //准备汇总list

            Iterator<OutList> iter = outlists.iterator();
            while (iter.hasNext()) {
                OutList outlist = iter.next();
                int listid = outlist.getId();   //获得listid
                if (brand == null) {    //只获取一次brand
                    brand = DAOFactory.getBrandDAOInstance(conn).getById(outlist.getBrandid()).getName();
                }
                //通过id获取List<StoreOut>,放在solist中
                List<StoreOut> onelist = DAOFactory.getStoreOutDAOInstance(conn).getByListId(listid);
                if (onelist != null) {
                    solist.addAll(onelist);
                }
            }
            //去除重复: 只比较sn,合并:amount
            solist = singleSnColorSize(solist);
            //排序: 只比较sn
            Collections.sort(solist, new StoreOutSimpleComparator());
            //变成可读形式
            if (solist != null) {
                sopilist = new ArrayList<StoreOutProductInfo>();
                Iterator<StoreOut> iter2 = solist.iterator();
                while (iter2.hasNext()) {
                    StoreOut so = iter2.next();
                    //价格
                    int price = 0;
                    try {
                        price = DAOFactory.getProductDAOInstance(conn).getBySn(so.getSn()).getPrice();
                    } catch (Exception e) {
                        System.out.println("!318行,No price: id=" + so.getId() + ",sn=" + so.getSn());
                    }
                    StoreOutProductInfo sopi = StoreOutProductUtil.getStoreOutProductInfo(so.getId(), so.getSn(), so.getColor(), so.getSize(), price, so.getAmount(), brand);
                    if (sopi != null) {
                        sopilist.add(sopi);
                    }
                }
            }
            return sopilist;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * getALl
     *
     * @param brand String:品牌名
     * @return
     * @throws Exception
     */
    @Override
    public List<StoreOutProductInfo> findDistinctAllProductInfos(String brand) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            //准备最终返回的结果
            List<StoreOutProductInfo> sopilist = null;
            List<StoreOut> solist = DAOFactory.getStoreOutDAOInstance(conn).getAll();
            //变成可读形式
            if (solist != null) {
                //去除重复,合并:amount
                solist = singleSnColorSize(solist);
                //排序
                Collections.sort(solist, new StoreOutComparator());
                sopilist = new ArrayList<StoreOutProductInfo>();
                Iterator<StoreOut> itr = solist.iterator();
                while (itr.hasNext()) {
                    StoreOut so = itr.next();
                    //价格
                    Product product = DAOFactory.getProductDAOInstance(conn).getBySn(so.getSn());
                    if (product != null) {
                        int price = product.getPrice();
                        StoreOutProductInfo sopi = StoreOutProductUtil.getStoreOutProductInfo(so.getId(), so.getSn(), so.getColor(), so.getSize(), price, so.getAmount(), brand);
                        if (sopi != null) {
                            sopilist.add(sopi);
                        }
                    }
                }
            }
            return sopilist;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    //private
    private List<StoreOut> singleSn(List<StoreOut> list) {
        List<SimpleStoreOut> newList = new ArrayList<SimpleStoreOut>();
        Iterator<StoreOut> iter = list.iterator();
        while (iter.hasNext()) {
            StoreOut so = iter.next();
            SimpleStoreOut sso = new SimpleStoreOut();
            sso.setId(so.getId());
            sso.setSn(so.getSn());
            sso.setSize(so.getSize());
            sso.setColor(so.getColor());
            sso.setAmount(so.getAmount());
            sso.setOutlistid(so.getOutlistid());
            //TODO amount
            if (!(newList.contains(sso))) {  //不重复则add,根据复写的Object.equals()方法
                newList.add(sso);
            } else {                          //重复则更新已有元素的amount
                int index = newList.indexOf(sso); //此元素在列表中的位置
                SimpleStoreOut existSimpleStoreOut = newList.get(index); //取得已有的元素
                existSimpleStoreOut.setAmount(existSimpleStoreOut.getAmount() + sso.getAmount());//将以有的数量与新数量相加
//                newList.remove(index);
//                newList.add(existSimpleStoreOut);
            }
        }
        List<StoreOut> finalList = new ArrayList<StoreOut>();
        finalList.addAll(newList);
        return finalList;
    }

    private List<StoreOut> singleSnColorSize(List<StoreOut> list) {
        List<StoreOut> newList = new ArrayList<StoreOut>();
        Iterator<StoreOut> iter = list.iterator();
        while (iter.hasNext()) {
            StoreOut so = iter.next();
            if (!(newList.contains(so))) {  //不重复则add,根据复写的Object.equals()方法
                newList.add(so);
            } else {
                int index = newList.indexOf(so);
                StoreOut existsStoreOut = newList.get(index);
                existsStoreOut.setAmount(existsStoreOut.getAmount() + so.getAmount());
                //TODO : 用不用重新添加到list里?
            }
        }
        return newList;
    }
//End

    @Override
    public List<StoreOut> findByList(int outlistid, String order) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IStoreOutDAO dao = DAOFactory.getStoreOutDAOInstance(conn);
            List<StoreOut> list = dao.getByListId(outlistid, order);
            if (list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public int getSumByList(int outlistid) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IStoreOutDAO dao = DAOFactory.getStoreOutDAOInstance(conn);
            return dao.getSumAmount(outlistid);
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
