<%--
  Created by IntelliJ IDEA.
  User: ldl
  Date: 2022/2/15
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
    <%@include file="../static/common/CommonHead.jsp"%>
    <link type="text/css" rel="stylesheet" href="${basePath}static/css/style.css" >
    <script type="text/javascript" src="${basePath}static/script/jquery-1.7.2.js"></script>

    <style type="text/css">
        .login_form{
            height:420px;
            margin-top: 25px;
        }

    </style>
        <script type="text/javascript">
            $(function () {
                $("#username").blur(function () {
                    let username = this.value;
                    let errorMsg = $(".errorMsg");
                    $.getJSON("${requestScope.basePath}userServlet",{username: username,action:"ajaxExistName"},function (data) {
                        if(data.isNull){
                            errorMsg.text("用户名不能为空")
                            errorMsg.css("color","red")
                            return false
                        }else {
                            if (data.existName) {
                                errorMsg.text("用户名已存在")
                                errorMsg.css("color", "red")
                                return false
                            } else {
                                errorMsg.text("用户名可用")
                                errorMsg.css("color", "green")
                                return true
                            }
                        }
                    })
                })


                $("#password").blur(function () {
                    let password = this.value;
                    let errorMsg = $(".errorMsg");
                    $.getJSON("${requestScope.basePath}userServlet",{password: password,action:"ajaxCheckPassword"},function (data) {
                        if(data.isOK){
                            errorMsg.text(data.passwordMsg)
                            errorMsg.css("color","green")

                        }else {
                            errorMsg.text(data.passwordMsg)
                            errorMsg.css("color","red")

                        }

                    })
                })

                $("#repwd").blur(function () {
                    let repwd = this.value;
                    let password = $("#password").val();
                    let errorMsg = $(".errorMsg");
                    if(repwd==password){
                        errorMsg.text("前后密码一致")
                        errorMsg.css("color","green")
                    }else {
                        errorMsg.text("两次输入的密码不一致")
                        errorMsg.css("color","red")
                    }
                })


                $("#email").blur(function () {
                    let email = this.value;
                    let errorMsg = $(".errorMsg");
                    $.getJSON("${requestScope.basePath}userServlet",{email: email,action:"ajaxCheckEmail"},function (data){
                        if(data.isOK){
                            errorMsg.text(data.emailMsg)
                            errorMsg.css("color","green")

                        }else {
                            errorMsg.text(data.emailMsg)
                            errorMsg.css("color","red")

                        }
                    })
                })



            })


    </script>

</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="${requestScope.basePath}static/common/logo.png" >
</div>
<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册用户</h1>
                    <span class="errorMsg" id="msg">
                        ${requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <%--								<form action="/book/registServlet" method="post">--%>
                    <form action="${requestScope.basePath}userServlet" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>昵称：</label>
                        <input class="itxt" type="text" placeholder="请输入昵称(不大于20个字符)"
                               autocomplete="off" tabindex="1" name="username" id="username"
                               value="${requestScope.registUsername}"/>

                        <br />
                        <br />
                        <label>密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" id="password"
                        />
                        <br />
                        <br />
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd" />
                        <br />
                        <br />
                        <label>邮箱：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱,这会成为您的账号"
                               autocomplete="off" tabindex="1" name="email" id="email"
                               value="${requestScope.registEmail}"/>
                        <br />
                        <br />
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 150px;" id="code" name="code"/>
                        <img src="${requestScope.basePath}kaptcha.jpg" id="codeImg" name="codeImg" style="width: 100px; height: 28px;"> <br>
                        <br />

                        <input type="submit" value="注册" id="sub_btn" />

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>
