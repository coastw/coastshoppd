/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Coast
 */
public class SAPUtil {

    public static SapWorkbook getWorkbook(List<StoreOutProductInfo> sopiList, String templatePath) throws Exception {
        SapWorkbook swb = new SapWorkbook();
        String msg = "";
        String err = "";
        int s = sopiList.size();
        String templatefile;
        if (s <= 300) {
            templatefile = "tmp3h.xlsx";
        } else if (s <= 1000) {
            templatefile = "tmp1k.xlsx";
        } else if (s <= 5000) {
            templatefile = "tmp5k.xlsx";
        } else {
            templatefile = "tmp.xlsx";
        }
        //String tmpfile = request.getServletContext().getRealPath("/") + templatefile;
        String tmpfile = templatePath + templatefile;
        InputStream is = new FileInputStream(tmpfile);

        Workbook wb = new XSSFWorkbook(is);
        Sheet sheet = wb.getSheetAt(0);
        //供应商名称：
        Cell supplierCell = sheet.getRow(1).getCell(1);
        supplierCell.setCellValue("北京亿合众通服饰有限公司");
        //品牌名称：
        Cell brandCell = sheet.getRow(2).getCell(1);
        brandCell.setCellValue("片断");
        //Data
        Iterator<StoreOutProductInfo> iter = sopiList.iterator();
        int row = 4;
        int sum = 0;
        while (iter.hasNext()) {
            StoreOutProductInfo info = iter.next();
            if (info != null) {
//                if (!(info.getColorType().equals("#") || info.getFirstType().equals("#") || info.getFitSeason().equals("#") || info.getSecondType().equals("#") || info.getThirdType().equals("#") || info.getWorldSize().equals("#"))) {
                Row currentRow = wb.getSheetAt(0).getRow(row);
                Cell cell;
                cell = currentRow.getCell(0);   //sno
                cell.setCellValue(info.getSn() + info.getColor() + info.getSize());
                cell = currentRow.getCell(1);   //sn
                cell.setCellValue(info.getSn());
                cell = currentRow.getCell(2);   //danwei
                cell.setCellValue("件");
                cell = currentRow.getCell(3);   //color
                cell.setCellValue(info.getOriginColor());
                cell = currentRow.getCell(4);   //colorType
                cell.setCellValue(info.getColorType());
                cell = currentRow.getCell(5);   //first
                cell.setCellValue(info.getFirstType());
                cell = currentRow.getCell(6);   //second
                cell.setCellValue(info.getSecondType());
                cell = currentRow.getCell(7);   //third
                cell.setCellValue(info.getThirdType());
                cell = currentRow.getCell(11);  //wSize
                cell.setCellValue(info.getLocalSize() + "(" + info.getWorldSize() + ")");
//                    cell = currentRow.getCell(12);  //size
//                    cell.setCellValue(info.getSize());
//                    cell = wb.getSheetAt(0).getRow(row).getCell(13);  //localsize
//                    cell.setCellValue(info.getLocalSize());
                cell = currentRow.getCell(14);  //season
                cell.setCellValue(info.getFitSeason());
                cell = currentRow.getCell(15);  //year
                cell.setCellValue(info.getYear() + "年");
                cell = currentRow.getCell(16);  //attribute
                cell.setCellValue("无");
//                cell = currentRow.getCell(19);  //联营
//                cell.setCellValue("联营");
                cell = currentRow.getCell(20);  //price
                cell.setCellValue(Integer.toString(info.getPrice()));
                //21 now price
                cell = currentRow.getCell(21);
                String persent = DiscountUtil.getDiscount(info.getSn());
                if (persent.equals("1000%")) {
                    cell.setCellValue("###");
                } else {
                    cell.setCellValue((int)(DiscountUtil.percent2double(persent)*info.getPrice()));
//                    String formula = "INT(U" + (row + 1) + "*" + persent + ")";
//                    cell.setCellFormula(formula);
                }
                //cell = currentRow.getCell(22);  //amount ==null
                cell = currentRow.createCell(22);
                if (cell != null) {
                    cell.setCellValue(persent);
                }
                cell = currentRow.createCell(23);
                if (cell != null) {
                    cell.setCellValue((double) (info.getAmount()));
                }
                    //next row
                sum += info.getAmount();
                row++;
//                } else {
//                    msg = msg + info.getSn() + "存在空信息,";
//                    System.out.println("msg: " + info.getSn() + info.getColor() + info.getSize() + " is #");
//                }
            } else {
                err = err + "对象[StoreOutProductInfo]存在null";
                System.out.println("err: 对象[StoreOutProductInfo]存在null");
            }
        }
        //Formula
        wb.getCreationHelper().createFormulaEvaluator().evaluateAll();//wb.getSheetAt(0).setForceFormulaRecalculation(true);
        swb.setWorkbook(wb);
        swb.setSum(sum);
        swb.setErr(err);
        swb.setMsg(msg);
        return swb;
    }
    
}
