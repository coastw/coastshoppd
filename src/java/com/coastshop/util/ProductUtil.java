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
        if (yearCode.equals("1")) {
            year = "2011";
        } else if (yearCode.equals("2")) {
            year = "2012";
        } else if (yearCode.equals("3")) {
            year = "2013";
        } else if (yearCode.equals("4")) {
            year = "2014";
        } else if (yearCode.equals("5")) {
            year = "2015";
        } else if (yearCode.equals("6")) {
            year = "2016";
        } else if (yearCode.equals("7")) {
            year = "2017";
        } else if (yearCode.equals("8")) {
            year = "2018";
        } else if (yearCode.equals("9")) {
            year = "2019";
        } else {
            year = "#" + yearCode;
        }
        return year;
    }

    public static String getSeason(String sn) {
        String season;
        String seasonCode = sn.substring(1, 2);
        if (seasonCode.equals("1")) {
            season = "春";
        } else if (seasonCode.equals("2")) {
            season = "夏";
        } else if (seasonCode.equals("3")) {
            season = "秋";
        } else if (seasonCode.equals("4")) {
            season = "冬";
        } else {
            season = "#" + seasonCode;
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
        if (typeCode.equals("01")) {
            type = "外套";
        } else if (typeCode.equals("02")) {
            type = "连衣裙";
        } else if (typeCode.equals("03")) {
            type = "裤子";
        } else if (typeCode.equals("04")) {
            type = "半裙";
        } else if (typeCode.equals("05")) {
            type = "风衣";
        } else if (typeCode.equals("06")) {
            type = "T恤";
        } else if (typeCode.equals("07")) {
            type = "毛衫";
        } else if (typeCode.equals("08")) {
            type = "衬衫";
        } else if (typeCode.equals("09")) {
            type = "羽绒";
        } else if (typeCode.equals("11")) {
            type = "大衣";
        } else if (typeCode.equals("13")) {
            type = "棉衣";
        } else if (typeCode.equals("14")) {
            type = "皮草";
        } else if (typeCode.equals("15")) {
            type = "饰品";
        } else {
            type = "#" + typeCode;
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
        if (typeCode.equals("01") || typeCode.equals("05") || typeCode.equals("06")
                || typeCode.equals("07") || typeCode.equals("08") || typeCode.equals("09")
                || typeCode.equals("11") || typeCode.equals("13") || typeCode.equals("14")) {
            secondType = "女装上装";
        } else if (typeCode.equals("03")) {
            secondType = "女装裤子";
        } else if (typeCode.equals("02") || typeCode.equals("04")) {
            secondType = "裙装";
        } else {
            secondType = "#" + typeCode;
        }
        return secondType;

    }

    public static String getThirdType(String sn) {
        String thirdType;
        String typeCode;
        typeCode = sn.substring(3, 6);
        if (typeCode.equals("037")) {
            thirdType = "打底裤";
        } else if (typeCode.equals("031")) {
            thirdType = "短裤";
        } else if (typeCode.equals("032") || typeCode.equals("034")
                || typeCode.equals("035") || typeCode.equals("036")) {
            thirdType = "休闲裤";
        } else if (typeCode.equals("033")) {
            thirdType = "长裤";
        } else if (typeCode.equals("041") || typeCode.equals("042")
                || typeCode.equals("043")) {
            thirdType = "半身裙";
        } else if (typeCode.equals("021") || typeCode.equals("022")
                || typeCode.equals("023") || typeCode.equals("024")
                || typeCode.equals("025")) {
            thirdType = "连衣裙";
        } else if (typeCode.equals("061") || typeCode.equals("062")
                || typeCode.equals("063") || typeCode.equals("064")
                || typeCode.equals("065")) {
            thirdType = "T恤";
        } else if (typeCode.equals("081") || typeCode.equals("082")) {
            thirdType = "衬衣";
        } else if (typeCode.equals("111") || typeCode.equals("112")
                || typeCode.equals("113")) {
            thirdType = "大衣";
        } else if (typeCode.equals("051") || typeCode.equals("052")) {
            thirdType = "风衣";
        } else if (typeCode.equals("071") || typeCode.equals("072")
                || typeCode.equals("073") || typeCode.equals("074")) {
            thirdType = "毛衣/针织衫";
        } else if (typeCode.equals("131") || typeCode.equals("132")
                || typeCode.equals("133")) {
            thirdType = "棉衣/棉服";
        } else if (typeCode.equals("011") || typeCode.equals("012")
                || typeCode.equals("013")) {
            thirdType = "外套";
        } else if (typeCode.equals("091") || typeCode.equals("092")
                || typeCode.equals("093")) {
            thirdType = "羽绒服";
        } else if (typeCode.equals("141")) {
            thirdType = "中款皮草";
        } else {
            thirdType = "#" + typeCode;
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

    //获取吊牌颜色
    //TODO 读取配置文件
    public static String getOriginColor(String color) {
        String originColor;
        if (color.equals("01002")) {
            originColor = "柳黄";
        } else if (color.equals("01003")) {
            originColor = "柳绿";
        } else if (color.equals("01004")) {
            originColor = "军绿";
        } else if (color.equals("01009")) {
            originColor = "植物绿";
        } else if (color.equals("01014")) {
            originColor = "绿色";
        } else if (color.equals("01015")) {
            originColor = "豆绿";
        } else if (color.equals("03036")) {
            originColor = "天蓝";
        } else if (color.equals("03038")) {
            originColor = "靛蓝";
        } else if (color.equals("03041")) {
            originColor = "宝蓝";
        } else if (color.equals("03042")) {
            originColor = "牛仔蓝";
        } else if (color.equals("03043")) {
            originColor = "藏青";
        } else if (color.equals("03050")) {
            originColor = "水蓝";
        } else if (color.equals("03052")) {
            originColor = "湖蓝";
        } else if (color.equals("04064")) {
            originColor = "藕色";
        } else if (color.equals("05070")) {
            originColor = "咖色";
        } else if (color.equals("05072")) {
            originColor = "西瓜红";
        } else if (color.equals("06076")) {
            originColor = "红色";
        } else if (color.equals("07080")) {
            originColor = "桔色";
        } else if (color.equals("07082")) {
            originColor = "亮黄";
        } else if (color.equals("07085")) {
            originColor = "土黄";
        } else if (color.equals("08087")) {
            originColor = "卡其";
        } else if (color.equals("08090")) {
            originColor = "灰色";
        } else if (color.equals("08091")) {
            originColor = "墨灰";
        } else if (color.equals("08093")) {
            originColor = "浅灰";
        } else if (color.equals("08095")) {
            originColor = "深灰";
        } else if (color.equals("09099")) {
            originColor = "米色";
        } else if (color.equals("09101")) {
            originColor = "粉桔";
        } else if (color.equals("09103")) {
            originColor = "象牙白";
        } else if (color.equals("10115")) {
            originColor = "黑色";
        } else if (color.equals("10900")) {
            originColor = "红色格";
        } else if (color.equals("11004")) {
            originColor = "军绿格";
        } else if (color.equals("11009")) {
            originColor = "植物绿格";
        } else if (color.equals("11015")) {
            originColor = "豆绿格";
        } else if (color.equals("11036")) {
            originColor = "天蓝格";
        } else if (color.equals("11037")) {
            originColor = "靛青格";
        } else if (color.equals("11038")) {
            originColor = "靛蓝格";
        } else if (color.equals("11041")) {
            originColor = "宝蓝格";
        } else if (color.equals("11043")) {
            originColor = "藏青格";
        } else if (color.equals("11050")) {
            originColor = "水蓝格";
        } else if (color.equals("11052")) {
            originColor = "湖蓝格";
        } else if (color.equals("11076")) {
            originColor = "红色格";
        } else if (color.equals("11080")) {
            originColor = "桔色格";
        } else if (color.equals("11087")) {
            originColor = "卡其格";
        } else if (color.equals("12003")) {
            originColor = "柳绿花";
        } else if (color.equals("12004")) {
            originColor = "军绿花";
        } else if (color.equals("12009")) {
            originColor = "植物绿花";
        } else if (color.equals("12015")) {
            originColor = "豆绿花";
        } else if (color.equals("12036")) {
            originColor = "天蓝花";
        } else if (color.equals("12038")) {
            originColor = "靛蓝花";
        } else if (color.equals("12041")) {
            originColor = "宝蓝花";
        } else if (color.equals("12043")) {
            originColor = "藏青花";
        } else if (color.equals("12050")) {
            originColor = "水蓝花";
        } else if (color.equals("12076")) {
            originColor = "红色花";
        } else if (color.equals("12080")) {
            originColor = "桔色花";
        } else if (color.equals("12099")) {
            originColor = "米色花";
        } else if (color.equals("12115")) {
            originColor = "黑色花";
        } else if (color.equals("13014")) {
            originColor = "绿色条";
        } else if (color.equals("13043")) {
            originColor = "藏青条";
        } else if (color.equals("13080")) {
            originColor = "桔色条";
        } else if (color.equals("13087")) {
            originColor = "卡其条";
        } else if (color.equals("13115")) {
            originColor = "黑色条";
        } else if (color.equals("09097")) {
            originColor = "米白";
        } else if (color.equals("09100")) {
            originColor = "蜡粉";
        } else if (color.equals("12101")) {
            originColor = "粉桔花";
        } else if (color.equals("06079")) {
            originColor = "酒红";
        } else if (color.equals("13095")) {
            originColor = "灰色条";
        } else if (color.equals("11115")) {
            originColor = "黑色格";
        } else if (color.equals("04066")) {
            originColor = "桃红";
        } else if (color.equals("07083")) {
            originColor = "黄色";
        } else if (color.equals("13083")) {
            originColor = "黄色条";
        } else if (color.equals("07081")) {
            originColor = "娥黄";
        } else if (color.equals("08088")) {
            originColor = "姜黄";
        } else if (color.equals("01012")) {
            originColor = "草绿";
        } else if (color.equals("09106")) {
            originColor = "裸粉";
        } else if (color.equals("12002")) {
            originColor = "柳黄花";
        } else if (color.equals("13015")) {
            originColor = "豆绿条";
        } else if (color.equals("11075")) {
            originColor = "玫红格";
        } else {
            originColor = color;
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
        } else //shangyi
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

        return worldSize;
    }

}
