/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.util;

/**
 *
 * @author Coast
 */
public class ProductUtil {

    //431023120
    //512023135
    //512023135120095
    //年份1 季节1 波段1 品类2 没用4 没用6
    public static final String BARCODEREGEX = "^[1-9]{1}[1-4]{1}[0-9]{1}[0-9]{2}[0-9]{4}[0-9]{5}[1-5]{1}$";        //edited 2015.08.21 13.43
    public static final String SNREGEX = "^[1-9]{1}[1-4]{1}[0-9]{1}[0-9]{2}[0-9]{4}$";
    public static final String COLORREGEX = "^[0-9]{5}$";
    public static final String SIZEREGEX = "^[1-5]{1}$";

    public static ProductInfo getProductInfo(String sn) {
        ProductInfo pi = new ProductInfo();
        if (isValidateSn(sn)) {
            pi.setSn(sn);
            pi.setYear(getYear(sn));
            pi.setSeason(getSeason(sn));
            pi.setType(getType(sn));
            pi.setFirstType(getFirstType(sn));
            pi.setSecondType(getSecondType(sn));
            pi.setThirdType(getThirdType(sn));
            pi.setFitSeason(getFitSeason(sn));
        } else {
            pi.setSn(sn);
        }
        return pi;
    }

    public static ProductInfo getProductInfo(String sn, String color, String size) {
        ProductInfo pi = new ProductInfo();
        if (isValidateSn(sn)) {
            pi.setSn(sn);
            pi.setColor(color);
            pi.setSize(size);
            pi.setYear(getYear(sn));
            pi.setSeason(getSeason(sn));
            pi.setType(getType(sn));
            pi.setFirstType(getFirstType(sn));
            pi.setSecondType(getSecondType(sn));
            pi.setThirdType(getThirdType(sn));
            pi.setFitSeason(getFitSeason(sn));
            pi.setColorType(getColorType(color));
            pi.setLocalSize(getLocalSize(sn, size));
            pi.setWorldSize(getWorldSize(sn, size));
        } else {
            pi.setSn(sn);
            pi.setColor(color);
            pi.setSize(size);
            if (isValidateColor(color)) {
                pi.setColorType(getColorType(color));
            }
            if (isValidateSize(size)) {
                pi.setLocalSize(getLocalSize(sn, size));
                pi.setWorldSize(getWorldSize(sn, size));
            }
        }
        return pi;
    }

    public static boolean isValidateBarCode(String barCode) {
        return barCode.matches(BARCODEREGEX);
    }
    
    public static boolean isValidateSn(String sn) {
        return sn.matches(SNREGEX);
    }

    public static boolean isValidateColor(String color) {
        return color.matches(COLORREGEX);
    }

    public static boolean isValidateSize(String size) {
        return size.matches(SIZEREGEX);
    }

    public static String getYear(String sn) {
        String year;
        String ys = sn.substring(0, 1);
        if (ys.equals("1")) {
            year = "2011";
        } else if (ys.equals("2")) {
            year = "2012";
        } else if (ys.equals("3")) {
            year = "2013";
        } else if (ys.equals("4")) {
            year = "2014";
        } else if (ys.equals("5")) {
            year = "2015";
        } else if (ys.equals("6")) {
            year = "2016";
        } else if (ys.equals("7")) {
            year = "2017";
        } else if (ys.equals("8")) {
            year = "2018";
        } else if (ys.equals("9")) {
            year = "2019";
        } else {
            year = "#";
        }
        return year;
    }

