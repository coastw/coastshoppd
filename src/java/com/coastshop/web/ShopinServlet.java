/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.util.DiscountUtil;
import com.coastshop.util.SAPUtil;
import com.coastshop.util.SapWorkbook;
import com.coastshop.util.StoreOutProductInfo;
import com.coastshop.util.StoreOutProductUtil;
import com.coastshop.vo.StoreOut;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
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
public class ShopinServlet extends HttpServlet {

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
        String tmpfile = request.getServletContext().getRealPath("/") + "tmp.xlsx";
        InputStream is = new FileInputStream(tmpfile);
        try {
            deal(is, os, response);
            //getSapWorkbook(request, os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
            Runtime.getRuntime().gc();
        }
    }

    public void getSapWorkbook(HttpServletRequest request, OutputStream os) {
        SapWorkbook swb = null;
        try {
            List<StoreOutProductInfo> list = ServiceFactory.getStoreOutServiceInstance().findDistinctAllProductInfos("秋水伊人");
            swb = SAPUtil.getWorkbook(list, request.getServletContext().getRealPath("/"));
            swb.getWorkbook().write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //自定义除去重复项方法
    public List<StoreOut> singleSn(List<StoreOut> list) {
        List<StoreOut> newList = new ArrayList<StoreOut>();
        Iterator<StoreOut> iter = list.iterator();
        while (iter.hasNext()) {
            StoreOut so = iter.next();
            if (!(newList.contains(so))) {  //不重复则add
                newList.add(so);
            }
        }
        return newList;
    }

    public void deal(InputStream is, OutputStream os, HttpServletResponse response) throws Exception {
        Workbook wb = null;
        Sheet sheet = null;
        List<StoreOutProductInfo> list = null;
        try {
            wb = new XSSFWorkbook(is);
            sheet = wb.getSheetAt(0);

            //供应商名称：
            Cell supplier = sheet.getRow(1).getCell(1);
            supplier.setCellValue("北京亿合众通服饰有限公司");
            //品牌名称：
            Cell brand = sheet.getRow(2).getCell(1);
            brand.setCellValue("秋水伊人");
            //data
            list = ServiceFactory.getStoreOutServiceInstance().findDistinctAllProductInfos("秋水伊人");
            Iterator<StoreOutProductInfo> iter = list.iterator();
            int row = 4;
            while (iter.hasNext()) {
                StoreOutProductInfo so = iter.next();
                String sn = so.getSn();
                String color = so.getColor();
                String size = so.getSize();
                if (StoreOutProductUtil.isValidateSn(sn)) {
                    if (!(so.getThirdType().matches("^#+") || so.getColorType().matches("^#+"))) {
                        //test
                        Row currentRow;
                        Cell cell;
                        currentRow = sheet.getRow(row);//row may be null
                        if (currentRow == null) {
                            System.out.println("MD:行不够用了" + row);
                        } else {
                            cell = currentRow.getCell(0);   //sno
                            cell.setCellValue(sn + color + size);
                            cell = currentRow.getCell(1);   //sn
                            cell.setCellValue(so.getSn());
                            cell = currentRow.getCell(2);   //danwei
                            cell.setCellValue("件");
                            cell = currentRow.getCell(3);   //color
                            cell.setCellValue(so.getColor());
                            cell = currentRow.getCell(4);   //colorType
                            cell.setCellValue(so.getColorType());
                            cell = currentRow.getCell(5);   //first
                            cell.setCellValue(so.getFirstType());
                            cell = currentRow.getCell(6);   //second
                            cell.setCellValue(so.getSecondType());
                            cell = currentRow.getCell(7);   //third
                            cell.setCellValue(so.getThirdType());
                            cell = currentRow.getCell(11);  //wSize
                            cell.setCellValue(so.getWorldSize());
                            cell = currentRow.getCell(12);  //size1
                            cell.setCellValue(so.getSize());
                            cell = currentRow.getCell(13);  //localsize
                            cell.setCellValue(so.getLocalSize());
                            cell = currentRow.getCell(14);  //season
                            cell.setCellValue(so.getFitSeason());
                            cell = currentRow.getCell(15);  //year
                            cell.setCellValue(so.getYear());
                            cell = currentRow.getCell(16);  //attribute
                            cell.setCellValue("无");
                            cell = currentRow.getCell(20);  //price
                            cell.setCellValue(Integer.toString(so.getPrice()));

                            //21 now price
                            cell = currentRow.getCell(21);
                            String persent = DiscountUtil.getDiscount(sn);
                            if (persent.equals("1000%")) {
                                cell.setCellValue("###");
                            } else {
                                String formula = "INT(U" + (row + 1) + "*" + persent + ")";
                                cell.setCellFormula(formula);
                            }
                            //next row
                            row++;
                        }

                    }
                }
            }
            sheet.setForceFormulaRecalculation(true);
            String filename = URLEncoder.encode("上品主数据.xlsx", "UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            wb.write(os);
        } catch (NullPointerException e) {
            e.printStackTrace();
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
}
