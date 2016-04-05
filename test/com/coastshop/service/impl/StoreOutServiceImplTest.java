/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.service.impl;

import com.coastshop.util.StoreOutProductInfo;
import com.coastshop.vo.OutList;
import com.coastshop.vo.StoreOut;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Coast
 */
public class StoreOutServiceImplTest {
    
    public StoreOutServiceImplTest() {
    }


    /**
     * Test of singleSnColor method, of class StoreOutServiceImpl.
     */
    @Test
    public void testSingleSnColor() {
        ArrayList<StoreOut> oldList = new ArrayList<>();
        StoreOut so;
        so = new StoreOut(1, "523061141", "03043", "1", 1, 1);
        oldList.add(so);
        so = new StoreOut(2, "523061141", "03043", "2", 2, 1);
        oldList.add(so);
        so = new StoreOut(3, "523061141", "03044", "1", 1, 2);
        oldList.add(so);
        so = new StoreOut(3, "523061141", "03044", "1", 1, 2);
        oldList.add(so);
        so = new StoreOut(3, "523061142", "03043", "1", 1, 2);
        oldList.add(so);
        so = new StoreOut(3, "523061142", "03045", "1", 1, 2);
        oldList.add(so);
        so = new StoreOut(3, "523061142", "03045", "1", 1, 2);
        oldList.add(so);
        so = new StoreOut(3, "523061142", "03044", "1", 1, 2);
        oldList.add(so);
        so = new StoreOut(3, "523061142", "03044", "1", 1, 2);
        oldList.add(so);
        StoreOutServiceImpl instance = new StoreOutServiceImpl();
        List<StoreOut> list = instance.singleSnColor(oldList);
        for (int i = 0; i < list.size(); i++) {
            
        System.out.println(list.get(i));
        }
            
    }
    
}
