/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.util;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.vo.Discount;
import java.util.List;

/**
 * class Discount{ String category String regex String persent }
 *
 * @author Coast
 */
public class DiscountUtil {

    public static String getDiscount(String sn) throws Exception {
        String persent = "1000%";
        List<Discount> list = ServiceFactory.getDiscountServiceInstance().getAll();
        for (Discount d : list) {
            if (sn.matches(d.getRegex())) {
                return d.getPersent();
            }
        }
        return persent;
    }

    public static double percent2double(String percent) {
        String p = percent.substring(0, percent.length() - 1);
        double d = Double.valueOf(p) / 100;
        return d;
    }
}
