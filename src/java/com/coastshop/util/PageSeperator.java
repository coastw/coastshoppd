/*
 * OutList 分页类
 */
package com.coastshop.util;


/**
 * OutList 分页类
 * @author Coast
 * @version 1.0
 */
public class PageSeperator {
    private int allcount;   //总记录数
    private int rowsperpage;    //每页多少条记录
    private int currentpagenum;    //当前第几页
    private String limitparam;    //limit 语句 (LIMIT 0,10)

    /**
     * 
     * @param allcount:总记录数
     * @param rowsperpage:每页多少条记录
     * @param currentpagenum:当前第几页
     */
    public PageSeperator(int allcount, int rowsperpage, int currentpagenum) {
        this.allcount = allcount;
        this.rowsperpage = rowsperpage;
        this.currentpagenum = currentpagenum;
        this.limitparam = "LIMIT "
                + Integer.toString( (this.currentpagenum-1)*this.rowsperpage )
                + "," + Integer.toString(rowsperpage);
    }
    
    /**
     * 总页数
     * @return
     */
    public int getPageCount(){
        return (allcount + rowsperpage - 1) / rowsperpage;
    }
        
    /**
     * 第一页
     * @return 
     */
    public int getFirstPageNum(){
        return 1;
    }
    
    /**
     * 最后一页
     * @return 
     */
    public int getLastPageNum(){
        return getPageCount();
    }
    
    /**
     * 上一页
     * @return 
     */
    public int getPrevPageNum(){
        if (getCurrentPageNum() <= 1) {
            return 1;
        }
        return  getCurrentPageNum() - 1;
    }
    
    /**
     * 下一页
     * @return 
     */
    public int getNextPageNum(){
        if (getCurrentPageNum() >= getLastPageNum()) {
            return getLastPageNum();
        }
        return getCurrentPageNum() + 1;
    }

    /**
     * @return the currentpagenum
     */
    public int getCurrentPageNum() {
        return currentpagenum;
    }

    /**
     * @param currentpagenum the currentpagenum to set
     */
    public void setCurrentpagenum(int currentpagenum) {
        this.currentpagenum = currentpagenum;
    }

    /**
     * @return the limitparam
     */
    public String getLimitparam() {
        return limitparam;
    }

    /**
     * @param limitparam the limitparam to set
     */
    public void setLimitparam(String limitparam) {
        this.limitparam = limitparam;
    }
    
    
}