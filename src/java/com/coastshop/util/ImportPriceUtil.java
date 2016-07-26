package com.coastshop.util;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.vo.Product;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Coast
 */
public class ImportPriceUtil {

    public static int doImport(InputStream inputStream) throws Exception {
        List<Product> products = readFromExcel(inputStream);
        return saveToDatabase(products);
    }

    private static List<Product> readFromExcel(InputStream inputStream) throws Exception {
        ArrayList<Product> products = new ArrayList<Product>();

        try {
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum()==0) {
                    continue;
                }
                Cell cell = null;
                cell = row.getCell(0);
                POIUtil poiUtil = new POIUtil();
                String sn = poiUtil.getCellContentToString(cell);
                cell = null;
                cell = row.getCell(1);
                int price = Integer.parseInt(poiUtil.getCellContentToString(cell));
                Product product = new Product();
                product.setSn(sn);
                product.setPrice(price);
                products.add(product);
            }
        } catch (Exception e) {
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
                    if (flag) {
                        count++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return count;
        }
    }
}
