/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.util;

import com.coastshop.vo.StoreOut;

/**
 * 通过sn比较StoreOut对象
 * @author Coast
 */
public class StoreOutSimpleComparator implements java.util.Comparator<StoreOut> {

    @Override
    public int compare(StoreOut o1, StoreOut o2) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return o1.getSn().compareTo(o2.getSn());
    }
}