    public static String getSeason(String sn) {
        String season;
        String ss = sn.substring(1, 2);
        if (ss.equals("1")) {
            season = "春";
        } else if (ss.equals("2")) {
            season = "夏";
        } else if (ss.equals("3")) {
            season = "秋";
        } else if (ss.equals("4")) {
            season = "冬";
        } else {
            season = "#";
        }
        return season;
    }

//代码	类别
//01	外套
//02	连衣裙
//03	裤子
//04	半裙
//05	风衣
//06	针织
//07	毛衫
//08	衬衫
//09	羽绒
//10	棉衣
//11	大衣
//12	罩衫
//13	棉衣
//14	皮草
//15	饰品
    public static String getType(String sn) {
        String type;
        String ts;
        ts = sn.substring(3, 5);
        if (ts.equals("01")) {
            type = "外套";
        } else if (ts.equals("02")) {
            type = "连衣裙";
        } else if (ts.equals("03")) {
            type = "裤子";
        } else if (ts.equals("04")) {
            type = "半裙";
        } else if (ts.equals("05")) {
            type = "风衣";
        } else if (ts.equals("06")) {
            type = "针织";
        } else if (ts.equals("07")) {
            type = "毛衫";
        } else if (ts.equals("08")) {
            type = "衬衫";
        } else if (ts.equals("09")) {
            type = "羽绒";
        } else if (ts.equals("10")) {
            type = "棉衣";
        } else if (ts.equals("11")) {
            type = "大衣";
        } else if (ts.equals("12")) {
            type = "罩衫";
        } else if (ts.equals("13")) {
            type = "棉衣";
        } else if (ts.equals("14")) {
            type = "皮草";
        } else if (ts.equals("15")) {
            type = "饰品";
        } else {
            type = "#";
        }
        return type;
    }

    public static String getFirstType(String sn) {
        return "女装";
    }

    public static String getSecondType(String sn) {
//        女装上装	01 05 06 07 08 09 10 11 12 13 14
//        女装裤子	03
//        裙装		02,09
//        连体衣
//        唐装
        String secondType;
        String ts;
        ts = sn.substring(3, 5);
        if (ts.equals("01") || ts.equals("05") || ts.equals("06") || ts.equals("07") || ts.equals("08") || ts.equals("09") || ts.equals("10") || ts.equals("11") || ts.equals("12") || ts.equals("13") || ts.equals("14") || ts.equals("15")) {
            secondType = "女装上装";
        } else if (ts.equals("03")) {
            secondType = "女装裤子";
        } else if (ts.equals("02") || ts.equals("04")) {
            secondType = "裙装";
        } else {
            secondType = "#";
        }
        return secondType;

    }

    /**
     * 三级品类：04上衣？ ----女装上装---- T恤 衬衣	--06-- 大衣	--12、13-- 短款皮草 风衣	--11-- 马夹 马甲
     * 毛衣/针织衫	--03-- 棉衣/棉服	--14-- 披肩 皮衣	--16-- 外套	--07-- 卫衣/绒衫	--10-- 西服 小吊带/背心
     * 雪纺衫 羽绒服	--15-- 中款皮草
     *
     * ----女装裤子---- 打底裤 短裤 牛仔裤 休闲裤	--08-- 长裤 ----裙装---- 半身裙	--09-- 吊带裙 礼服裙 连衣裙
     * --02--
     *
     * ----连体衣---- 连体衣
     *
     * ----唐装---- 男式唐装裤子 男式唐装上装 女式唐装裤子 女式唐装上装 旗袍 裙装
     *
     * @param sn
     * @return
     */
    public static String getThirdType(String sn) {
        String thirdType;
        String ts;
        ts = sn.substring(3, 5);
        if (ts.equals("08")) {
            thirdType = "衬衣";
        } else if (ts.equals("11")) {
            thirdType = "大衣";
        } else if (ts.equals("05")) {
            thirdType = "风衣";
        } else if (ts.equals("06") || ts.equals("07") || ts.equals("08") || ts.equals("12")) {
            thirdType = "毛衣/针织衫";
        } else if (ts.equals("10") || ts.equals("13")) {
            thirdType = "棉衣/棉服";
        } else if (ts.equals("14")) {
            thirdType = "皮衣";
        } else if (ts.equals("01")) {
            thirdType = "外套";
        } else if (ts.equals("12")) {
            thirdType = "卫衣/绒衫";
        } else if (ts.equals("09")) {
            thirdType = "羽绒服";
        } else if (ts.equals("03")) {
            thirdType = "休闲裤";
        } else if (ts.equals("04")) {
            thirdType = "半身裙";
        } else if (ts.equals("02")) {
            thirdType = "连衣裙";
        } else {
            thirdType = "#";
        }
        return thirdType;
    }

