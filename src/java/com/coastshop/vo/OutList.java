/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.vo;

import java.util.Date;

/**
 *
 * @author Coast
 */
public class OutList {
    private int id;
    private int brandid;
    private int userid;
    private int shopid;
    private Date date;

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
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * @return the shopid
     */
    public int getShopid() {
        return shopid;
    }

    /**
     * @param shopid the shopid to set
     */
    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
