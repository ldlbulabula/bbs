
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String scheme = request.getScheme();//http
    String serverName = request.getServerName();//ip地址
    int port = request.getServerPort();//端口号
    String contextPath = request.getContextPath();//工程路径
    String href=scheme+"://"+serverName+":"+port+contextPath+"/";
    request.setAttribute("basePath",href);
%>
<base href="<%=href%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="static/css/bootstrap.min.css">

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="static/js/jquery-3.2.1.min.js" ></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="static/js/bootstrap.min.js" ></script>

<script>
    window.onload=function () {
        let code_img = document.getElementById("codeImg");
        code_img.onclick=function () {
            code_img.src="${requestScope.basePath}kaptcha.jpg?jpg="+new Date();
        }
    }
</script>