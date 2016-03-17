/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.util;

import com.coastshop.vo.StoreOut;

/**
 *
 * @author Coast
 */
public class StoreOutComparator implements java.util.Comparator<StoreOut> {

    @Override
    public int compare(StoreOut o1, StoreOut o2) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (o1.getSn().equals(o2.getSn())) {
            if (o1.getColor().equals(o2.getColor())) {
                return o1.getSize().compareTo(o2.getSize());
            }else{
                return o1.getColor().compareTo(o2.getColor());
            }
        } else {
            return o1.getSn().compareTo(o2.getSn());
        }
    }
}
