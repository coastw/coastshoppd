<%-- 
    Document   : login
    Created on : 2013-8-11, 3:37:14
    Author     : Coast
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>片断 | 用户登录 | CoastShopPD</title>
            
        <script type="text/javascript">
            $(function() {	//	onload
                //alert('ok');
                $("#button").button().click(function() {
                    //validate
                    $("#form1").submit();
                });
            });
        </script>
    </head>
    <body>
        <div id="myform">
            <div id="titlebar">片段 用户登录</div>
            <div id="bjdform">		<!-- form -->
                <p class="validateTips">请输入用户名与密码</p>
                <form id="form1" action="login.do" method="post">
                    <fieldset>
                        <label>用户</label>
                        <input type="text" name="name" value="" class="text" />
                        <label>密码</label>
                        <input type="password" name="password" value="" class="text" />
                        <button id="button">登 录</button>
                    </fieldset>
                </form>
            </div>
        </div>
        <!--
        <form action="login.do" method="post">
            <label for="name">用户名</lable>
            <input type="text" name="name" value="" />
            <label for="password">密码</lable>
            <input type="password" name="password" value="" />
            <input type="submit"  />
        </form>
        -->
    </body>
</html>
