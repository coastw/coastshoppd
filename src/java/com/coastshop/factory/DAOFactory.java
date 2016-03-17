/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.factory;

import com.coastshop.dao.*;
import com.coastshop.dao.impl.*;
import java.sql.Connection;

/**
 *
 * @author Coast
 */
public class DAOFactory {

    public static IOutListDAO getOutListDAOInstance(Connection connection) {
        return new OutListDAOImpl(connection);
    }

    public static IBrandDAO getBrandDAOInstance(Connection connection) {
        return new BrandDAOImpl(connection);
    }

    public static IProductDAO getProductDAOInstance(Connection connection) {
        return new ProductDAOImpl(connection);
    }
    
    public static IShopDAO getShopDAOInstance(Connection connection){
        return new ShopDAOImpl(connection);
    }
    
    public static IStoreOutDAO getStoreOutDAOInstance(Connection connection){
        return new StoreOutDAOImpl(connection);
    }
    
    public static IUserDAO getUserDAOInstance(Connection connection){
        return new UserDAOImpl(connection);
    }
    
    public static IShopinSAPDAO getShopinSAPDAOInstance(Connection connection){
        return new ShopinSAPDAOImpl(connection);
    }
    
    public static IDiscountDAO getDiscountDAOInstance(Connection connection){
        return new DiscountDAOImpl(connection);
    }
}