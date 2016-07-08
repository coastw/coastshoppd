package com.coastshop.util;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.vo.StoreOut;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Coast
 */
public class ImportOrderUtil {

    public static int doImport(File file, int listid) throws Exception {
        List<StoreOut> products = readFromExcel(file, listid);
        return saveToDatabase(products);
    }

    private static List<StoreOut> readFromExcel(File file, int listid) throws Exception {
        ArrayList<StoreOut> products = new ArrayList<StoreOut>();
        int row = 1;
        Cell cell = null;
        try {
            Workbook wb = new HSSFWorkbook(new FileInputStream(file));
            Sheet sheet = wb.getSheetAt(0);
            while (row <= sheet.getLastRowNum()) {
                cell = sheet.getRow(row).getCell(0);
                String sn = cell.getStringCellValue();
                cell = sheet.getRow(row).getCell(1);
                String color = cell.getStringCellValue();
                cell = sheet.getRow(row).getCell(2);
                String size = cell.getStringCellValue();
                cell = sheet.getRow(row).getCell(3);
                int amount = (int) cell.getNumericCellValue();
                if (amount == 0) {
                    continue;
                }
                StoreOut product = new StoreOut();
                product.setSn(sn);
                product.setColor(color);
                product.setSize(size);
                product.setAmount(amount);
                product.setOutlistid(listid);
                products.add(product);
                row++;
            }
        } catch (Exception e) {
            System.out.println("readProductsFromMyExcel出现异常:行=" + row + "列=" + cell.getAddress());
            products = null;
            e.printStackTrace();
        } finally {
            return products;
        }
    }

    private static int saveToDatabase(List<StoreOut> products) throws Exception {
        int count = 0;
        try {
            for (StoreOut product : products) {
                int id = ServiceFactory.getStoreOutServiceInstance().add(product);
                if (id > 0) {
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return count;
        }
    }
}
