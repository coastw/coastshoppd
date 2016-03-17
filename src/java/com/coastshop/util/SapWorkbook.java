/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.util;

import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Coast
 */
public class SapWorkbook {
    private Workbook workbook;
    private int sum;
    private String err;
    private String msg;

    /**
     * @return the wb
     */
    public Workbook getWorkbook() {
        return workbook;
    }

    /**
     * @param wb the wb to set
     */
    public void setWorkbook(Workbook wb) {
        this.workbook = wb;
    }

    /**
     * @return the sum
     */
    public int getSum() {
        return sum;
    }

    /**
     * @param sum the sum to set
     */
    public void setSum(int sum) {
        this.sum = sum;
    }

    /**
     * @return the err
     */
    public String getErr() {
        return err;
    }

    /**
     * @param err the err to set
     */
    public void setErr(String err) {
        this.err = err;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
