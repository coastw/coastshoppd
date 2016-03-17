/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service.impl;

import com.coastshop.dao.IBrandDAO;
import com.coastshop.dao.IOutListDAO;
import com.coastshop.dao.IShopDAO;
import com.coastshop.dao.IStoreOutDAO;
import com.coastshop.dao.IUserDAO;
import com.coastshop.dbc.DatabaseConnection;
import com.coastshop.factory.DAOFactory;
import com.coastshop.service.IOutListService;
import com.coastshop.util.HtmlOutList;
import com.coastshop.util.PageSeperator;
import com.coastshop.vo.OutList;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OutListServiceImpl implements IOutListService {

    @Override
    public HtmlOutList getHtmlById(int id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            OutList outlist = dao.getById(id);
            if (outlist == null) {
                return null;
            } else {
                //TODO
                IOutListDAO olDAO = DAOFactory.getOutListDAOInstance(conn);
                IShopDAO shopDAO = DAOFactory.getShopDAOInstance(conn);
                IBrandDAO brandDAO = DAOFactory.getBrandDAOInstance(conn);
                IUserDAO userDAO = DAOFactory.getUserDAOInstance(conn);
                IStoreOutDAO soDAO = DAOFactory.getStoreOutDAOInstance(conn);
                //取得shopname
                String shop = shopDAO.getById(outlist.getShopid()).getName();
                //取得总数
                int amount = soDAO.getSumAmount(outlist.getId());
                //取得品牌
                String brand = brandDAO.getById(outlist.getBrandid()).getName();
                //User
                String user = userDAO.getById(outlist.getUserid()).getName();
                HtmlOutList hol = new HtmlOutList();
                hol.setId(outlist.getId());
                hol.setShop(shop);
                hol.setDate(outlist.getDate());
                hol.setAmount(amount);
                hol.setBrand(brand);
                hol.setUser(user);
                return hol;
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
    public List<HtmlOutList> getHtmlAll(PageSeperator ps) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            List<OutList> list = dao.getAll(ps.getLimitparam());
            if (list.isEmpty()) {
                return null;
            } else {
                //TODO
                IShopDAO shopDAO = DAOFactory.getShopDAOInstance(conn);
                IBrandDAO brandDAO = DAOFactory.getBrandDAOInstance(conn);
                IStoreOutDAO soDAO = DAOFactory.getStoreOutDAOInstance(conn);
                List<HtmlOutList> htmlList = new ArrayList<HtmlOutList>();
                Iterator<OutList> iter = list.iterator();
                while (iter.hasNext()) {
                    OutList ol = iter.next();
                    //取得shopname
                    String shop = shopDAO.getById(ol.getShopid()).getName();
                    //取得总数
                    int amount = soDAO.getSumAmount(ol.getId());
                    //取得品牌
                    String brand = brandDAO.getById(ol.getBrandid()).getName();
                    HtmlOutList hol = new HtmlOutList();
                    hol.setId(ol.getId());
                    hol.setShop(shop);
                    hol.setDate(ol.getDate());
                    hol.setAmount(amount);
                    hol.setBrand(brand);
                    htmlList.add(hol);
                }
                return htmlList;
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
    public List<HtmlOutList> getHtmlByShop(int shopid, PageSeperator ps) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            List<OutList> list = dao.getByShopId(shopid, ps.getLimitparam());
            if (list.isEmpty()) {
                return null;
            } else {
                //TODO
                IShopDAO shopDAO = DAOFactory.getShopDAOInstance(conn);
                IBrandDAO brandDAO = DAOFactory.getBrandDAOInstance(conn);
                IStoreOutDAO soDAO = DAOFactory.getStoreOutDAOInstance(conn);
                List<HtmlOutList> htmlList = new ArrayList<HtmlOutList>();
                Iterator<OutList> iter = list.iterator();
                while (iter.hasNext()) {
                    OutList ol = iter.next();
                    //取得shopname
                    String shop = shopDAO.getById(ol.getShopid()).getName();
                    //取得总数
                    int amount = soDAO.getSumAmount(ol.getId());
                    //取得品牌
                    String brand = brandDAO.getById(ol.getBrandid()).getName();
                    HtmlOutList hol = new HtmlOutList();
                    hol.setId(ol.getId());
                    hol.setShop(shop);
                    hol.setDate(ol.getDate());
                    hol.setAmount(amount);
                    hol.setBrand(brand);
                    htmlList.add(hol);
                }
                return htmlList;
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
    public List<OutList> getAll(PageSeperator ps) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            List<OutList> list = dao.getAll(ps.getLimitparam());
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
    public int insert(OutList outList) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            //TODO 怎么判断才能不重复插入
            /*
             * 检查上次list是否与这次重复（字段值都一样），并且上次list的明细是否为空
             * 上次id = id-1 ？？？
             * 如果 （重复&&明细为空则用上次outList）
             * 否则 新建
             */

            OutList lastList = dao.getLast();
            //初次使用程序时，没有结果，为Null
            if (lastList == null) {
                return dao.doCreate(outList);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String d1 = sdf.format(outList.getDate());
                String d2 = sdf.format(lastList.getDate());
                if (outList.getBrandid() == lastList.getBrandid()
                        && outList.getShopid() == lastList.getShopid()
                        && outList.getUserid() == lastList.getUserid()
                        && d1.equals(d2)
                        && 0 == DAOFactory.getStoreOutDAOInstance(conn).getSumAmount(lastList.getId())) {
                    return lastList.getId();
                }
                return dao.doCreate(outList);
            }
            ////初次使用程序时，没有结果，为Null

        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public boolean update(OutList outList) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            boolean flag = false;
            if (dao.getById(outList.getId()) != null) {
                flag = dao.doUpdate(outList);
            }
            return flag;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            boolean flag = false;
            if (dao.getById(id) != null) {
                flag = dao.doDelete(id);
            }
            return flag;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public OutList getById(int id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
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
    public List<OutList> getAll() throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            List<OutList> list = dao.getAll();
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
    public List<OutList> getByBrandId(int brandid) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            List<OutList> list = dao.getByBrandId(brandid);
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
    public List<OutList> getByUserId(int userid) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            List<OutList> list = dao.getByUserId(userid);
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
    public List<OutList> getByShopId(int shopid) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            List<OutList> list = dao.getByUserId(shopid);
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
    public List<OutList> getByDate(Date date) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            List<OutList> list = dao.getByDate(date);
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
    public List<OutList> getFromDateShopIdBrandId(Date begindate, Date enddate, int shopid, int brandid) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            List<OutList> list = dao.getFromDateShopIDBrandId(begindate, enddate, shopid, brandid);
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
    public int getCount() throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            return dao.getCount();
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public int getCountByShop(int shopid) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            IOutListDAO dao = DAOFactory.getOutListDAOInstance(conn);
            return dao.getCountByShopId(shopid);
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
