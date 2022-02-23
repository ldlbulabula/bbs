<%--
  Created by IntelliJ IDEA.
  User: ldl
  Date: 2022/2/17
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="static/common/CommonHead.jsp"%>
    <title>帖子展示页</title>

</head>
<body>
    <%@include file="static/common/top.jsp"%>
    <div>
        <table width="80%" align="center" border="1px" style="word-wrap:break-word;word-break:break-all;" >
            <tr>
                <td colspan="2"> <h2>${requestScope.post.title}</h2> </td>
            </tr>
            <tr>
                <td width="20%">时间：${requestScope.post.date}</td>
                <td><a title="个人主页" target="_blank" href="${basePath}userServlet?action=showPersonalPage&username=${requestScope.post.author}">作者：${requestScope.post.author}</a></td>
            </tr>
            <tr>
                <td colspan="2" id="content">
                    <br>
                    ${requestScope.post.content}
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
