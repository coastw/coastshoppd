<%-- 
    Document   : importprice
    Created on : 2016-7-8, 12:45:25
    Author     : Coast
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>片断 导入价格</title>
    </head>
    <body>
        <jsp:include page="/include/nav.jsp">
            <jsp:param name="subTitle" value="上传价格" />
        </jsp:include>
        <h1>片断导入价格</h1>
        <form method="POST" enctype="multipart/form-data" action="<c:url value="/importprice.do" />">
            File to upload: <input type="file" name="pricedata"><br/>
            <br/>
            <input type="submit" value="导入">
        </form>

    </body>
</html>
