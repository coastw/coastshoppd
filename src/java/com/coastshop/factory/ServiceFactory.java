/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.factory;

import com.coastshop.service.*;
import com.coastshop.service.impl.*;

/**
 *
 * @author Coast
 */
public class ServiceFactory {
    public static IBrandService getBrandServiceInstance(){
        return new BrandServiceImpl();
    }
    
    public static IOutListService getOutListServiceInstance(){
        return new OutListServiceImpl();
    }
    
    public static IProductService getProductServiceInstance(){
        return new ProductServiceImpl();
    } 
    
    public static IShopService getShopServiceInstance(){
        return new ShopServiceImpl();
    }
    
    public static IStoreOutService getStoreOutServiceInstance(){
        return new StoreOutServiceImpl();
    }
    
    public static IUserService getUserServiceInstance(){
        return new UserServiceImpl();
    }
    
    public static IShopinSAPService getShopinSAPServiceInstance(){
        return new ShopinSAPServiceImpl();
    }
    
    public static IDiscountService getDiscountServiceInstance(){
        return new DiscountServiceImpl();
    }
}
