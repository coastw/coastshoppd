<%-- 
    Document   : scan
    Created on : 2013-10-9, 0:45:46
    Author     : Coast
--%>

<%@page import="com.coastshop.util.ProductUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="pragma" content="no-cache"> 
        <meta http-equiv="cache-control" content="no-cache"> 
        <meta http-equiv="expires" content="0"> 
        <title>片断 | 扫单页</title>
        <link rel="stylesheet" href="css/redmond/jquery-ui-1.9.2.custom.min.css" />
        <style type="text/css">
            .no-close .ui-dialog-titlebar-close {
                display: none;
            }
            .hide{
                display: none;
            }
            /*0415*/
            .mainbody{width: 100%}
            .mainbodyleft{float: left; width: 50%; min-width: 500px}
            .mainbodyright{float: left; font-size:300px;font-weight: bold; padding: 0 0 0 0}
        </style>
        <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
        <script type="text/javascript">
            $(function () {
                //alert("hello");
                showData(0);
                /**
                 * 条码扫
                 */
                $("#sno").focus();
                $("#sno").bind('keyup', function (e) {
                    //e.preventDefault();
                    if (e.keyCode === 13) {
                        //alert("13");
                        //验证
                        var sno = $.trim($("#sno").val());
                        if (validate(sno)) {
                            //查价格
                            $.post("ajax.do", {method: "getprice", keyword: $("#sno").val()}, function (data) {
                                //如果没查到则价格为0
                                var price = parseInt(data);
                                if (price === 0) {
                                    //TODO 播放声音提示，设置价格
                                    //var audioElm = document.getElementById("mp3");
                                    //audioElm.play(); //IE do not work
                                    $("#mp3")[0].currentTime = 0.5;
                                    $("#mp3")[0].play();
                                    alert("没查到此款价格");
                                    //设置dialog里的sn
                                    if (is2017(sno)) {
                                        $("#sn").val(sno.substring(0, 12));
                                        $("#snlable").text(sno.substring(0, 12));
                                    } else {
                                        $("#sn").val(sno.substring(0, 9));
                                        $("#snlable").text(sno.substring(0, 9));
                                    }
                                    $("#sn").attr("readonly", "readonly");
                                    $("#dialog").dialog("open");
                                    $("#price").focus();
                                } else {
                                    //查到价格,并提交
                                    //alert("TODO 提交");
                                    postData(sno);
                                    $("#sno").focus();
                                    $("#sno").val("");
                                }
                            });
                            //提交
                            //$("#dialog").dialog("open");
                        } else {
                            //款号输入有误
                            $("#mp3wrongsn")[0].currentTime = 1;
                            $("#mp3wrongsn")[0].play();
                            alert("款号不正确:" + $("#sno").val());
                            $("#sno").focus();
                            $("#sno").val("");
                        }
                        //end13
                    }
                });


                //验证
                function validate(sno) {
                    //需要servlet获取验证码写到此处
                    var regex = /<%= ProductUtil.BARCODEREGEX%>/;
                    return regex.test(sno);
                }

                //是否是2017款号
                function is2017(sno) {
                    var regex = /<%= ProductUtil.BARCODEREGEX2017%>/;
                    return regex.test(sno);
                }

                //validate price
                function validatePrice(price) {
                    var regex = /^[1-9][0-9]{1,4}$/;
                    return regex.test(price);
                }

                //提交数据 TODO ajax同步请求避免出错
                function postData(sno) {
                    var outlistid = "${sessionScope.listid}";
                    /*
                     $.post("createstoreout.do", {outlistid: outlistid, sno: sno}, function(data) {
                     //data 为id
                     showData(data);
                     if (data == 0) {
                     alert("提交失败");
                     }
                     });
                     */
                    //同步
                    $.ajax({
                        type: "post",
                        url: "createstoreout.do",
                        data: "outlistid=" + outlistid + "&sno=" + sno,
                        cache: false,
                        async: false,
                        success: function (data) {
                            var regex = /^[0-9]+$/;
                            if (regex.test(data)) { //是数字
                                showData(data);
                                if (data == 0) {
                                    alert("提交数据失败");
                                } else {
                                    //成功语音提示
                                    //alert("提交数据成功" + data);
                                }
                            } else {
                                alert("提交数据异常");
                            }
                        }
                    });
                    //同步
                }

                //提交价格 
                function postPrice() {
                    var sn = $.trim($("#sn").val());
                    var price = $.trim($("#price").val());
                    if (sn.lengh !== 0 && validatePrice(price)) {
                        $.post("ajax.do", {method: "addproduct", sn: sn, price: price}, function (data) {
                            if (data != 0) {
                                postData($("#sno").val());
                                $("#sn").val("");
                                $("#price").val("");
                                $("#dialog").dialog("close");
                                $("#sno").val("");
                                $("#sno").focus();
                            } else {
                                alert("提交价格失败");
                            }
                        });
                    } else {
                        alert("输入有误");
                        $("#price").focus();
                    }
                }

                //显示数量
                function showSum() {
                    var outlistid = "${sessionScope.listid}";
                    $(".listsum").empty();
                    $.post("ajax.do", {method: "getsumbylist", keyword: outlistid}, function (sum) {
                        $(".listsum").append(sum);
                    });
                }

                //显示数据
                function showData(id) {
                    //alert("Begin Show Data...");
                    var outlistid = "${sessionScope.listid}";
                    var dataElement = $("#data");
                    dataElement.empty();
                    $.post("ajax.do", {method: "getsolist", keyword: outlistid, id: id}, function (data) {
                        dataElement.append(data);
                    });
                    showSum();
                }


                //输入价格
                $("#dialog").dialog({
                    autoOpen: false,
                    width: 270,
                    height: 300,
                    modal: true,
                    closeOnEscape: false,
                    dialogClass: "no-close",
                    draggable: false,
                    resizable: false,
                    buttons: {
                        提交: function () {
                            //提交价格
                            postPrice();
                        },
                        取消: function () {
                            if (confirm("是否取消提交价格？")) {
                                $(this).dialog("close");
                            }
                        }
                    }
                });

                //end onload
            });
        </script>
    </head>
    <body>
        <jsp:include page="include/nav.jsp">
            <jsp:param name="subTitle" value="条秒扫描" />
        </jsp:include>
        <!--mp3-->
        <audio id="mp3" src="audio/noprice.mp3" preload>
            HTML5 audio not supported
        </audio>

        <audio id="mp3wrongsn" src="audio/wrongsno.mp3" preload>
            HTML5 audio not supported
        </audio>

        <audio id="mp3number" src="audio/number.mp3" preload>
            HTML5 audio not supported
        </audio>

        <!--Session-->
        <div id="listifno">
            <p>单号:${sessionScope.currentlist.id},地点:${sessionScope.currentlist.shop},品牌:${sessionScope.currentlist.brand},数量:<span id="listid" class="listsum"></span></p>
        </div>
        <!-- 扫描条码 -->
        <div class="mainbody">
            <div id="content" class="mainbodyleft">
                <p>
                    标签序列号：
                    <input id="sno" type="text" name="sno" />
                </p>
                <!-- !!!导致IE不能回车!!!
                <button id="btnshow">显示明细</button>
                <button id="btndone">录入完成</button>
                <button id="btnexcel">使用Excel打开</button>
                -->
                <div id="data"></div>
            </div>
            <div class="mainbodyright listsum"></div>
        </div>
        <!-- 扫描条码 -->
        <!-- 输入价格表单 -->
        <div id="dialog" title="输入价格">
            <p class="validateTips">请输入此款价格</p>
            <form>
                <fieldset>
                    <input type="hidden" name="sn" id="sn" />
                    <label id="snlable" for="price"></label>
                    <input type="text" name="price" id="price" />
                </fieldset>
            </form>
        </div>
        <!-- 输入价格表单 -->
    </body>
</html>