    public static String getFitSeason(String sn) {
        String fitSeason;
        String ss = sn.substring(1, 2);
        if (ss.equals("1") || ss.equals("2")) {
            fitSeason = "春/夏FP01";
        } else if (ss.equals("3") || ss.equals("4")) {
            fitSeason = "秋/冬FP02";
        } else {
            fitSeason = "无FP00";
        }
        return fitSeason;
    }

    //TODO
    public static String getColorType(String color) {
        String colorType;
        if (color.equals("09103") || color.equals("09099") || color.equals("12099")
                || color.equals("09103") || color.equals("09097")) {
            colorType = "白色";
        } else if (color.equals("09101") ) {
            colorType = "粉色";
        } else if (color.equals("07080") || color.equals("11080") || color.equals("12080")
                || color.equals("13080") || color.equals("07082") || color.equals("01002")
                || color.equals("07085")) {
            colorType = "黄色";
        } else if (color.equals("06076") || color.equals("10900") || color.equals("11076")
                || color.equals("12076") || color.equals("05072") || color.equals("05070")) {
            colorType = "红色";
        } else if (color.equals("10115") || color.equals("12115") || color.equals("13115")) {
            colorType = "黑色";
        } else if (color.equals("01015") || color.equals("11015") || color.equals("01004")
                || color.equals("11004") || color.equals("12004") || color.equals("01003")
                || color.equals("01014") || color.equals("13014") || color.equals("01009")
                || color.equals("11009") || color.equals("12009") || color.equals("12050")
                || color.equals("12015") || color.equals("12101") || color.equals("12036")
                || color.equals("12003")) {
            colorType = "绿色";
        } else if (color.equals("08090") || color.equals("08091") || color.equals("04064")
                || color.equals("08095") || color.equals("08093")) {
            colorType = "灰色";
        } else if (color.equals("03041") || color.equals("11041") || color.equals("12041")
                || color.equals("03043") || color.equals("11043") || color.equals("12043")
                || color.equals("13043") || color.equals("03038") || color.equals("11038")
                || color.equals("12038") || color.equals("11037") || color.equals("03052")
                || color.equals("11052") || color.equals("03050") || color.equals("11050")
                || color.equals("03036") || color.equals("11036") || color.equals("03042")) {
            colorType = "蓝色";
        } else if (color.equals("13087") || color.equals("08087") || color.equals("11087")) {
            colorType = "棕色";
        } else {
            colorType = "#";
        }
        return colorType;
    }

    //done
    //080 081 170 180
    public static String getLocalSize(String sn, String size) {
        String localSize;

        if (size.equals("1")) {
            localSize = "S";
        } else if (size.equals("2")) {
            localSize = "M";
        } else if (size.equals("3")) {
            localSize = "L";
        } else if (size.equals("4")) {
            localSize = "XL";
        } else if (size.equals("5")) {
            localSize = "XXL";
        } else {
            localSize = "#";
        }

        return localSize;
    }

    public static String getWorldSize(String sn, String size) {
        String worldSize;
        String ts;//type string 判断上衣还是裤子
        ts = sn.substring(3, 5);
        if (ts.equals("03") || ts.equals("04")) {  //kuzi
            if (size.equals("1")) {
                worldSize = "155/60A";
            } else if (size.equals("2")) {
                worldSize = "160/64A";
            } else if (size.equals("3")) {
                worldSize = "165/68A";
            } else if (size.equals("4")) {
                worldSize = "170/72A";
            } else if (size.equals("5")) {
                worldSize = "175/76A";
            } else {
                worldSize = "#";
            }
        } else { //shangyi
            if (size.equals("1")) {
                worldSize = "155/80A";
            } else if (size.equals("2")) {
                worldSize = "160/84A";
            } else if (size.equals("3")) {
                worldSize = "165/88A";
            } else if (size.equals("4")) {
                worldSize = "170/92A";
            } else if (size.equals("5")) {
                worldSize = "175/96A";
            } else {
                worldSize = "#";
            }
        }

        return worldSize;
    }

}
