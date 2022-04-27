<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/5
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>笔记上传成功</title>
</head>
<body>
    <%--获取笔记标题--%>
    <h3><%=request.getParameter("title")%> 上传成功，正在等待管理员审核</h3>
    <%--动态获取虚拟目录--%>
    <a href="${pageContext.request.contextPath}/loginSuccess.jsp">返回到个人主页</a>
</body>
</html>
