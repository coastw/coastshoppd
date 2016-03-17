<%-- 
    Document   : producteditform
    Created on : 2013-9-29, 16:07:22
    Author     : Coast
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>片断 | Product Edit | CoastShopPD</title>
        <link type="text/css" rel="stylesheet" href="css/redmond/jquery-ui-1.9.2.custom.css" />
        <link type="text/css" rel="stylesheet" href="css/style.css" />
        <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.2.custom.js"></script>
        <script type="text/javascript">
            $(function(){
                $(".fr").button();
            });
        </script>
    </head>
    <body>
        <h3><a href="index.jsp">首页</a>->修改价格</h3>
        <hr />
        <div id="myform">
            <div id="titlebar">修改价格</div>
            <div id="bjdform">
                <p class="validateTips">修改价格</p>
                <form action="productedit.do" method="post">
                    <fieldset>
                        <input type="text" name="id" style="display:none" value="${product.id}" />
                        <label>款号</label>
                        <!--<input type="text" name="sn" class="text  ui-widget-content" disabled="disabled" value="${product.sn}" />-->
                        <input type="text" name="sn" class="text ui-widget-content" value="${product.sn}" />
                        <label>价格</label>
                        <input type="text" name="price" class="text ui-widget-content" value="${product.price}" />
                        <input type="reset" value="重置" class="fr" />
                        <input type="submit" value="修改" class="fr" />
                        <div style="clear: both"></div>
                    </fieldset>
                </form>
            </div>
        </div>
    </body>
</html>
