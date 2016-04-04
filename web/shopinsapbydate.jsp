<%-- 
    Document   : sap
    Created on : 2016-4-4, 13:46:59
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>片断 | 上品SAP | CoastShop</title>
        <script type="text/javascript">
            $(function() {	//	onload
                //alert('ok');
                $("#datepicker1").datepicker({dateFormat: "yy-mm-dd"});
                $("#ui-datepicker-div").css('font-size', '1em');
                $("#datepicker2").datepicker({dateFormat: "yy-mm-dd"});
                $("#ui-datepicker-div").css('font-size', '1em');
                $("#button").button().click(function() {
                    //validate
                    $("#form1").submit();
                });
            });
        </script>
    </head>
    <body>
        <h3><a href="index.jsp">首页</a>->上品SAP</h3>
        <hr />
        <h3>${err}</h3>
        <div id="myform">
            <div id="titlebar">生成上品SAP</div>
            <div id="bjdform">		<!-- form -->
                <p class="validateTips">根据日期生成上品SAP</p>
                <form id="form1" action="shopinsapbydate.do" method="get">
                    <fieldset>
                        <label for="date1">起始日期</label>
                        <input type="text" name="date1" id="datepicker1" class="text ui-widget-content" />
                        <label>结束日期</label>
                        <input type="text" name="date2" id="datepicker2" class="text ui-widget-content" />
                        <button id="button">提 交</button>
                    </fieldset>
                </form>
            </div>
        </div>
    </body>
</html>
