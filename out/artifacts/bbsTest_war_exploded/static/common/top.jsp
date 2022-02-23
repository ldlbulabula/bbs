<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    window.onload=function () {
        let logout = document.getElementById("logout");
        logout.onclick=function () {
            return confirm("你确定要退出登录吗？")
        }
    }
</script>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${basePath}index.jsp">BBS论坛</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
                <li ><a href="${basePath}index.jsp">论坛首页<span class="sr-only">(current)</span></a></li>
                <li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
            </ul>
            <form action="${basePath}postServlet" method="post" class="navbar-form navbar-left">
                <input type="hidden" name="action" value="searchPosts">
                <div class="form-group">
                    <input type="text" name="search" class="form-control" placeholder="输入作者或标题查找帖子">
                </div>
                <button type="submit" class="btn btn-default">查找</button>
            </form>
            <ul class="nav navbar-nav">
                <li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
                <c:if test="${empty sessionScope.loginUser &&empty sessionScope.loginAdmin}">
                    <li ><a href="${basePath}admin/login.jsp">管理员登录<span class="sr-only">(current)</span></a></li>
                </c:if>
                <c:if test="${not empty sessionScope.loginAdmin}">
                    <li ><a target="_blank" href="${basePath}adminServlet?action=manageUser&pageNo=1">管理用户<span class="sr-only">(current)</span></a></li>
                </c:if>
                <li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty sessionScope.loginUser && empty sessionScope.loginAdmin}">
                    <li><a title="用户登录" href="${basePath}User/login.jsp">用户登录</a></li>
                    <li><a title="用户注册" href="${basePath}User/regist.jsp">用户注册</a></li>
                </c:if>
                <c:if test="${not empty sessionScope.loginUser ||not empty sessionScope.loginAdmin}">
                    <c:if test="${not empty sessionScope.loginUser}">
                        <li><a title="个人主页" href="${basePath}userServlet?action=showPersonalPage&username=${sessionScope.loginUser.username}">${sessionScope.loginUser.username}</a></li>
                    </c:if>
                    <c:if test="${not empty sessionScope.loginAdmin}">
                        <li><a title="个人主页" href="${basePath}userServlet?action=showPersonalPage&username=${sessionScope.loginAdmin.adminName}">${sessionScope.loginAdmin.adminName}</a></li>
                    </c:if>
                    <li><a title="注销登录" id="logout" href="${basePath}userServlet?action=logout">注销登录</a></li>
                </c:if>

            </ul>
        </div>
    </div>
</nav>
