/*
 * 生成出库单
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.util.StoreOutProductInfo;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Coast
 */
public class StoreOutExcelServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
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
            int listid = Integer.parseInt(request.getParameter("listid"));
            //Service
            String brand = ServiceFactory.getBrandServiceInstance().find(ServiceFactory.getOutListServiceInstance().getById(listid).getBrandid()).getName();
            String shop = ServiceFactory.getShopServiceInstance().find(ServiceFactory.getOutListServiceInstance().getById(listid).getShopid()).getName();
            List<StoreOutProductInfo> list = ServiceFactory.getStoreOutServiceInstance().findProductInfosByList(listid);
            //POI
            Workbook wb = new HSSFWorkbook();
            Map<String, CellStyle> styles = createStyles(wb);
            Sheet sheet = wb.createSheet("出库单");
            sheet.setColumnWidth(0, 256 * 16);
            sheet.setColumnWidth(1, 256 * 10);
            sheet.setColumnWidth(2, 256 * 10);
            sheet.setColumnWidth(3, 256 * 10);
            sheet.setColumnWidth(4, 256 * 10);
            sheet.setColumnWidth(5, 256 * 10);
            sheet.setColumnWidth(6, 256 * 15);//new add
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
            headCell.setCellValue(brand + " " + shop + "店 出库单" + sdf.format(new Date()));
            //title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
            Row titleRow = sheet.createRow(1);
            titleRow.setHeightInPoints(20);
            Cell titleCell0 = titleRow.createCell(0);
            titleCell0.setCellValue("款号");
            titleCell0.setCellStyle(styles.get("title"));
            Cell titleCell1 = titleRow.createCell(1);
            titleCell1.setCellValue("色号");
            titleCell1.setCellStyle(styles.get("title"));
            Cell titleCell2 = titleRow.createCell(2);
            titleCell2.setCellValue("颜色");
            titleCell2.setCellStyle(styles.get("title"));
            Cell titleCell3 = titleRow.createCell(3);
            titleCell3.setCellValue("尺码");
            titleCell3.setCellStyle(styles.get("title"));
            Cell titleCell4 = titleRow.createCell(4);
            titleCell4.setCellValue("数量");
            titleCell4.setCellStyle(styles.get("title"));
            Cell titleCell5 = titleRow.createCell(5);
            titleCell5.setCellValue("单价");
            titleCell5.setCellStyle(styles.get("title"));
            Cell titleCell6 = titleRow.createCell(6);       //new
            titleCell6.setCellValue("总价");                //new
            titleCell6.setCellStyle(styles.get("title"));   //new
            //遍历
            Iterator<StoreOutProductInfo> iter = list.iterator();
            int num_row = titleRow.getRowNum() + 1;
            while (iter.hasNext()) {
                StoreOutProductInfo info = iter.next();  //获得品类等
                if (info != null) {
                    Row row = sheet.createRow(num_row);
                    int rowNum = row.getRowNum() + 1;
                    row.setHeightInPoints(15);
                    List<String> values = new ArrayList<String>();
                    values.add(info.getSn());
                    values.add(info.getColor());
                    values.add(info.getColorType());
                    values.add(info.getLocalSize());
                    values.add(Integer.toString(info.getAmount()));
                    values.add(Integer.toString(info.getPrice()));
                    for (int col = 0; col < 7; col++) {
                        Cell cell = row.createCell(col);
                        if (col == 6) {     //formula
                            cell.setCellFormula("E" + rowNum + "*F" + rowNum);
                        } else if (col == 4 || col == 5) {
                            cell.setCellValue(Double.parseDouble(values.get(col)));
                        } else {
                            cell.setCellValue(values.get(col));
                        }
                        cell.setCellStyle(styles.get("data"));
                    }
                    num_row++;
                }
            }
            //汇总
            Row footRow = sheet.createRow(num_row);
            sheet.addMergedRegion(new CellRangeAddress(num_row, num_row, 0, 3));
            Cell sumCell0 = footRow.createCell(0);
            sumCell0.setCellValue("汇总");
            sumCell0.setCellStyle(styles.get("data"));
            Cell sumCell1 = footRow.createCell(1);
            sumCell1.setCellStyle(styles.get("data"));
            Cell sumCell2 = footRow.createCell(2);
            sumCell2.setCellStyle(styles.get("data"));
            Cell sumCell3 = footRow.createCell(3);
            sumCell3.setCellStyle(styles.get("data"));
            Cell sumCell4 = footRow.createCell(4);
            sumCell4.setCellFormula("SUM(E3:E" + (num_row) + ")");
            sumCell4.setCellStyle(styles.get("data"));
            Cell sumCell5 = footRow.createCell(5);
            sumCell5.setCellFormula("AVERAGE(F3:F" + (num_row) + ")");
            sumCell5.setCellStyle(styles.get("data"));
            Cell sumCell6 = footRow.createCell(6);
            sumCell6.setCellFormula("SUM(G3:G" + (num_row) + ")");
            sumCell6.setCellStyle(styles.get("data"));
            //刷新公式结果
            wb.getCreationHelper().createFormulaEvaluator().evaluateAll();
            wb.getSheetAt(0).setForceFormulaRecalculation(true);
            String filename = URLEncoder.encode(shop + "_" + brand + "_出库单_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls", "UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
        //End
    }

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

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
}
