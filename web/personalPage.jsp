<%@ page import="Bean.PersonalPage" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>${requestScope.personalPage[0].username}的个人主页</title>
    <%@include file="static/common/CommonHead.jsp"%>
    <script>
        $(function () {
            $(".deletePost").click(function () {
                return confirm("是否将帖子删除？");
            })
        })
    </script>
</head>
<body>
    <%@include file="static/common/top.jsp"%>
    <div>
        <table width="80%" align="center" border="1px" style="word-wrap:break-word;word-break:break-all;" >
            <tr>
                <td rowspan="2" ><img alt="用户已经不存在" width="190px" height="190px" src="${requestScope.personalPage[0].head_photo}"> </td>

                <td width="90%">${requestScope.personalPage[0].username}</td>

            </tr>
            <tr>
                <td>这个人很神秘，什么都没写</td>
            </tr>

        </table>
    </div>
    <br>
    <div>
        <table width="80%" align="center" border="1px" style="word-wrap:break-word;word-break:break-all;" >
            <c:if test="${not empty requestScope.personalPage[0].title}">
                <tr>
                    <th colspan="2" style="text-align: center"><h3>该用户发表过的帖子</h3> </th>
                </tr>
            </c:if>

            <c:if test="${empty requestScope.personalPage[0].title}">
                <tr>
                    <th colspan="2" style="text-align: center"><h3>暂无帖子</h3> </th>
                </tr>
            </c:if>

            <c:if test="${not empty requestScope.personalPage[0].title}">
            <c:forEach items="${requestScope.personalPage}" var="personalPage">
                <tr>
                    <td>
                        <h4><a target="_blank" href="${basePath}postServlet?action=showPostDetail&post_id=${personalPage.post_id}">${personalPage.title}</a> </h4>
                        <div><h6>所属专区：<a href="${basePath}postServlet?action=selectByPage&tag=${personalPage.tag}" target="_blank">
                            <c:if test="${personalPage.tag=='qianduan'}">
                                前端
                            </c:if>
                            <c:if test="${personalPage.tag=='houduan'}">
                                后端
                            </c:if>
                            <c:if test="${personalPage.tag=='dashuju'}">
                                大数据
                            </c:if>
                            <c:if test="${personalPage.tag=='shenduxuexi'}">
                                深度学习
                            </c:if>
                        </a></h6></div>
                        <div><h6>发布时间：${personalPage.date}</h6></div>
                    </td>

                    <c:if test="${requestScope.personalPage[0].username==sessionScope.loginUser.username || not empty sessionScope.loginAdmin}">
                        <td>
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <c:if test="${requestScope.personalPage[0].username==sessionScope.loginUser.username || requestScope.personalPage[0].username==sessionScope.loginAdmin.adminName}">
                                        <li><a href="${basePath}postServlet?action=updatePost&post_id=${personalPage.post_id}">修改</a></li>
                                    </c:if>
                                    <li><a class="deletePost" href="${basePath}postServlet?action=deletePost&post_id=${personalPage.post_id}&author=${requestScope.personalPage[0].username}">删除</a></li>
                                </ul>
                            </nav>
                        </td>
                    </c:if>

                </tr>
            </c:forEach>
            </c:if>
        </table>
    </div>
</body>
</html>