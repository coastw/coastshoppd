<%-- 
    Document   : storeoutlist
    Created on : 2013-8-28, 20:14:44
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
                $(".btn").button();
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
                var amount = 0;
                $(".amount").each(function(){
                    amount += parseInt($(this).text());
                });
                //alert(amount);
                $("#sum").text(amount);
            });
        </script>
        <title>片断 | 出库单明细 | CoastShopPD</title>
    </head>
    <body>
        <h3><a href="index.jsp">首页</a>-><a href="outlist.do">出库单列表</a>->出库单明细</h3>
        <hr />
        <!-- 信息:单号,日期,总数 -->
        <div>
            <span>单号: ${outlist.id}</span>
            <span>日期: ${outlist.date}</span>
            <span>用户: ${outlist.user}</span>
            <br />
            <span>品牌: ${outlist.brand}</span>
            <span>商店: ${outlist.shop}</span>
            <strong><span>总数:${outlist.amount}</span></strong>
        </div>
        <a class="btn" href="storeoutexcel.do?listid=${outlist.id}">出库单</a>
        <a class="btn" href="bjd.do?listid=${outlist.id}">报价单</a>
        <a class="btn" href="sotosap.do?listid=${outlist.id}">上品SAP</a>
        <c:if test="${showUploadSAPButton == true}">
            <a class="btn" href="sotoftp.do?listid=${outlist.id}">上传SAP到FTP</a>
        </c:if>
        
        <table width="500px">
            <tr>
                <th></th>
                <th>款号</th>
                <th>色号</th>
                <th>颜色</th>
                <th>尺码</th>
                <th>价格</th>
                <th>数量</th>
            </tr>
            <c:forEach var="sopi" items="${sopilist}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${sopi.sn}</td>
                    <td>${sopi.color}</td>
                    <td>${sopi.colorType}</td>
                    <td>${sopi.localSize}</td>
                    <td>${sopi.price}</td>
                    <td class="amount">${sopi.amount}</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="6">总数</td>
                <td id="sum">???</td>
            </tr>
        </table>
    </body>
</html>
