/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.util;

/**
 *
 * @author Coast
 */                                    //   1      3       1                   1 0 2 0 3 8
public class ProductUtil_YSLY {           //   1      3       3         C   1     0 2 0 0 3 5   "^[1]{1}[0-9]{1}[1-8]{1}[0-9a-zA-Z]?[0-9]{6,7}$";
    //    1      3       4     2   S         1 2 2 0 6 2   "^[1]{1}[0-9]{1}[1-8]{1}[a-zA-Z]?[0-9]{6,7}[0-9]{3}[0][1-5]$";

    public static final String SNREGEX = "^[1]{1}[0-9]{1}[1-8]{1}[0-9]{1}[0-9]{5}$|^[1]{1}[0-9]{1}[1-8]{1}[a-zA-Z]{1}[0-9]{7}$";
    public static final String COLORREGEX = "^[0-9]{3}";
    public static final String SIZEREGEX = "^[0][1-5]$";
    public static final String SNOREGEX = "(^[1]{1}[0-9]{1}[1-8]{1}[0-9]{1}[0-9]{5}|^[1]{1}[0-9]{1}[1-8]{1}[a-zA-Z]{1}[0-9]{7})[0-9]{3}[0][1-5]$";

//    public static final String SNREGEX = "^[1]{1}[0-9]{1}[1-8]{1}[0-9a-zA-Z]{0,2}[0-9]{6,7}$";
//    public static final String COLORREGEX = "^[0-9]{3}";
//    public static final String SIZEREGEX = "^[0][1-5]$";
//    public static final String SNOREGEX = "^[1]{1}[0-9]{1}[1-8]{1}[0-9a-zA-Z]{0,2}[0-9]{6,7}[0-9]{3}[0][1-5]$";
    public static ProductInfo getProductInfo(String sn) {
        ProductInfo pi = new ProductInfo();
        if (isValidate(sn)) {
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
        if (isValidate(sn)) {
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
        }
        return pi;
    }

    public static boolean isValidate(String sn) {
        //String regex = "^[1]{1}[0-9]{1}[1-8]{1}[0-9]{1}[0-1]{1}[0-9]{1}[0-9]{3}$";
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
        String ys = sn.substring(0, 2);
        if (ys.equals("10")) {
            year = "2010";
        } else if (ys.equals("11")) {
            year = "2011";
        } else if (ys.equals("12")) {
            year = "2012";
        } else if (ys.equals("13")) {
            year = "2013";
        } else if (ys.equals("14")) {
            year = "2014";
        } else if (ys.equals("15")) {
            year = "2015";
        } else if (ys.equals("16")) {
            year = "2016";
        } else if (ys.equals("17")) {
            year = "2017";
        } else if (ys.equals("18")) {
            year = "2018";
        } else if (ys.equals("19")) {
            year = "2009";
        } else {
            year = "#";
        }
        return year;
    }

    public static String getSeason(String sn) {
        String season;
        String ss = sn.substring(2, 3);
        if (ss.equals("1") || ss.equals("5")) {
            season = "春";
        } else if (ss.equals("2") || ss.equals("6")) {
            season = "夏";
        } else if (ss.equals("3") || ss.equals("7")) {
            season = "秋";
        } else if (ss.equals("4") || ss.equals("8")) {
            season = "冬";
        } else {
            season = "#";
        }
        return season;
    }

    public static String getType(String sn) {
        String type;
        String ts = null;
        ts = sn.substring(4, 5);
        if (ts.equals("5")) {           //used
            type = "连衣裙";
        //} else if (ts.equals("03")) {
            //    type = "梭织上衣";
        } else if (ts.equals("1")) {    //used
            type = "上衣";
//        } else if (ts.equals("05")) {
//            type = "针织上衣";
//        } else if (ts.equals("06")) {
//            type = "衬衫";
//        } else if (ts.equals("07")) {
//            type = "外套";
        } else if (ts.equals("6")) {   //used
            type = "裤子";
        } else if (ts.equals("3")) {   //used
            type = "裙子";
//        } else if (ts.equals("10")) {
//            type = "毛衫";
//        } else if (ts.equals("11")) {
//            type = "风衣";
//        } else if (ts.equals("12")) {
//            type = "长大衣";
//        } else if (ts.equals("13")) {
//            type = "短大衣";
//        } else if (ts.equals("14")) {
//            type = "棉衣";
//        } else if (ts.equals("15")) {
//            type = "羽绒服";
//        } else if (ts.equals("16")) {
//            type = "皮衣";
//        } else if (ts.equals("17")) {
//            type = "牛仔裤";
//        } else if (ts.equals("18")) {
//            type = "休闲裤";
        } else {
            type = "#";
        }
        return type;
    }

    public static String getFirstType(String sn) {
        return "女装";
    }

    public static String getSecondType(String sn) {
//        女装上装	03,06,07,10,11,12,13,14,15,16 (04?)
//        女装裤子	08,18
//        裙装		02,09
//        连体衣
//        唐装
        String secondType;
        String ts = null;
        //判断位数
        ts = sn.substring(4, 5);

        if ( ts.equals("1") ) {
            secondType = "女装上装";
        } else if ( ts.equals("6") ) {
            secondType = "女装裤子";
        } else if (ts.equals("3") || ts.equals("5")) {
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
        String ts = null;
        //判断位数
        if (sn.length() == 9) {
            ts = sn.substring(4, 6);
        } else {
            ts = sn.substring(5, 7);
        }
        if (ts.equals("06")) {
            thirdType = "衬衣";
        } else if (ts.equals("12") || ts.equals("13")) {
            thirdType = "大衣";
        } else if (ts.equals("11")) {
            thirdType = "风衣";
        } else if (ts.equals("03") || ts.equals("05")) {
            thirdType = "毛衣/针织衫";
        } else if (ts.equals("04")) {
            thirdType = "雪纺衫";
        } else if (ts.equals("14")) {
            thirdType = "棉衣/棉服";
        } else if (ts.equals("16")) {
            thirdType = "皮衣";
        } else if (ts.equals("07")) {
            thirdType = "外套";
        } else if (ts.equals("10")) {
            thirdType = "卫衣/绒衫";
        } else if (ts.equals("15")) {
            thirdType = "羽绒服";
        } else if (ts.equals("08") || ts.equals("17") || ts.equals("18")) {
            thirdType = "休闲裤";
        } else if (ts.equals("09")) {
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
        String ss = sn.substring(2, 3);
        if (ss.equals("1") || ss.equals("5") || ss.equals("2") || ss.equals("6")) {
            fitSeason = "春/夏FP01";
        } else if (ss.equals("3") || ss.equals("7") || ss.equals("4") || ss.equals("8")) {
            fitSeason = "秋/冬FP02";
        } else {
            fitSeason = "无FP00";
        }
        return fitSeason;
    }

    //TODO
    public static String getColorType(String color) {
        String colorType;
        if (color.equals("010") || color.equals("011") || color.equals("012")
                || color.equals("013") || color.equals("014")) {
            colorType = "白色";
        } else if (color.equals("030") || color.equals("031") || color.equals("032") || color.equals("033") || color.equals("035")
                || color.equals("101") || color.equals("102") || color.equals("103") || color.equals("104")
                || color.equals("105") || color.equals("112")
                || color.equals("115") || color.equals("116")) {
            colorType = "黄色";
        } else if (color.equals("081") || color.equals("082") || color.equals("083")) {
            colorType = "紫色";
        } else if (color.equals("041") || color.equals("042") || color.equals("043")
                || color.equals("044") || color.equals("045") || color.equals("046")
                || color.equals("047") || color.equals("048") || color.equals("049") || color.equals("040")) {
            colorType = "红色";
        } else if (color.equals("020") || color.equals("021") || color.equals("022")
                || color.equals("023") || color.equals("111") || color.equals("029")) {
            colorType = "黑色";
        } else if (color.equals("051") || color.equals("052") || color.equals("053")
                || color.equals("054") || color.equals("055") || color.equals("056")) {
            colorType = "绿色";
        } else if (color.equals("070") || color.equals("071") || color.equals("072")
                || color.equals("073") || color.equals("074") || color.equals("113")) {
            colorType = "灰色";
        } else if (color.equals("060") || color.equals("061") || color.equals("062")
                || color.equals("063") || color.equals("064") || color.equals("065")) {
            colorType = "蓝色";
        } else if (color.equals("091") || color.equals("092") || color.equals("093")) {
            colorType = "棕色";
        } else {
            colorType = "#";
        }
        return colorType;
    }

    public static String getLocalSize(String sn, String size) {
        String localSize;
        String ts = sn.substring(4, 6);//判断上装还是裤子
        if (ts.equals("08") || ts.equals("09") || ts.equals("18")) {  //kuzi
            if (size.equals("01")) {
                localSize = "S";
            } else if (size.equals("02")) {
                localSize = "M";
            } else if (size.equals("03")) {
                localSize = "L";
            } else if (size.equals("04")) {
                localSize = "XL";
            } else if (size.equals("05")) {
                localSize = "XXL";
            } else {
                localSize = "#";
            }
        } else { //shangyi
            if (size.equals("01")) {
                localSize = "S";
            } else if (size.equals("02")) {
                localSize = "M";
            } else if (size.equals("03")) {
                localSize = "L";
            } else if (size.equals("04")) {
                localSize = "XL";
            } else if (size.equals("05")) {
                localSize = "XXL";
            } else {
                localSize = "#";
            }
        }

        return localSize;
    }

    public static String getWorldSize(String sn, String size) {
        String worldSize;
        String ts = sn.substring(4, 6);//判断上一还是裤子
        if (ts.equals("08") || ts.equals("09") || ts.equals("18")) {  //kuzi
            if (size.equals("01")) {
                worldSize = "155/60A";
            } else if (size.equals("02")) {
                worldSize = "160/64A";
            } else if (size.equals("03")) {
                worldSize = "165/68A";
            } else if (size.equals("04")) {
                worldSize = "170/72A";
            } else if (size.equals("05")) {
                worldSize = "175/76A";
            } else {
                worldSize = "#";
            }
        } else { //shangyi
            if (size.equals("01")) {
                worldSize = "155/80A";
            } else if (size.equals("02")) {
                worldSize = "160/84A";
            } else if (size.equals("03")) {
                worldSize = "165/88A";
            } else if (size.equals("04")) {
                worldSize = "170/92A";
            } else if (size.equals("05")) {
                worldSize = "175/96A";
            } else {
                worldSize = "#";
            }
        }

        return worldSize;
    }

    public static boolean checkSNO(String sno) {
        int len = sno.length();
        if (len != 14 || len != 16) {
            return false;
        }
        String sn = null;
        String color = null;
        String size = null;
        if (len == 14) {
            //  133102051 0  2  0   0  2
            //  012345678 9  10 11  12 13
            sn = sno.substring(0, 9);
            color = sno.substring(9, 12);
            size = sno.substring(12, 14);
            /*
             System.out.println("sno=" + sno);
             System.out.println("sn=" + sn);
             System.out.println("color=" + color);
             System.out.println("size=" + size);
             */
        }
        if (len == 16) {
            sn = sno.substring(0, 11);
            color = sno.substring(11, 14);
            size = sno.substring(14, 16);
        }

        return isValidate(sn) && isValidateColor(color) && isValidateSize(size);
    }
}
