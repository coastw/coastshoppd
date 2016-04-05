/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.vo;

/**
 *
 * @author Coast
 */
public class StoreOut {
    private int id;
    private String sn;
    private String color;
    private String size;
    private int amount;
    private int outlistid;

    public StoreOut() {
        super();
    }

    public StoreOut(int id, String sn, String color, String size, int amount, int outlistid) {
        super();
        this.id = id;
        this.sn = sn;
        this.color = color;
        this.size = size;
        this.amount = amount;
        this.outlistid = outlistid;
    }

    @Override
    public String toString() {
        return "StoreOut{" + "id=" + id + ", sn=" + sn + ", color=" + color + ", size=" + size + ", amount=" + amount + ", outlistid=" + outlistid + '}';
    }

    
    
    
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
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
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
    
    @Override
    public boolean equals(Object object){
        if (!(object instanceof StoreOut)){
            return false;
        }
        StoreOut so = (StoreOut)object;
        return this.sn.equals(so.sn)&&this.color.equals(so.color)&&this.size.equals(so.size);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.sn != null ? this.sn.hashCode() : 0);
        return hash;
    }
}
