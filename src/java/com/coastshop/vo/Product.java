/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.vo;

/**
 *
 * @author Coast
 */
public class Product {
    private int id;
    private String sn;
    private int price;
    private int brandid;
    private int org_price;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * @param sn the sn to set
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the brandid
     */
    public int getBrandid() {
        return brandid;
    }

    /**
     * @param brandid the brandid to set
     */
    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    /**
     * @return the org_price
     */
    public int getOrg_price() {
        return org_price;
    }

    /**
     * @param org_price the org_price to set
     */
    public void setOrg_price(int org_price) {
        this.org_price = org_price;
    }
    
}
