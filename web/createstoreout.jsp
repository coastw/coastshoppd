<%-- 
    Document   : createstoreout
    Created on : 2013-9-10, 7:08:50
    Author     : Coast
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
        <script type="text/javascript">
            $(function() {
                $("#submit").on("click", function() {
                    //alert(${newoutlistid});
                    alert($("#sno").val());
                    $.post("createstoreout.do", {outlistid:${newoutlistid}, sno: $("#sno").val()}, function(data) {
                        $("#data").html(data);
                    });
                });
            });
        </script>
        <title>片断 | 扫描吊牌</title>
    </head>
    <body>
        <div>
            <h1>扫描吊牌</h1>
            <div>
                <input id="sno" type="text" name="sno" />
                <button id="submit">提交</button>
            </div>
            <div id="data">

            </div>
        </div>
    </body>
</html>
