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
public class ShopinSAP {
    private int id;
    private int outlistid;
    private Date uploadDate;

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
     * @return the outlistid
     */
    public int getOutlistid() {
        return outlistid;
    }

    /**
     * @param outlistid the outlistid to set
     */
    public void setOutlistid(int outlistid) {
        this.outlistid = outlistid;
    }

    /**
     * @return the uploadDate
     */
    public Date getUploadDate() {
        return uploadDate;
    }

    /**
     * @param uploadDate the uploadDate to set
     */
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
    
}
