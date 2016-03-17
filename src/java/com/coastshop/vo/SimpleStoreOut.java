/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.vo;

/**
 * 复写equals方法，以供bjd.do -> singel()方法使用
 * @author Coast
 */
public class SimpleStoreOut extends StoreOut{
    @Override
    public boolean equals(Object object){
        if (!(object instanceof SimpleStoreOut)){
            return false;
        }
        StoreOut so = (SimpleStoreOut)object;
        return ( this.getSn() ).equals( so.getSn() );
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    
}
