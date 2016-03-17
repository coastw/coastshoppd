<%-- 
    Document   : getStoreOutList
    Created on : 2013-10-12, 15:32:42
    Author     : Coast
    Description: 用于扫面页面显示数据表格
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<table style="width: 310px">
    <tr>
        <th>id</th>
        <th>name</th>
    </tr>
    <c:forEach var="shop" items="${shoplist}">
        <tr id="${shop.id}">
            <td>${shop.id}</td>
            <td>${shop.name}</td>
        </tr>
    </c:forEach>
</table>
<script type="text/javascript">
    $(function() {

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
