<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/4
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>笔记链接列表</title>
</head>
<body>
<table width="60%" border="1">
    <tr>
        <%--动态获取虚拟目录，从Session中获取用户信息作为参数传递--%>
        <th bgcolor="#a52a2a"><a href="${pageContext.request.contextPath}/loginSuccess.jsp?userId=<%= session.getAttribute("userId") %>&username=<%= session.getAttribute("username") %>">我的</a></th>
        <th bgcolor="#5f9ea0"><a href="${pageContext.request.contextPath}/noteListServlet?areaId=0&userId=<%= session.getAttribute("userId") %>&username=<%= session.getAttribute("username") %>">首页</a></th>
        <th bgcolor="#5f9ea0"><a href="${pageContext.request.contextPath}/noteListServlet?areaId=1&userId=<%= session.getAttribute("userId") %>&username=<%= session.getAttribute("username") %>">游戏区</a></th>
        <th bgcolor="#5f9ea0"><a href="${pageContext.request.contextPath}/noteListServlet?areaId=2&userId=<%= session.getAttribute("userId") %>&username=<%= session.getAttribute("username") %>">科技区</a></th>
        <th bgcolor="#5f9ea0"><a href="${pageContext.request.contextPath}/noteListServlet?areaId=3&userId=<%= session.getAttribute("userId") %>&username=<%= session.getAttribute("username") %>">美食区</a></th>
        <th bgcolor="#5f9ea0"><a href="${pageContext.request.contextPath}/noteListServlet?areaId=4&userId=<%= session.getAttribute("userId") %>&username=<%= session.getAttribute("username") %>">知识区</a></th>
    </tr>
</table>
<%--采集用户输入数据的表单，以post方式提交给Servlet--%>
<form action="searchServlet" method="post">
    <label for="search">搜索</label>:<input name="search" placeholder="请输入..." id="search"><br>
    搜索对象:<input type="radio" name="object" value="tag">标签<input type="radio" name="object" value="title">笔记标题<br>
    搜索方式:<input type="radio" name="type" value="precise">精确<input type="radio" name="type" value="fuzzy">模糊<br>
    <input type="submit" value="搜索">
</form>
<%--使用jstl，遍历List，将笔记信息逐行写入到链接中--%>
<c:forEach items="${notes}" var="note">
    <a href="${pageContext.request.contextPath}/isLikeFavoriteServlet?title=${note.title}&text=${note.text}&likes=${note.likes}&favorites=${note.favorites}&noteId=${note.id}">${note.title}</a><br>
</c:forEach>
</body>
</html>
