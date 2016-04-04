<%-- 
    Document   : index
    Created on : 2013-8-11, 3:36:38
    Author     : Coast
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/redmond/jquery-ui-1.9.2.custom.css" />
        <link type="text/css" rel="stylesheet" href="css/style.css" />
        <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.2.custom.js"></script>
        <script type="text/javascript">
            $(function() {
                $(".button").button();
            });
        </script>
        <title>片断 | 首页 | CoastShopPD</title>
    </head>
    <body>
        <h1>片段</h1>
        <h3>库存管理</h3><hr />
        <!--<a href="dbsys/dbbackup.do">备份数据库</a>
        <a href="dbsys/upload.jsp">还原数据库</a>
        <a href="usermanager.do">用户管理</a>
        <a href="productmanager.do">价格管理</a>
        <a href="shopform.html">商店管理</a>
        <a href="createshop.html">创建商店</a>
        <a href="shopin.do">上品主数据</a>
        <a href="outlist.do">出库单查询</a>
        <a href="bjd.jsp">出库->报价单</a>
        <a href="out2sap.jsp">出库->上品SAP</a>
        <a href="createoutlistform.jsp">新建出库单</a>-->
        <ul>
            <li><a class="button" href="createoutlistform.jsp">新建出库单</a></li>
            <li><a class="button" href="outlist.do">出库单查询</a></li>
            <li><a class="button" href="bjd.jsp">出库->报价单</a></li>
            <li><a class="button" href="out2sap.jsp">出库->上品SAP</a></li>
            <li><a class="button" href="productlist.do">价格管理</a></li>
            <li><a class="button" href="discount.do">折扣管理</a></li>
            <li><a class="button" href="createshop.jsp">商店管理</a></li>
            <li><a class="button" href="shopinsapbydate.jsp">从日期获取SAP</a></li>
            <li><a class="button" href="shopin.do">上品主数据</a></li>
        </ul>
        <hr />
        <ul>
            <li><a class="button" href="dbsys/dbbackup.do">数据备份</a></li>
                <c:if test="${sessionScope.user.name eq 'coastw'}">
                <li><a class="button" href="dbsys/upload.jsp">还原</a></li>
                </c:if>
        </ul>
        <hr />
        <li><a class="button" href="logout.do">注销</a></li>
    </body>
</html>
