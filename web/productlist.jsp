<%-- 
    Document   : productlist
    Created on : 2013-8-17, 21:40:37
    Author     : Coast
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>片断 | 款号查询 | CoastShopPD</title>
        <link rel="stylesheet" href="css/style.css" />
        <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
        <script type="text/javascript">
            $(function() {
                //table
                $("table").on("mouseover", "tr", function() {
                    $(this).css({
                        'background-color': '#ff9'
                    });
                });
                $("table").on("mouseout", "tr", function() {
                    $(this).css({
                        'background-color': '#fff'
                    });
                });

            });
        </script>
    </head>
    <body>
        <h3><a href="index.jsp">首页</a>->款号查询</h3>
        <hr />
        <!-- 过滤器 -->
        <form action="productlist.do" method="get">
            <label>查询款号</label>
            <input type="text" name="sn" value="${sn}" />
            <input type="submit" />
        </form>
            <table border="1" class="width600">
            <tr>
                <th>ID</th>
                <th>款号</th>
                <th>价格</th>
                <th>操作</th>
            </tr>
            <c:forEach var="product" items="${productlist}" varStatus="status">
                <tr>
                    <!--<td>${status.count}</td>-->
                    <td>${product.id}</td>
                    <td class="width200">${product.sn}</td>
                    <td>${product.price}</td>
                    <td><a href="productedit.do?id=${product.id}">改价</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="4"><a href="productlist.do?sn=${sn}&page=${ps.getFirstPageNum()}">第一页</a>
                    <a href="productlist.do?sn=${sn}&page=${ps.getPrevPageNum()}">上一页</a>
                    当前第${ps.getCurrentPageNum()}页
                    <a href="productlist.do?sn=${sn}&page=${ps.getPageCount()}">总共${ps.getPageCount()}页</a>
                    <a href="productlist.do?sn=${sn}&page=${ps.getNextPageNum()}">下一页</a>
                    <a href="productlist.do?sn=${sn}&page=${ps.getLastPageNum()}">最后页</a></td>
            </tr>
        </table>
    </body>
</html>
