<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/3
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改个人信息</title>
</head>
<body>
<%--采集用户输入数据的表单，以post方式提交给Servlet，并且获取用户序号作为参数传递过去--%>
<form action="userModifyServlet?userId=<%= request.getParameter("userId") %>" method="post">
    <label for="username">用户名</label>:<input name="username" placeholder="请输入用户名" id="username"><br>
    <label for="password">密码</label>:<input type="password" name="password" placeholder="请输入密码" id="password"><br>
    <label for="password2">请确认密码</label>:<input type="password" name="password2" placeholder="请再次输入密码" id="password2"><br>
    性别:<input type="radio" name="sex" value="男">男<input type="radio" name="sex" value="女">女<br>
    生日:<input type="date" name="birthday"><br>
    <label for="email">邮箱</label>:<input name="email" placeholder="请输入邮箱" id="email"><br>
    <input type="submit" value="修改">
</form>
</body>
</html>
