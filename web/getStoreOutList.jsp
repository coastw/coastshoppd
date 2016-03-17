<%-- 
    Document   : getStoreOutList
    Created on : 2013-10-12, 15:32:42
    Author     : Coast
    Description: 用于扫面页面显示数据表格
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<table>
    <tr>
        <th>编号</th>
        <th>款号</th>
        <th>色号</th>
        <th>尺码</th>
        <th>数量</th>
        <th>价格</th>
        <!--TODO只有当天可以进行减数操作-->
        <th>操作</th>
    </tr>
    <c:forEach var="info" items="${list}">
        <tr id="${info.id}">
            <td>${info.id}</td>
            <td>${info.sn}</td>
            <td>${info.color}</td>
            <td>${info.size}</td>
            <td>${info.amount}</td>
            <td>${info.price}</td>
            <!--TODO只有当天可以进行减数操作-->
            <td><a class="del_one">-</a></td>
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

        //数量减1
        $(".del_one").click(function() {
            //alert("减1");
            //获得id
            var id = $(this).closest("tr").attr("id");
            //alert(id);
            //执行dao操作减法
            $.post("ajax.do", {method: "substract", id: id}, function(data) {
                if(data==0){
                    alert("操作失败");
                }else{
                    alert("操作成功");
                }
                //刷新页面
                location.reload();
            });
        });

    <c:if test="${sessionScope.lastid!=0}">
        $("#${sessionScope.lastid}").css("color", "red");
    </c:if>

    });
</script>
