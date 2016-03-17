/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.util;

/**
 * 通过sn，color，size比较StoreOutProductInfo对象
 * @author Coast
 */
public class StoreOutProductInfoComparator implements java.util.Comparator<StoreOutProductInfo> {
    
    @Override
    public int compare(StoreOutProductInfo o1, StoreOutProductInfo o2) {
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
