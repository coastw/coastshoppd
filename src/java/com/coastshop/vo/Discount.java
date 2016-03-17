/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coastshop.vo;

/**
 *
 * @author Coast
 */
public class Discount {
    private int id;
    private String categorynum;
    private String categorychar;
    private String regex;
    private String persent;

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
     * @return the categorynum
     */
    public String getCategorynum() {
        return categorynum;
    }

    /**
     * @param categorynum the categorynum to set
     */
    public void setCategorynum(String categorynum) {
        this.categorynum = categorynum;
    }

    /**
     * @return the categorychar
     */
    public String getCategorychar() {
        return categorychar;
    }

    /**
     * @param categorychar the categorychar to set
     */
    public void setCategorychar(String categorychar) {
        this.categorychar = categorychar;
    }

    /**
     * @return the regex
     */
    public String getRegex() {
        return regex;
    }

    /**
     * @param regex the regex to set
     */
    public void setRegex(String regex) {
        this.regex = regex;
    }

    /**
     * @return the persent
     */
    public String getPersent() {
        return persent;
    }

    /**
     * @param persent the persent to set
     */
    public void setPersent(String persent) {
        this.persent = persent;
    }

    @Override
    public String toString() {
        return "Discount{" + "id=" + id + ", categorynum=" + categorynum + ", categorychar=" + categorychar + ", regex=" + regex + ", persent=" + persent + '}';
    }
    
}