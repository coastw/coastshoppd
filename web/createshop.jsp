<%-- 
    Document   : createshop
    Created on : 2013-10-15, 20:32:13
    Author     : Coast
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>片断 | 创建商店 | CoastShopPD</title>
        <jsp:include page="include/head.jsp" />
        <script type="text/javascript">
            $(function() {

                getShopList();
                //style
                $("#button").button().click(function(e) {
                    //e.preventDefault();
                    if (confirm("请确认修改")) {
                        var p = $("#position").val();
                        var n = $("#name").val();
                        $.post("createshop.do", {position: p, name: n}, function(data) {
                            alert(data);
                            if (data == 1) {
                                alert("创建成功");
                            } else {
                                alert("创建失败");
                            }
                            //
                            getShopList();
                        });
                    }
                });

                //getShopList
                function getShopList() {
                    $("#shoplist").empty()
                    $.post("ajax.do", {method: "getshoplist"}, function(data) {
                        $("#shoplist").append(data);
                    });
                }

            });
        </script>
    </head>
    <body>
        <jsp:include page="include/nav.jsp">
            <jsp:param name="subTitle" value="创建商店" />
        </jsp:include>
        <div id ="myform" style="float: left">
            <div id="titlebar">创建商店</div>
            <div id="bjdform">
                <p class="validateTips">例如:王府井-上品</p>
                <fieldset>
                    <lable>地点</lable>
                    <input type="text" name="position" id="position" class="text" />
                    <lable>名称</lable>
                    <input type="text" name="name" id="name" class="text" />
                    <button id="button">提 交</button>
                </fieldset>
            </div>
        </div>
        <div style="clear: both"></div>
        <div id="shoplist">
        </div>
    </body>
</html>
