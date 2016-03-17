<%-- 
    Document   : outlist
    Created on : 2013-8-28, 8:22:24
    Author     : Coast
--%>
<%@page import="com.coastshop.util.PageSeperator"%>
<%@page import="com.coastshop.vo.Shop"%>
<%@page import="com.coastshop.factory.ServiceFactory"%>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Shop> shoplist = ServiceFactory.getShopServiceInstance().findAll();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" />
        <title>片断 | 出库单 | CoastShopPD</title>
        <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
        <script type="text/javascript">
            $(function(){
                $("select").get(0).selectedIndex=${shopid};
                $("table").on("mouseover", "tr", function(){
                    $(this).css({
                        'background-color':'#ff9'
                    });
                });
                $("table").on("mouseout", "tr", function(){
                    $(this).css({
                        'background-color':'#fff'
                    });
                });
            });
        </script>
    </head>
    <body>
        <h3><a href="index.jsp">首页</a>->出库单列表</h3>
        <hr />
        <!-- 过滤器 -->
        <form action="outlist.do" method="get">
            <label>商店过滤</label>
            <select name="shopid">
                <option value="0">全部</option>
                <c:forEach var="shop" items="<%= shoplist%>">
                    <option value="${shop.id}">${shop.name}</option>
                </c:forEach>
            </select>
            <input type="submit" />
        </form>

        <table border="1" width="800px">
            <tr>
                <th>单号</th><th class="width200">地点</th><th>日期</th><th>总数</th><th>品牌</th><th>操作</th>
            </tr>
            <!-- data -->
            <c:forEach var="hol" items="${list}">
                <tr>
                    <td>${hol.id}</td>
                    <td>${hol.shop}</td>
                    <td>${hol.date}</td>
                    <td>${hol.amount}</td>
                    <td>${hol.brand}</td>
                    <td><a href="storeoutlist.do?outlistid=${hol.id}">查看</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td><a href="outlist.do?shopid=${shopid}&page=${ps.getFirstPageNum()}">第一页</a></td>
                <td><a href="outlist.do?shopid=${shopid}&page=${ps.getPrevPageNum()}">上一页</a></td>
                <td>当前第${ps.getCurrentPageNum()}页</td>
                <td><a href="outlist.do?shopid=${shopid}&page=${ps.getPageCount()}">总共${ps.getPageCount()}页</a></td>
                <td><a href="outlist.do?shopid=${shopid}&page=${ps.getNextPageNum()}">下一页</a></td>
                <td><a href="outlist.do?shopid=${shopid}&page=${ps.getLastPageNum()}">最后页</a></td>
            </tr>
        </table>
    </body>
</html>
