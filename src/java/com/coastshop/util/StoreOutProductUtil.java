/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.util;

/**
 *
 * @author Coast
 */
public class StoreOutProductUtil extends ProductUtil {

    /**
     *
     * @param storeoutid
     * @param sn
     * @param color
     * @param size
     * @param price
     * @param amount
     * @param brand
     * @return StoreOutProductInfo
     */
    public static StoreOutProductInfo getStoreOutProductInfo(int storeoutid, String sn, String color, String size, int price, int amount, String brand) {

        StoreOutProductInfo soInfo = new StoreOutProductInfo();
        if (ProductUtil.isValidateSn(sn)) {
            ProductInfo productInfo = ProductUtil.getProductInfo(sn, color, size);
            soInfo.setId(storeoutid);
            soInfo.setColor(productInfo.getColor());
            soInfo.setColorType(productInfo.getColorType());
            soInfo.setFirstType(productInfo.getFirstType());
            soInfo.setFitSeason(productInfo.getFitSeason());
            soInfo.setSeason(productInfo.getSeason());
            soInfo.setSecondType(productInfo.getSecondType());
            soInfo.setSize(productInfo.getSize());
            soInfo.setSn(productInfo.getSn());
            soInfo.setThirdType(productInfo.getThirdType());
            soInfo.setType(productInfo.getType());
            soInfo.setLocalSize(productInfo.getLocalSize());
            soInfo.setWorldSize(productInfo.getWorldSize());
            soInfo.setYear(productInfo.getYear());
            soInfo.setPrice(price);
            soInfo.setAmount(amount);
            soInfo.setBrand(brand);
            soInfo.setOriginColor(productInfo.getOriginColor());
        }else{
            soInfo.setId(storeoutid);
            soInfo.setSn(sn);
            soInfo.setColor(color);
            soInfo.setSize(size);
            soInfo.setPrice(price);
            soInfo.setAmount(amount);
            soInfo.setBrand(brand);
        }
        return soInfo;

    }
}
