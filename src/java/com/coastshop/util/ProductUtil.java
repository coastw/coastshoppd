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
    //年份1 季节1 波段1 品类3 没用3 色码5 尺码1
    public static final String BARCODEREGEX = "^[1-9]{1}[1-4]{1}[0-9]{1}[0-9]{3}[0-9]{3}[0-9]{5}[1-5]{1}$";        //sn color size
    public static final String SNREGEX = "^[1-9]{1}[1-4]{1}[0-9]{1}[0-9]{3}[0-9]{3}$";  //sn
    public static final String COLORREGEX = "^[0-9]{5}$"; //color
    public static final String SIZEREGEX = "^[1-5]{1}$";    //size
    public static final String[] SIZE_STRINGS = {"1","2","3","4","5"};

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
            pi.setOriginColor(getOriginColor(color));
        } else {
            pi.setSn(sn);
            pi.setColor(color);
            pi.setSize(size);
            if (isValidateColor(color)) {
                pi.setColorType(getColorType(color));
                pi.setOriginColor(getOriginColor(color));
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
        String yearCode = sn.substring(0, 1);
        switch (yearCode) {
            case "1":
                year = "2011";
                break;
            case "2":
                year = "2012";
                break;
            case "3":
                year = "2013";
                break;
            case "4":
                year = "2014";
                break;
            case "5":
                year = "2015";
                break;
            case "6":
                year = "2016";
                break;
            case "7":
                year = "2017";
                break;
            case "8":
                year = "2018";
                break;
            case "9":
                year = "2019";
                break;
            default:
                year = "#" + yearCode;
                break;
        }
        return year;
    }

    public static String getSeason(String sn) {
        String season;
        String seasonCode = sn.substring(1, 2);
        switch (seasonCode) {
            case "1":
                season = "春";
                break;
            case "2":
                season = "夏";
                break;
            case "3":
                season = "秋";
                break;
            case "4":
                season = "冬";
                break;
            default:
                season = "#" + seasonCode;
                break;
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
    /*
    前两位取大类，总3为表示小类
     */
    public static String getType(String sn) {
        String type;
        String typeCode;
        typeCode = sn.substring(3, 5);
        switch (typeCode) {
            case "01":
                type = "外套";
                break;
            case "02":
                type = "连衣裙";
                break;
            case "03":
                type = "裤子";
                break;
            case "04":
                type = "半裙";
                break;
            case "05":
                type = "风衣";
                break;
            case "06":
                type = "T恤";
                break;
            case "07":
                type = "毛衫";
                break;
            case "08":
                type = "衬衫";
                break;
            case "09":
                type = "羽绒";
                break;
            case "11":
                type = "大衣";
                break;
            case "13":
                type = "棉衣";
                break;
            case "14":
                type = "皮草";
                break;
            case "15":
                type = "饰品";
                break;
            default:
                type = "#" + typeCode;
                break;
        }
        return type;
    }

    public static String getFirstType(String sn) {
        return "女装";
    }

    public static String getSecondType(String sn) {
        String secondType;
        String typeCode;
        typeCode = sn.substring(3, 5);
        switch (typeCode) {
            case "01":
            case "05":
            case "06":
            case "07":
            case "08":
            case "09":
            case "11":
            case "13":
            case "14":
                secondType = "女装上装";
                break;
            case "03":
                secondType = "女装裤子";
                break;
            case "02":
            case "04":
                secondType = "裙装";
                break;
            default:
                secondType = "#" + typeCode;
                break;
        }
        return secondType;

    }

    public static String getThirdType(String sn) {
        String thirdType;
        String typeCode;
        typeCode = sn.substring(3, 6);
        switch (typeCode) {
            case "037":
                thirdType = "打底裤";
                break;
            case "031":
                thirdType = "短裤";
                break;
            case "032":
            case "034":
            case "035":
            case "036":
                thirdType = "休闲裤";
                break;
            case "033":
                thirdType = "长裤";
                break;
            case "041":
            case "042":
            case "043":
                thirdType = "半身裙";
                break;
            case "021":
            case "022":
            case "023":
            case "024":
            case "025":
                thirdType = "连衣裙";
                break;
            case "061":
            case "062":
            case "063":
            case "064":
            case "065":
                thirdType = "T恤";
                break;
            case "081":
            case "082":
                thirdType = "衬衣";
                break;
            case "111":
            case "112":
            case "113":
                thirdType = "大衣";
                break;
            case "051":
            case "052":
                thirdType = "风衣";
                break;
            case "071":
            case "072":
            case "073":
            case "074":
                thirdType = "毛衣/针织衫";
                break;
            case "131":
            case "132":
            case "133":
                thirdType = "棉衣/棉服";
                break;
            case "011":
            case "012":
            case "013":
                thirdType = "外套";
                break;
            case "091":
            case "092":
            case "093":
                thirdType = "羽绒服";
                break;
            case "141":
                thirdType = "中款皮草";
                break;
            default:
                thirdType = "#" + typeCode;
                break;
        }
        return thirdType;
    }

    public static String getFitSeason(String sn) {
        String fitSeason;
        String ss = sn.substring(1, 2);
        switch (ss) {
            case "1":
            case "2":
                fitSeason = "春/夏FP01";
                break;
            case "3":
            case "4":
                fitSeason = "秋/冬FP02";
                break;
            default:
                fitSeason = "无FP00";
                break;
        }
        return fitSeason;
    }

    //获取吊牌颜色
    //TODO 读取配置文件
    public static String getOriginColor(String color) {
        String originColor;
        switch (color) {
            case "01002":
                originColor = "柳黄";
                break;
            case "01003":
                originColor = "柳绿";
                break;
            case "01004":
                originColor = "军绿";
                break;
            case "01009":
                originColor = "植物绿";
                break;
            case "01014":
                originColor = "绿色";
                break;
            case "01015":
                originColor = "豆绿";
                break;
            case "03036":
                originColor = "天蓝";
                break;
            case "03038":
                originColor = "靛蓝";
                break;
            case "03041":
                originColor = "宝蓝";
                break;
            case "03042":
                originColor = "牛仔蓝";
                break;
            case "03043":
                originColor = "藏青";
                break;
            case "03050":
                originColor = "水蓝";
                break;
            case "03052":
                originColor = "湖蓝";
                break;
            case "04064":
                originColor = "藕色";
                break;
            case "05070":
                originColor = "咖色";
                break;
            case "05072":
                originColor = "西瓜红";
                break;
            case "06076":
                originColor = "红色";
                break;
            case "07080":
                originColor = "桔色";
                break;
            case "07082":
                originColor = "亮黄";
                break;
            case "07085":
                originColor = "土黄";
                break;
            case "08087":
                originColor = "卡其";
                break;
            case "08090":
                originColor = "灰色";
                break;
            case "08091":
                originColor = "墨灰";
                break;
            case "08093":
                originColor = "浅灰";
                break;
            case "08095":
                originColor = "深灰";
                break;
            case "09099":
                originColor = "米色";
                break;
            case "09101":
                originColor = "粉桔";
                break;
            case "09103":
                originColor = "象牙白";
                break;
            case "10115":
                originColor = "黑色";
                break;
            case "10900":
                originColor = "红色格";
                break;
            case "11004":
                originColor = "军绿格";
                break;
            case "11009":
                originColor = "植物绿格";
                break;
            case "11015":
                originColor = "豆绿格";
                break;
            case "11036":
                originColor = "天蓝格";
                break;
            case "11037":
                originColor = "靛青格";
                break;
            case "11038":
                originColor = "靛蓝格";
                break;
            case "11041":
                originColor = "宝蓝格";
                break;
            case "11043":
                originColor = "藏青格";
                break;
            case "11050":
                originColor = "水蓝格";
                break;
            case "11052":
                originColor = "湖蓝格";
                break;
            case "11076":
                originColor = "红色格";
                break;
            case "11080":
                originColor = "桔色格";
                break;
            case "11087":
                originColor = "卡其格";
                break;
            case "12003":
                originColor = "柳绿花";
                break;
            case "12004":
                originColor = "军绿花";
                break;
            case "12009":
                originColor = "植物绿花";
                break;
            case "12015":
                originColor = "豆绿花";
                break;
            case "12036":
                originColor = "天蓝花";
                break;
            case "12038":
                originColor = "靛蓝花";
                break;
            case "12041":
                originColor = "宝蓝花";
                break;
            case "12043":
                originColor = "藏青花";
                break;
            case "12050":
                originColor = "水蓝花";
                break;
            case "12076":
                originColor = "红色花";
                break;
            case "12080":
                originColor = "桔色花";
                break;
            case "12099":
                originColor = "米色花";
                break;
            case "12115":
                originColor = "黑色花";
                break;
            case "13014":
                originColor = "绿色条";
                break;
            case "13043":
                originColor = "藏青条";
                break;
            case "13080":
                originColor = "桔色条";
                break;
            case "13087":
                originColor = "卡其条";
                break;
            case "13115":
                originColor = "黑色条";
                break;
            case "09097":
                originColor = "米白";
                break;
            case "09100":
                originColor = "蜡粉";
                break;
            case "12101":
                originColor = "粉桔花";
                break;
            case "06079":
                originColor = "酒红";
                break;
            case "13095":
                originColor = "灰色条";
                break;
            case "11115":
                originColor = "黑色格";
                break;
            case "04066":
                originColor = "桃红";
                break;
            case "07083":
                originColor = "黄色";
                break;
            case "13083":
                originColor = "黄色条";
                break;
            case "07081":
                originColor = "娥黄";
                break;
            case "08088":
                originColor = "姜黄";
                break;
            case "01012":
                originColor = "草绿";
                break;
            case "09106":
                originColor = "裸粉";
                break;
            case "12002":
                originColor = "柳黄花";
                break;
            case "13015":
                originColor = "豆绿条";
                break;
            case "11075":
                originColor = "玫红格";
                break;
            default:
                originColor = color;
                break;
        }
        return originColor;
    }

    public static String getColorType(String color) {
        String colorType;
        if (color.equals("09103") || color.equals("09099") || color.equals("09097")) {
            colorType = "白色";
        } else if (color.equals("10115")) {
            colorType = "黑色";
        } else if (color.equals("09101")) {
            colorType = "桔色";
        } else if (color.equals("08087")) {
            colorType = "棕色";
        } else if (color.equals("09106") || color.equals("09100")) {
            colorType = "粉色";
        } else if (color.equals("10115") || color.equals("12115") || color.equals("13115")) {
            colorType = "黑色";
        } else if (color.matches("^[0]{1}[7]{1}[0-9]{3}$")) {
            colorType = "黄色";
        } else if (color.matches("^[0]{1}[4]{1}[0-9]{3}$")) {
            colorType = "紫色";
        } else if (color.matches("^[0]{1}[5]{1}[0-9]{3}$") || color.matches("^[0]{1}[6]{1}[0-9]{3}$")) {
            colorType = "红色";
        } else if (color.matches("^[0]{1}[1]{1}[0-9]{3}$")) {
            colorType = "绿色";
        } else if (color.matches("^[0]{1}[8]{1}[0-9]{3}$")) {
            colorType = "灰色";
        } else if (color.matches("^[0]{1}[3]{1}[0-9]{3}$")) {
            colorType = "蓝色";
        } else if (color.matches("^[1]{1}[1]{1}[0-9]{3}$") || color.matches("^[1]{1}[2]{1}[0-9]{3}$")
                || color.matches("^[1]{1}[3]{1}[0-9]{3}$")) {
            colorType = "花色";
        } else {
            colorType = "#" + color;
        }
        return colorType;
    }

    //done
    //080 081 170 180
    public static String getLocalSize(String sn, String size) {
        String localSize;

        switch (size) {
            case "1":
                localSize = "S";
                break;
            case "2":
                localSize = "M";
                break;
            case "3":
                localSize = "L";
                break;
            case "4":
                localSize = "XL";
                break;
            case "5":
                localSize = "XXL";
                break;
            default:
                localSize = "#";
                break;
        }

        return localSize;
    }

    public static String getWorldSize(String sn, String size) {
        String worldSize;
        String ts;//type string 判断上衣还是裤子
        ts = sn.substring(3, 5);
        if (ts.equals("03") || ts.equals("04")) {  //kuzi
            switch (size) {
                case "1":
                    worldSize = "155/60A";
                    break;
                case "2":
                    worldSize = "160/64A";
                    break;
                case "3":
                    worldSize = "165/68A";
                    break;
                case "4":
                    worldSize = "170/72A";
                    break;
                case "5":
                    worldSize = "175/76A";
                    break;
                default:
                    worldSize = "#"+ts;
                    break;
            }
        } else //shangyi
            switch (size) {
                case "1":
                    worldSize = "155/80A";
                    break;
                case "2":
                    worldSize = "60/84A";
                    break;
                case "3":
                    worldSize = "165/88A";
                    break;
                case "4":
                    worldSize = "170/92A";
                    break;
                case "5":
                    worldSize = "175/96A";
                    break;
                default:
                    worldSize = "#"+ts;
                    break;
            }
        return worldSize;
    }

}
