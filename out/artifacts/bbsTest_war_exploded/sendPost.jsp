<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>发帖页面</title>
    <%@include file="static/common/CommonHead.jsp"%>

</head>
<body>
<%
    String uuid = UUID.randomUUID().toString();
    session.setAttribute("uuid", uuid);
%>
<form action="${basePath}postServlet?action=uploadText" method="post">
    <input type="hidden" name="author" value="${sessionScope.loginUser.username}">
    <input type="hidden" name="author_id" value="${sessionScope.loginUser.id}">
    <input type="hidden" name="action" value="uploadText">
    <input type="hidden" name="uuid" value="<%=uuid %>"/>
<%--    <input type="hidden" name="token" value="${sessionScope.token}">--%>
    标题：<input type="text" name="title" value="${requestScope.title}"><br>
    <select name="tag">
        <option value="0" selected="selected">--请选择分区--</option>
        <option value="1">前端</option>
        <option value="2">后端</option>
        <option value="3">大数据</option>
        <option value="4">深度学习</option>
    </select><br>
    <textarea cols="100" rows="30" name="content" id="content" wrap="hard">${requestScope.content}</textarea><br>
    <input type="submit" value="发帖">${requestScope.msg}
</form>

</body>
</html>
