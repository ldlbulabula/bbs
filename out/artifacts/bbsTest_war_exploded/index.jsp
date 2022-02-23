<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
  <%@include file="./static/common/CommonHead.jsp"%>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>论坛首页</title>

</head>
<body>
<%@include file="static/common/top.jsp"%>


<div>
  <table border="1px" width="100%">
    <tr>
      <td rowspan="2" height="300px" width="50%">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
          <!-- Indicators -->
          <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
<%--            <li data-target="#carousel-example-generic" data-slide-to="2"></li>--%>
          </ol>

          <!-- Wrapper for slides -->
          <div class="carousel-inner" role="listbox">
            <div class="item active">
              <img src="static/common/logo.png" alt="图片1">
              <div class="carousel-caption">

              </div>
            </div>
            <div class="item">
              <img src="static/common/defalHeadPhoto.png" alt="图片2">
              <div class="carousel-caption">

              </div>
            </div>
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
              <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
              <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>

          <!-- Controls -->

        </div>
      </td>
      <th valign="top">
        公告<br>
        1.不要骂人
      </th>
    </tr>
  </table>
</div>
<div>
  <br>
  <font color="red" size="7" face="宋体">分区</font>
</div>
<div>
  <table width="80%" border="1px">
    <tr>
      <td>
        <table >
          <tr><td><a href="${requestScope.basePath}postServlet?action=selectByPage&tag=qianduan">前端</a></td></tr>
          <tr><td></td></tr>
        </table>
      </td>
      <td>
        <table>
          <tr><td><a href="${requestScope.basePath}postServlet?action=selectByPage&tag=houduan">后端</a></td></tr>
          <tr><td></td></tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>
        <table>
          <tr><td><a href="${requestScope.basePath}postServlet?action=selectByPage&tag=dashuju">大数据</a></td></tr>
          <tr><td></td></tr>
        </table>
      </td>
      <td>
        <table>
          <tr><td><a href="${requestScope.basePath}postServlet?action=selectByPage&tag=shenduxuexi">深度学习</a></td></tr>
          <tr><td></td></tr>
        </table>
      </td>
    </tr>
  </table>
</div>

<c:if test="${not empty sessionScope.loginAdmin}">
  <br>
  <h1>当前以管理员身份登录</h1>
</c:if>
</body>
</html>
