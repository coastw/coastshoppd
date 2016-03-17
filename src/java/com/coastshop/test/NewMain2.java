package com.coastshop.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class NewMain2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        Connection connection = new DatabaseConnection().getConnection();
        
        long beginTime = System.currentTimeMillis();
        ArrayList<StoreOut> list = getCountList(connection);
        long endTime = System.currentTimeMillis();
        System.out.println("----查询两次getCountList用时"+ (endTime-beginTime) + "毫秒----");
        
        //elementData
        Class c = Class.forName("java.util.ArrayList");
        Field f = c.getDeclaredField("elementData");
        f.setAccessible(true);
        Object[] data = (Object[])f.get(list);
        System.out.println("内部容量: "+ data.length);
        System.out.println("实际用量: " + list.size());
        
        connection.close();
    }
    
    public static int getCount(Connection connection) throws Exception{
        int count = 0;
        String sql = "select count(*) from tbstoreout";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        pstmt.close();
        return count;
    }
    
    /**
     * 通过sql语句得到count
     * @param conn
     * @param listid
     * @return
     * @throws Exception 
     */
    public static ArrayList<StoreOut> getCountList(Connection conn) throws Exception{
        int max = getCount(conn);
        ArrayList<StoreOut> list = new ArrayList<StoreOut>(max);
        String sql = "SELECT `id`,`sn`,`color`,`size`,`amount`,`outlistid` FROM `tbstoreout`";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
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
