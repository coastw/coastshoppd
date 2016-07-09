package com.coastshop.util;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.vo.Product;
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
public class ImportPriceUtil {

    public static int doImport(File file) throws Exception {
        List<Product> products = readFromExcel(file);
        return saveToDatabase(products);
    }

    private static List<Product> readFromExcel(File file) throws Exception {
        ArrayList<Product> products = new ArrayList<Product>();
        int row = 1;
        Cell cell = null;
        try {
            Workbook wb = new HSSFWorkbook(new FileInputStream(file));
            Sheet sheet = wb.getSheetAt(0);
            //最后一行的行号
            System.out.println("最后一行的行号=" + sheet.getLastRowNum());
            while (row <= sheet.getLastRowNum()) {
                cell = sheet.getRow(row).getCell(0);
                String sn = cell.getStringCellValue();
                cell = sheet.getRow(row).getCell(1);
                int price = (int) cell.getNumericCellValue();
                Product product = new Product();
                product.setSn(sn);
                product.setPrice(price);
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

    private static int saveToDatabase(List<Product> products) throws Exception {
        int count = 0;
        try {
            for (Product product : products) {
                String sn = product.getSn();
                if (ServiceFactory.getProductServiceInstance().find(sn) == null) {
                    boolean flag = ServiceFactory.getProductServiceInstance().insert(product);
                    if (flag) count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return count;
        }
    }
}
