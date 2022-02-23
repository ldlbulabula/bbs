<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改帖子</title>
    <%@include file="static/common/CommonHead.jsp"%>

</head>
<body>
<%
    String uuid = UUID.randomUUID().toString();
    session.setAttribute("uuid", uuid);
%>
<form action="${basePath}postServlet?action=updateComplete" method="post">
    <c:if test="${not empty sessionScope.loginUser}">
        <input type="hidden" name="author" value="${sessionScope.loginUser.username}">
        <input type="hidden" name="author_id" value="${sessionScope.loginUser.id}">
    </c:if>
    <c:if test="${not empty sessionScope.loginAdmin}">
        <input type="hidden" name="author" value="${sessionScope.loginAdmin.adminName}">
        <input type="hidden" name="author_id" value="${sessionScope.loginAdmin.admin_id}">
    </c:if>
    <input type="hidden" name="action" value="updateComplete">
    <input type="hidden" name="post_id" value="${requestScope.post_id}">
    <input type="hidden" name="uuid" value="<%=uuid %>"/>
    标题：<input type="text" name="title" value="${requestScope.title}"><br>
    <select name="tag">
        <option value="0" selected="selected">--请选择分区--</option>
        <option value="1">前端</option>
        <option value="2">后端</option>
        <option value="3">大数据</option>
        <option value="4">深度学习</option>
    </select><br>
    <textarea cols="100" rows="30" name="content" id="content" wrap="hard">${requestScope.content}</textarea><br>
    <input type="submit" value="完成修改">${requestScope.msg}
</form>

</body>
</html>
