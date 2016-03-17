<%-- 
    Document   : userlist
    Created on : 2013-8-11, 20:11:20
    Author     : Coast
--%>

<%@page import="java.util.Iterator"%>
<%@page import="com.coastshop.vo.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>User List</h1>
        <%
            List<User> all = (List<User>)request.getAttribute("userlist");
            Iterator<User> iter = all.iterator();
        %>
        <table border="1">
            <%
            while ( iter.hasNext() ){
                pageContext.setAttribute("user", iter.next());
                %>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.privilege}</td>
                    <td><a href="useredit.do?id=${user.id}">edit</a></td>
                </tr>
                <%
            }
            %>
        </table>
    </body>
</html>