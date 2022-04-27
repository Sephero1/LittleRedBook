<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/5
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息修改成功</title>
</head>
<body>
<h3>个人信息修改成功</h3>
<%--动态获取虚拟目录，获取用户名作为参数传递--%>
<a href="${pageContext.request.contextPath}/loginSuccess.jsp?username=<%=session.getAttribute("username")%>">返回到个人主页</a>
</body>
</html>
