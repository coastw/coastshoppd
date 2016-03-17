/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.test;

import com.coastshop.dbc.DatabaseConnection;
import com.coastshop.vo.StoreOut;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Coast
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        Connection connection = new DatabaseConnection().getConnection();
        
        long beginTime = System.currentTimeMillis();
        ArrayList<StoreOut> list = getList(connection);
        long endTime = System.currentTimeMillis();
        System.out.println("----移动指针getList用时"+ (endTime-beginTime) + "毫秒----");
        
        //elementData
        Class c = Class.forName("java.util.ArrayList");
        Field f = c.getDeclaredField("elementData");
        f.setAccessible(true);
        Object[] data = (Object[])f.get(list);
        System.out.println("内部容量: "+ data.length);
        System.out.println("实际用量: " + list.size());
        
        connection.close();
    }
    
   
    
    /**
     * 可移动指针的ResultSet
     * @param conn
     * @param listid
     * @return
     * @throws Exception 
     */
    public static ArrayList<StoreOut> getList(Connection conn) throws Exception{
        
        String sql = "SELECT `id`,`sn`,`color`,`size`,`amount`,`outlistid` FROM `tbstoreout`";
        PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = pstmt.executeQuery();
        rs.last();
        int max = rs.getRow();
        ArrayList<StoreOut> list = new ArrayList<StoreOut>(max);
        rs.beforeFirst();
        while(rs.next()){
            StoreOut so = new StoreOut();
            so.setId(rs.getInt(1));
            so.setSn(rs.getString(2));
            so.setColor(rs.getString(3));
            so.setSize(rs.getString(4));
            so.setAmount(rs.getInt(5));
            so.setOutlistid(rs.getInt(6));
            list.add(so);
        }
        rs.close();
        pstmt.close();
        return list;
    }
    
    
}
