<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/3
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%--动态获取虚拟目录--%>
<a href="${pageContext.request.contextPath}/register.jsp">没有用户？立即注册</a><br>
<%--采集用户输入数据的表单，以post方式提交给Servlet--%>
<form action="userLoginServlet?firstLogin=1" method="post">
    <label for="username">用户名</label>:<input name="username" placeholder="请输入用户名" id="username"><br>
    <label for="password">密码</label>:<input type="password" name="password" placeholder="请输入密码" id="password"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
