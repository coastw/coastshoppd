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
            点击选择上传的文件: <input type="file" name="pricedata"><br/>
            <br/>
            <input type="submit" value="导入" />
        </form>
            <hr />
        <div>
            <p>文件格式: *.xls</p>
            <p>内容格式如下:</p>
            <table border="1" cellpadding="5" cellspacing="0">
                <tr>
                    <th>款号</th>
                    <th>单价</th>
                </tr>
                <tr>
                    <td>639159653</td>
                    <td>469</td>
                </tr>
                <tr>
                    <td>639159652</td>
                    <td>569</td>
                </tr>
                <tr>
                    <td>639159651</td>
                    <td>499</td>
                </tr>
            </table>
        </div>

    </body>
</html>
