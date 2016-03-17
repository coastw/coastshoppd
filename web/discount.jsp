<%-- 
    Document   : discount
    Created on : 2013-11-5, 1:44:54
    Author     : Coast
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include/head.jsp" />
        <title>片断 | 折扣管理 | CoastShopPD</title>
        <script type="text/javascript">
            $(function() {
                $("table").on("mouseover", "tr", function() {
                    $(this).css({
                        'background-color': '#ff9'
                    });
                });
                $("table").on("mouseout", "tr", function() {
                    $(this).css({
                        'background-color': '#fff'
                    });
                });
            });
        </script>
    </head>
    <body>
        <jsp:include page="include/nav.jsp">
            <jsp:param name="subTitle" value="折扣管理" />
        </jsp:include>
        <c:if test="${sessionScope.user.name eq 'coastw'}">
            <div id="form1">
                <form action="discount.do" method="post">
                    <table border="1">
                        <tr>
                            <th>类别代码</th>
                            <th>类别名称</th>
                            <th>匹配规则</th>
                            <th>折扣值</th>
                            <th>操作</th>
                        </tr>
                        <td><input type="text" name="categorynum" /></td>
                        <td><input type="text" name="categorychar" /></td>
                        <td><input type="text" name="regex" /></td>
                        <td><input type="text" name="persent" /></td>
                        <td><input type="submit" /></td>
                    </table>
                </form>
            </div>
        </c:if>
        <script type="text/javascript">
            var isInEdit = false;
            $(function() {
                $(".edit").on("click", function(event) {
                    if (isInEdit) {
                        return;
                    }
                    isInEdit = true;
                    event.preventDefault();
                    var id = $(this).attr("id");
                    var cgnumElement = $(this).closest("tr").find(".cgnum");
                    var cgcharElement = $(this).closest("tr").find(".cgchar");
                    var regElement = $(this).closest("tr").find(".reg");
                    var persElement = $(this).closest("tr").find(".pers");

                    changeToEdit(cgnumElement);
                    changeToEdit(cgcharElement);
                    changeToEdit(regElement);
                    changeToEdit(persElement);

                    $(this).css("display", "none");
                    $(this).closest("td").append("<button class='editbtn' id='" + id + "'>修改</button>");
                    $(this).closest("td").append("<button class='cancelbtn' id='" + id + "'>取消</button>");

                    $(".editbtn").click(function() {
                        var id = $(this).attr("id");
                        var cgnum = $.trim($("#cgnum").val());
                        var cgchar = $.trim($("#cgchar").val());
                        var reg = $.trim($("#reg").val());
                        var pers = $.trim($("#pers").val());
                        var persentRegex = /%$/;
                        if (persentRegex.test(pers)) {
                            $.post("discount.do", {method: "edit", id: id, cgnum: cgnum, cgchar: cgchar, reg: reg, pers: pers}, function(data) {
                                if (data == 0) {
                                    alert("更新失败");
                                } else {
                                    alert("更新成功");
                                    location.href = "discount.do";
                                }
                            });
                        }else{
                            alert("请输入%");
                        }
                    });
                    
                    $(".cancelbtn").click(function() {
                        location.href="discount.do";
                    });
                });
            });

            function changeToEdit(element) {
                var name = element.attr("class");
                var value = element.text();
                element.text("");
                element.append("<input type='text' id='" + name + "' name='" + name + "' value='" + value + "' />");
            }
        </script>
        <div id="content">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>类别代码</th>
                    <th>类别名称</th>
                    <th>匹配规则</th>
                    <th>折扣值</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${list}" var="i">
                    <tr>
                        <td>${i.id}</td>
                        <td class="cgnum">${i.categorynum}</td>
                        <td class="cgchar">${i.categorychar}</td>
                        <td class="reg">${i.regex}</td>
                        <td class="pers">${i.persent}</td>
                        <td><a id="${i.id}" class="edit" href="#">修改</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>