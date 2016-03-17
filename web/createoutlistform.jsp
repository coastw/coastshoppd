<%-- 
    Document   : bjd
    Created on : 2013-8-24, 8:06:59
    Author     : 
    describe   :通过ajax方式查询shop列表和brand列表（MVC）
--%>

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
        <link rel="stylesheet" href="css/redmond/jquery-ui-1.9.2.custom.css" />
        <link rel="stylesheet" href="css/style.css" />
        <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.2.custom.js"></script>
        <script type="text/javascript" src="js/jquery.ui.datepicker-zh-CN.js"></script>
        <title>片断 | 新建出库单 | CoastShopPD</title>
            
        <script type="text/javascript">
            $(function() {	//	onload
                $("#button").button().click(function() {
                    //validate
                    $("#form1").submit();
                });
            });
        </script>
    </head>
    <body>
        <jsp:include page="include/nav.jsp">
            <jsp:param name="subTitle" value="新建出库单" />
        </jsp:include>
        <div>
            <h3>片段库存管理</h3>
        </div>
        <div id="myform">
            <div id="titlebar">新建出库单</div>
            <div id="bjdform">		<!-- form -->
                <p class="validateTips">建立新的出库单</p>
                <form id="form1" action="createoutlist.do" method="post">
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
                        <button id="button">提 交</button>
                    </fieldset>
                </form>
            </div>
        </div>
    </body>
</html>
