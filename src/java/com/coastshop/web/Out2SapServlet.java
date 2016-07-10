/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.util.DiscountUtil;
import com.coastshop.util.StoreOutProductInfo;
import com.coastshop.util.StoreOutProductInfoComparator;
import com.coastshop.vo.OutList;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Coast
 */
public class Out2SapServlet extends HttpServlet {

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

        OutputStream os = null;
        InputStream is = null;
        try {
            //param
            //
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(new Date());
            String shop;
            String brand;
            List<OutList> outLists;

            int shopid = Integer.parseInt(request.getParameter("shopid"));
            int brandid = Integer.parseInt(request.getParameter("brandid"));
            Date d1 = df.parse(request.getParameter("date1"));
            Date d2 = df.parse(request.getParameter("date2"));
            String distinct = request.getParameter("distinct");
            shop = ServiceFactory.getShopServiceInstance().find(shopid).getName();
            brand = ServiceFactory.getBrandServiceInstance().find(brandid).getName();
            outLists = ServiceFactory.getOutListServiceInstance().getFromDateShopIdBrandId(d1, d2, shopid, brandid);
            //空判断
            if (outLists == null) {
                response.setContentType("text/html;charset=UTF-8");
                request.setAttribute("err", "查询结果为空");
                request.getRequestDispatcher("/out2sap.jsp").forward(request, response);
                return ;
            }
            //查询StoreOut表（根据outlistid）
            os = response.getOutputStream();
            String tmpfile = request.getServletContext().getRealPath("/") + "tmp.xlsx";
            is = new FileInputStream(tmpfile);
            List<StoreOutProductInfo> soiflist = null;
            if (distinct.equals("sn")) {
                soiflist = ServiceFactory.getStoreOutServiceInstance().findProductInfosByLists(outLists);
            }
            if (distinct.equals("scs")) {
                soiflist = ServiceFactory.getStoreOutServiceInstance().findDistinctProductInfosByLists(outLists);
            }
            //Sort
            Collections.sort(soiflist, new StoreOutProductInfoComparator());

            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //POI
            Workbook wb = new XSSFWorkbook(is);
            Runtime.getRuntime().gc();
            Sheet sheet = wb.getSheetAt(0);
            //供应商名称：
            Cell supplierCell = sheet.getRow(1).getCell(1);
            supplierCell.setCellValue("北京亿合众通服饰有限公司");
            //品牌名称：
            Cell brandCell = sheet.getRow(2).getCell(1);
            brandCell.setCellValue("dribs&drabs片断");
            //Data
            if (soiflist == null) {
                System.out.println("没有数据");
                return ;
            }
            Iterator<StoreOutProductInfo> iter = soiflist.iterator();
            int row = 4;
            while (iter.hasNext()) {
                StoreOutProductInfo info = iter.next();
                if (info != null) {
                    Cell cell;
                    cell = wb.getSheetAt(0).getRow(row).getCell(0);   //sno
                    cell.setCellValue(info.getSn() + info.getColor() + info.getSize());
                    cell = wb.getSheetAt(0).getRow(row).getCell(1);   //sn
                    cell.setCellValue(info.getSn());
                    cell = wb.getSheetAt(0).getRow(row).getCell(2);   //danwei
                    cell.setCellValue("件");
                    cell = wb.getSheetAt(0).getRow(row).getCell(3);   //color
                    cell.setCellValue(info.getOriginColor());
                    cell = wb.getSheetAt(0).getRow(row).getCell(4);   //colorType
                    cell.setCellValue(info.getColorType());
                    cell = wb.getSheetAt(0).getRow(row).getCell(5);   //first
                    cell.setCellValue(info.getFirstType());
                    cell = wb.getSheetAt(0).getRow(row).getCell(6);   //second
                    cell.setCellValue(info.getSecondType());
                    cell = wb.getSheetAt(0).getRow(row).getCell(7);   //third
                    cell.setCellValue(info.getThirdType());
                    cell = wb.getSheetAt(0).getRow(row).getCell(11);  //wSize
                    cell.setCellValue(info.getLocalSize() + "(" +info.getWorldSize() + ")");
                    cell = wb.getSheetAt(0).getRow(row).getCell(14);  //season
                    cell.setCellValue(info.getFitSeason());
                    cell = wb.getSheetAt(0).getRow(row).getCell(15);  //year
                    cell.setCellValue(info.getYear() + "年");
                    cell = wb.getSheetAt(0).getRow(row).getCell(16);  //attribute
                    cell.setCellValue("无");
                    cell = wb.getSheetAt(0).getRow(row).getCell(20);  //price
                    cell.setCellValue(Integer.toString(info.getPrice()));
                    //21 now price
                    cell = wb.getSheetAt(0).getRow(row).getCell(21);
                    String persent = DiscountUtil.getDiscount(info.getSn());
                    if (persent.equals("1000%")) {
                        cell.setCellValue("###");
                    } else {
//                        String formula = "INT(U" + (row + 1) + "*" + persent + ")";
//                        cell.setCellFormula(formula);
                        cell.setCellValue((int)(info.getPrice()*DiscountUtil.percent2double(persent)));
                    }
                    cell = wb.getSheetAt(0).getRow(row).createCell(22);
                    if (cell != null){
                        cell.setCellValue(persent);
                    }
                    cell = wb.getSheetAt(0).getRow(row).createCell(23);
                    if (cell != null){
                        cell.setCellValue((double)info.getAmount());
                    }
                    //next row
                    row++;
                }
            }
            for (int i = row; i <= sheet.getLastRowNum(); i++) {
                Row useless = sheet.getRow(i);
                if (useless !=null) {
                    sheet.removeRow(useless);
                }
            }
            Runtime.getRuntime().gc();
            wb.getCreationHelper().createFormulaEvaluator().evaluateAll();
            //wb.getSheetAt(0).setForceFormulaRecalculation(true);
            //download
            String filename = URLEncoder.encode(shop + "_" + brand + "_SAP_" + date + ".xlsx", "UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            wb.write(os);
            //END DATA
            //end try{}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os!=null) {
                os.close();
            }
            if (is!=null) {
                is.close();
            }
        }
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
