/*
 * 生成报价单
 */
package com.coastshop.web;

import com.coastshop.factory.*;
import com.coastshop.util.DiscountUtil;
import com.coastshop.util.StoreOutProductInfo;
import com.coastshop.vo.*;
import java.io.*;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Coast
 */
public class BJDServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        OutputStream os = response.getOutputStream();
        try {
            /*
             * 生成报价单
             * 根据参数查询outlist表
             * TODO 出入库类型，折扣
             * @param 日期(start-end),brandid,shopid (查询list表)
             * @return 款号，原价格，折扣，折后价，年份，季节，品类 2017-7新格式：品牌，品项，款号，原价，现价，折扣，颜色，年份

             */
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(new Date());
            String shop;
            String brand;
            List<OutList> outLists;
            if (request.getParameter("listid") != null) {
                int listid = Integer.parseInt(request.getParameter("listid"));
                OutList outlist = ServiceFactory.getOutListServiceInstance().getById(listid);
                shop = ServiceFactory.getShopServiceInstance().find(outlist.getShopid()).getName();
                brand = ServiceFactory.getBrandServiceInstance().find(outlist.getBrandid()).getName();
                outLists = new ArrayList<OutList>();
                outLists.add(outlist);
            } else {
                int shopid = Integer.parseInt(request.getParameter("shopid"));
                int brandid = Integer.parseInt(request.getParameter("brandid"));
                Date d1 = df.parse(request.getParameter("date1"));
                Date d2 = df.parse(request.getParameter("date2"));
                shop = ServiceFactory.getShopServiceInstance().find(shopid).getName();
                brand = ServiceFactory.getBrandServiceInstance().find(brandid).getName();
                outLists = ServiceFactory.getOutListServiceInstance().getFromDateShopIdBrandId(d1, d2, shopid, brandid);
            }
            //查询StoreOut表（根据outlistid）
            List<StoreOutProductInfo> productInfos = ServiceFactory.getStoreOutServiceInstance().findProductInfosByLists(outLists);
            //POI
            Workbook wb = new HSSFWorkbook();
            Map<String, CellStyle> styles = createStyles(wb);
            Sheet sheet = wb.createSheet("片断报价单");
            sheet.setColumnWidth(0, 256 * 10);
            sheet.setColumnWidth(1, 256 * 10);
            sheet.setColumnWidth(2, 256 * 16);
            sheet.setColumnWidth(3, 256 * 10);
            sheet.setColumnWidth(4, 256 * 10);
            sheet.setColumnWidth(5, 256 * 10);
            sheet.setColumnWidth(6, 256 * 10);
            sheet.setColumnWidth(7, 256 * 10);
            //Title
            //head
            Row headRow = sheet.createRow(0);
            Cell headCell = headRow.createCell(0);
            //height
            headRow.setHeightInPoints(30);
            //style
            headCell.setCellStyle(styles.get("head"));
            //value
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月");
            headCell.setCellValue(brand + " " + shop + "店 报价单" + sdf.format(new Date()));
            //title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));//new 6->7，2017-7新格式：品牌，品项，款号，原价，现价，折扣，颜色，年份
            Row titleRow = sheet.createRow(1);
            titleRow.setHeightInPoints(20);
            Cell titleCell0 = titleRow.createCell(0);
            titleCell0.setCellValue("品牌");
            titleCell0.setCellStyle(styles.get("title"));
            Cell titleCell1 = titleRow.createCell(1);
            titleCell1.setCellValue("品项");
            titleCell1.setCellStyle(styles.get("title"));
            Cell titleCell2 = titleRow.createCell(2);
            titleCell2.setCellValue("款号");
            titleCell2.setCellStyle(styles.get("title"));
            Cell titleCell3 = titleRow.createCell(3);
            titleCell3.setCellValue("原价");
            titleCell3.setCellStyle(styles.get("title"));
            Cell titleCell4 = titleRow.createCell(4);
            titleCell4.setCellValue("现价");
            titleCell4.setCellStyle(styles.get("title"));
            Cell titleCell5 = titleRow.createCell(5);
            titleCell5.setCellValue("折扣");
            titleCell5.setCellStyle(styles.get("title"));
            Cell titleCell6 = titleRow.createCell(6);
            titleCell6.setCellValue("颜色");
            titleCell6.setCellStyle(styles.get("title"));
            Cell titleCell7 = titleRow.createCell(7);
            titleCell7.setCellValue("年份");
            titleCell7.setCellStyle(styles.get("title"));
            //遍历
            Iterator<StoreOutProductInfo> iter = productInfos.iterator();
            int num_row = titleRow.getRowNum() + 1;
            while (iter.hasNext()) {
                StoreOutProductInfo info = iter.next();  //获得品类等
                if (info != null) {
                    Row row = sheet.createRow(num_row);
                    row.setHeightInPoints(15);
                    List<String> values = new ArrayList<String>();
                    //2017-7新格式：品牌，品项，款号，原价，现价，折扣，颜色，年份
                    values.add("片断");
                    values.add(info.getType());
                    values.add(info.getSn());
                    //orgprice
                    int orgPrice = info.getPrice();
                    values.add(Integer.toString(info.getPrice()));
                    //now price
                    String persent = DiscountUtil.getDiscount(info.getSn());
                    NumberFormat nf = NumberFormat.getPercentInstance();
                    Number numberDiscountPersent = nf.parse(persent);
                    int nowprice = 0;
                    nowprice = (int) (orgPrice * numberDiscountPersent.floatValue());
                    values.add(Integer.toString(nowprice));
                    //discount
                    values.add(persent);
                    values.add(info.getColorType());
                    values.add(info.getYear());
                    for (int col = 0; col < 8; col++) {
                        Cell cell = row.createCell(col);
                        cell.setCellValue(values.get(col));
                        cell.setCellStyle(styles.get("data"));
                    }
                    num_row++;
                    //old----------------
                    /*
                    values.add(info.getSn());
                    values.add(Integer.toString(info.getPrice()));
                    //persent
                    String persent = DiscountUtil.getDiscount(info.getSn());
                    values.add(persent);
                    values.add("");
                    values.add(info.getYear());
                    values.add(info.getSeason());
                    values.add(info.getType());
                    
                    for (int col = 0; col < 7; col++) {
                        Cell cell = row.createCell(col);
                        if (col == 3) {
                            String formula = "INT(B" + (num_row + 1) + "*C" + (num_row + 1) + ")";
                            cell.setCellFormula(formula);
                        } else {
                            cell.setCellValue(values.get(col));
                        }
                        cell.setCellStyle(styles.get("data"));
                    }
                    num_row++;
                     */
                }
            }
            //wb.getCreationHelper().createFormulaEvaluator().evaluateAll();
//            wb.getSheetAt(0).setForceFormulaRecalculation(true);
            String filename = URLEncoder.encode(shop + "_" + brand + "_报价单_" + date + ".xls", "UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
            Runtime.getRuntime().gc();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * cell styles used for formatting calendar sheets
     */
    private Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

        CellStyle style;
        //head
        Font headFont = wb.createFont();
        headFont.setFontHeightInPoints((short) 12);
        headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(headFont);
        styles.put("head", style);

        //title
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 12);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setFont(titleFont);
        styles.put("title", style);

        //data
        Font dataFont = wb.createFont();
        dataFont.setFontHeightInPoints((short) 12);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setFont(dataFont);
        styles.put("data", style);

        return styles;
    }
}
