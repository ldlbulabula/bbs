<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <%@include file="../static/common/CommonHead.jsp"%>
    <script>
        $(function () {
            $(".deleteUser").click(function () {
                return confirm("是否将用户删除？");
            })
            $(".setAsAdmin").click(function () {
                return confirm("是否将用户设为管理员？");
            })
        })
    </script>
</head>
<body>
<div>
    <table align="center" width="80%" border="1px">
        <tr>
            <td colspan="3" style="text-align: center">用户列表</td>
        </tr>
        <tr>
            <td>用户名</td>
            <td>id</td>
            <td width="20%">操作</td>
        </tr>
        <c:forEach items="${requestScope.userPage.items}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.id}</td>
                <td>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <c:if test="${user.isAdmin==0}">
                                <li><a href="${requestScope.basePath}adminServlet?action=deleteUser&pageNo=${requestScope.userPage.pageNo}&id=${user.id}" class="deleteUser">删除</a></li>
                                <li><a href="${requestScope.basePath}adminServlet?action=setAsAdmin&pageNo=${requestScope.userPage.pageNo}&id=${user.id}" class="setAsAdmin">设为管理员</a></li>
                            </c:if>
                            <c:if test="${user.isAdmin==1}">
                                <li><a>该用户为管理员</a></li>
                            </c:if>
                        </ul>
                    </nav>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<nav aria-label="Page navigation">
    <ul class="pagination">
        <c:if test="${param.pageNo>requestScope.userPage.pageTotal}">
            <script>
                location.href="${requestScope.basePath}adminServlet?action=manageUser&pageNo=${requestScope.userPage.pageTotal}";
            </script>
        </c:if>

        <li><a href="${requestScope.basePath}adminServlet?action=manageUser&pageNo=1">首页</a></li>
        <%--            上一页--%>
        <c:if test="${requestScope.userPage.pageNo==1}">
            <li class="disabled">
                <a aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${requestScope.userPage.pageNo>1}">
            <li>
                <a href="${requestScope.basePath}adminServlet?action=manageUser&pageNo=${requestScope.userPage.pageNo-1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>



        <c:if test="${requestScope.userPage.pageNo-2>=1}">
            <li>
                <a href="${requestScope.basePath}adminServlet?action=manageUser&pageNo=${requestScope.userPage.pageNo-2}">
                        ${requestScope.userPage.pageNo-2}
                </a>
            </li>
        </c:if>


        <c:if test="${requestScope.userPage.pageNo-1>=1}">
            <li>
                <a href="${requestScope.basePath}adminServlet?action=manageUser&pageNo=${requestScope.userPage.pageNo-1}">
                        ${requestScope.userPage.pageNo-1}
                </a>
            </li>
        </c:if>


        <li class="active">
            <a href="${requestScope.basePath}adminServlet?action=manageUser&pageNo=${requestScope.userPage.pageNo}">
                ${requestScope.userPage.pageNo}
            </a>
        </li>


        <c:if test="${requestScope.userPage.pageNo+1<=requestScope.userPage.pageTotal}" >
            <li>
                <a href="${requestScope.basePath}adminServlet?action=manageUser&pageNo=${requestScope.userPage.pageNo+1}">
                        ${requestScope.userPage.pageNo+1}
                </a>
            </li>
        </c:if>


        <c:if test="${requestScope.userPage.pageNo+2<=requestScope.userPage.pageTotal}" >
            <li>
                <a href="${requestScope.basePath}adminServlet?action=manageUser&pageNo=${requestScope.userPage.pageNo+2}">
                        ${requestScope.userPage.pageNo+2}
                </a>
            </li>
        </c:if>

        <%--            下一页--%>
        <c:if test="${requestScope.userPage.pageNo==requestScope.userPage.pageTotal}">
            <li class="disabled">
                <a aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${requestScope.userPage.pageNo<requestScope.userPage.pageTotal}">
            <li>
                <a href="${requestScope.basePath}adminServlet?action=manageUser&pageNo=${requestScope.userPage.pageNo+1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
        <li><a href="${requestScope.basePath}adminServlet?action=manageUser&pageNo=${requestScope.userPage.pageTotal}">末页</a></li>
    </ul>
</nav>
</body>
</html>
