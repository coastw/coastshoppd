<%@page import="com.coastshop.vo.*"%>
<%@page import="java.util.*"%>
<%@page import="com.coastshop.factory.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Shop> shoplist = ServiceFactory.getShopServiceInstance().findAll();
    List<Brand> brandlist = ServiceFactory.getBrandServiceInstance().findAll();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>导入订单 | CoastShop</title>
        <link rel="stylesheet" href="<c:url value="/css/redmond/jquery-ui-1.9.2.custom.css"/>" />
        <link rel="stylesheet" href="<c:url value="/css/style.css"/>" />
        <script type="text/javascript" src="<c:url value="/js/jquery-1.8.3.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/jquery-ui-1.9.2.custom.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/jquery.ui.datepicker-zh-CN.js"/>"></script>

    </head>
    <body>
        <h1>导入出库单</h1>
        <jsp:include page="/include/nav.jsp">
            <jsp:param name="subTitle" value="导入订单" />
        </jsp:include>

        <div id="myform" style="margin-left: 0">
            <div id="titlebar">导入订单</div>
            <div id="bjdform">		<!-- form -->
                <p class="validateTips">导入新的出库单</p>
                <form id="form1" action="<c:url value='/importorder.do'/>" method="post" enctype="multipart/form-data">
                    <fieldset>
                        <label>送货地点</label>
                        <select name="shopid" class="text ui-widget-content select">
                            <c:forEach  var="shop" items="<%=shoplist%>">
                                <option value="${shop.id}">${shop.name}</option>
                            </c:forEach>
                        </select>
                        <label>货品品牌</label>
                        <select name="brandid" class="text ui-widget-content select">
                            <c:forEach  var="brand" items="<%= brandlist%>">
                                <option value="${brand.id}">${brand.name}</option>
                            </c:forEach>
                        </select>
                        选择文件<input type="file" name="upload" />
                        <br />
                        <input type="submit" />
                    </fieldset>
                </form>
            </div>
        </div>

        <hr />
        
        <div>
            <p>文件格式: *.xls</p>
            <p>内容格式如下:</p>
            <table border="1" cellpadding="5" cellspacing="0">
                <tr>
                    <th>款号</th>
                    <th>色号</th>
                    <th>尺码</th>
                    <th>发货数量</th>
                </tr>
                <tr>
                    <td>621021010</td>
                    <td>12014</td>
                    <td>3</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>621021010</td>
                    <td>12014</td>
                    <td>1</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>621021010</td>
                    <td>12014</td>
                    <td>2</td>
                    <td>2</td>
                </tr>
                <tr>
                    <td>621021010</td>
                    <td>12014</td>
                    <td>4</td>
                    <td>1</td>
                </tr>
            </table>
        </div>
    </body>
</html>

