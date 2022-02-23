<%--
  Created by IntelliJ IDEA.
  User: ldl
  Date: 2022/2/15
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <%@include file="../static/common/CommonHead.jsp"%>
    <link type="text/css" rel="stylesheet" href="${basePath}static/css/style.css" >
    <script type="text/javascript" src="${basePath}static/script/jquery-1.7.2.js"></script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="${basePath}common/logo.png" >
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>登录界面</h1>
                    <a href="${basePath}User/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
<%--                    <b></b>--%>
                    <span class="errorMsg">
<%--					请输入用户名和密码--%>
						${empty requestScope.loginMessage?"请输入用户名和密码":requestScope.loginMessage}
					</span>
                </div>
                <div class="form">

                    <form action="${requestScope.basePath}userServlet" method="post">
                        <input type="hidden" name="action" value="login">
                        <label for="email">账户：</label>
                        <input class="itxt" type="text" id="email" placeholder="请输入账号(您的邮箱)" autocomplete="off" tabindex="1" name="email"
                               value="${requestScope.email}"/>
                        <br />
                        <br />
                        <label for="password">密码：</label>
                        <input class="itxt" id="password" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
                        <br />
                        <br />
                        <label for="code">验证码：</label>
                        <input class="itxt" type="text" style="width: 150px;" id="code" name="code"/>
                        <img src="${requestScope.basePath}kaptcha.jpg" id="codeImg" name="codeImg" style="width: 100px; height: 28px;"> <br>
                        <br />
                        <input type="submit" value="登录" id="sub_btn" />
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>

