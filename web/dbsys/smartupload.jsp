<%-- 
    Document   : smartupload
    Created on : 2013-8-12, 6:22:01
    Author     : Coast
--%>

<%@page import="com.coastshop.dbc.DatabaseConnection"%>
<%@page import="com.coastshop.dbc.DatabasePool"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.coastshop.dbsys.DBSystem"%>
<%@page import="java.text.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.jspsmart.upload.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            request.setCharacterEncoding("UTF-8");
            long a = System.currentTimeMillis();
            String filename = null;
            String filepath = null;
            SmartUpload su = null;
            Connection conn = null;
            try {
                su = new SmartUpload();
                su.initialize(pageContext);
                su.setAllowedFilesList("sql");
                su.upload();
                String ext = su.getFiles().getFile(0).getFileExt();
                filename = "dbrestore." + ext;
                filepath = this.getServletContext().getRealPath("/") + "dbsys" + java.io.File.separator;
                su.getFiles().getFile(0).saveAs(filepath + filename);
                conn = new DatabaseConnection().getConnection();
                DBSystem dbs = new DBSystem(conn);
                dbs.restoreDB(filepath + filename);
            } catch (Exception e) {
                out.println(e.toString());
                response.sendRedirect("upload.jsp");
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            long b = System.currentTimeMillis();
        %>
        <h3>还原数据库完成</h3>
        <h4>用时<%= b-a %>毫秒</h4>
        <a href="<%= filename%>"><%= filename%></a>
        <a href="../index.jsp">返回首页</a>
    </body>
</html>
