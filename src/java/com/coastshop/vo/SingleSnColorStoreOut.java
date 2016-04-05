/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.vo;

/**
 * 复写equals方法，以供 singelSnColor()方法使用
 *
 * @author Coast
 */
public class SingleSnColorStoreOut extends StoreOut {

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SingleSnColorStoreOut)) {
            return false;
        }
        StoreOut so = (SingleSnColorStoreOut) object;
        boolean snFlag = this.getSn().equals(so.getSn());
        boolean colorFlag = this.getColor().equals(so.getColor());
        return snFlag && colorFlag;
    }

}
