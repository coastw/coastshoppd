<%-- 
    Document   : upload
    Created on : 2013-8-12, 6:17:32
    Author     : Coast

    Descreption: 数据还原提交页面，内容提交到smartupload.jsp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>数据还原 | CoastShop</title>
    </head>
    <body>
        <jsp:include page="/include/nav.jsp">
            <jsp:param name="subTitle" value="数据还原" />
        </jsp:include>
        <form action="smartupload.jsp" method="post" enctype="multipart/form-data">
            选择文件<input type="file" name="sqlfile" />
            <input type="submit" />
        </form>
    </body>
</html>
