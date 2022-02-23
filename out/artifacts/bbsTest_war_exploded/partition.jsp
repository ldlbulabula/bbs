<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.attrTag}</title>
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
        <table width="80%" align="center" >
            <tr>
                <td> <h1>${requestScope.attrTag}</h1>    共${requestScope.page.pageTotal}页，${requestScope.page.totalCount}条记录</td>
                <td> <a class="btn btn-default" href="${requestScope.basePath}sendPost.jsp" role="button">发帖</a> </td>
            </tr>
        </table>
    </div><br>
    <c:if test="${requestScope.page.totalCount==0}">
        <br><br><br>
        <table width="80%" align="center">
            <tr>
                <th>暂无数据</th>
            </tr>
        </table>
    </c:if>
    <c:if test="${requestScope.page.totalCount>0}">
    <div>
        <table class="table table-hover" width="80%" align="center" >
            <c:forEach items="${requestScope.page.items}" var="item">
                <tr>
                    <td>
                        <h4><a target="_blank" href="${requestScope.basePath}postServlet?action=showPostDetail&post_id=${item.post_id}">${item.title}</a> </h4>
                        <div><h6><a target="_blank" href="${basePath}userServlet?action=showPersonalPage&username=${item.author}"></a>作者：${item.author}</h6></div>
                        <div><h6>发布时间：${item.date}</h6></div>
                    </td>
                    <c:if test="${not empty sessionScope.loginAdmin}">
                        <td>
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <li><a class="deletePost" href="${basePath}postServlet?action=deletePost&post_id=${item.post_id}&tag=${item.tag}&pageNo=${requestScope.page.pageNo}">删除</a></li>
                                </ul>
                            </nav>
                        </td>
                    </c:if>

                </tr>
            </c:forEach>


        </table>
    </div>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${param.pageNo>requestScope.page.pageTotal}">
                <script>
                    location.href="${requestScope.basePath}postServlet?action=selectByPage&pageNo=${requestScope.page.pageTotal}&tag=${requestScope.tag}";
                </script>
            </c:if>

            <li><a href="${requestScope.basePath}postServlet?action=selectByPage&pageNo=1&tag=${requestScope.tag}">首页</a></li>
<%--            上一页--%>
            <c:if test="${requestScope.page.pageNo==1}">
                <li class="disabled">
                    <a aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${requestScope.page.pageNo>1}">
                <li>
                    <a href="${requestScope.basePath}postServlet?action=selectByPage&pageNo=${requestScope.page.pageNo-1}&tag=${requestScope.tag}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>



            <c:if test="${requestScope.page.pageNo-2>=1}">
                <li>
                    <a href="${requestScope.basePath}postServlet?action=selectByPage&pageNo=${requestScope.page.pageNo-2}&tag=${requestScope.tag}">
                            ${requestScope.page.pageNo-2}
                    </a>
                </li>
            </c:if>


            <c:if test="${requestScope.page.pageNo-1>=1}">
                <li>
                    <a href="${requestScope.basePath}postServlet?action=selectByPage&pageNo=${requestScope.page.pageNo-1}&tag=${requestScope.tag}">
                        ${requestScope.page.pageNo-1}
                    </a>
                </li>
            </c:if>


            <li class="active">
                <a href="${requestScope.basePath}postServlet?action=selectByPage&pageNo=${requestScope.page.pageNo}&tag=${requestScope.tag}">
                    ${requestScope.page.pageNo}
                </a>
            </li>


        <c:if test="${requestScope.page.pageNo+1<=requestScope.page.pageTotal}" >
            <li>
                <a href="${requestScope.basePath}postServlet?action=selectByPage&pageNo=${requestScope.page.pageNo+1}&tag=${requestScope.tag}">
                        ${requestScope.page.pageNo+1}
                </a>
            </li>
        </c:if>


        <c:if test="${requestScope.page.pageNo+2<=requestScope.page.pageTotal}" >
            <li>
                <a href="${requestScope.basePath}postServlet?action=selectByPage&pageNo=${requestScope.page.pageNo+2}&tag=${requestScope.tag}">
                    ${requestScope.page.pageNo+2}
                </a>
            </li>
        </c:if>







<%--            下一页--%>
            <c:if test="${requestScope.page.pageNo==requestScope.page.pageTotal}">
                <li class="disabled">
                    <a aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
                <li>
                    <a href="${requestScope.basePath}postServlet?action=selectByPage&pageNo=${requestScope.page.pageNo+1}&tag=${requestScope.tag}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <li><a href="${requestScope.basePath}postServlet?action=selectByPage&pageNo=${requestScope.page.pageTotal}&tag=${requestScope.tag}">末页</a></li>
        </ul>
    </nav>
    </c:if>
</body>
</html>
