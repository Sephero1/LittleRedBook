<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/3
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
<table width="60%" border="1">
    <tr>
        <%--动态获取虚拟目录，从Session中获取用户信息作为参数传递--%>
        <th bgcolor="#a52a2a"><a href="${pageContext.request.contextPath}/loginSuccess.jsp?userId=<%= session.getAttribute("userId") %>&username=<%= session.getAttribute("username") %>">我的</a></th></th>
        <th bgcolor="#5f9ea0"><a href="${pageContext.request.contextPath}/noteListServlet?areaId=0&userId=<%= session.getAttribute("userId") %>&username=<%= session.getAttribute("username") %>">首页</a></th>
        <th bgcolor="#5f9ea0"><a href="${pageContext.request.contextPath}/noteListServlet?areaId=1&userId=<%= session.getAttribute("userId") %>&username=<%= session.getAttribute("username") %>">游戏区</a></th>
        <th bgcolor="#5f9ea0"><a href="${pageContext.request.contextPath}/noteListServlet?areaId=2&userId=<%= session.getAttribute("userId") %>&username=<%= session.getAttribute("username") %>">科技区</a></th>
        <th bgcolor="#5f9ea0"><a href="${pageContext.request.contextPath}/noteListServlet?areaId=3&userId=<%= session.getAttribute("userId") %>&username=<%= session.getAttribute("username") %>">美食区</a></th>
        <th bgcolor="#5f9ea0"><a href="${pageContext.request.contextPath}/noteListServlet?areaId=4&userId=<%= session.getAttribute("userId") %>&username=<%= session.getAttribute("username") %>">知识区</a></th>
    </tr>
</table>
<%--获取用户名，根据权限等级输出称谓--%>
<h3>欢迎您，<c:if test="${sessionScope.rank==1}">游戏区管理员，</c:if>
    <c:if test="${sessionScope.rank==2}">科技区管理员，</c:if>
    <c:if test="${sessionScope.rank==3}">美食区管理员，</c:if>
    <c:if test="${sessionScope.rank==4}">知识区管理员，</c:if>
    <c:if test="${sessionScope.rank==5}">系统管理员，</c:if>
    <%= session.getAttribute("username") %>！</h3><br>

<%--动态获取虚拟目录，超链接到修改用户信息的jsp页面，并且获取用户序号作为参数传递过去--%>
<a href="${pageContext.request.contextPath}/modifyUser.jsp?userId=<%= session.getAttribute("userId") %>">修改个人信息</a><br><br>

<%--动态获取虚拟目录，超链接到修改用户信息的jsp页面，并且获取用户序号作为参数传递过去--%>
<a href="${pageContext.request.contextPath}/noteUpload.jsp?userId=<%= session.getAttribute("userId") %>">上传我的笔记</a><br><br>

<%--动态获取虚拟目录，超链接到查询用户笔记的Servlet，并且获取用户序号作为参数传递过去--%>
<a href="${pageContext.request.contextPath}/noteViewServlet?userId=<%= session.getAttribute("userId") %>">查看我的笔记</a><br><br>

<%--动态获取虚拟目录，超链接到查询用户点赞的笔记的Servlet，并且获取用户序号作为参数传递过去--%>
<a href="${pageContext.request.contextPath}/userLikeFavoriteServlet?userId=<%= session.getAttribute("userId") %>&type=1">查看我点赞的笔记</a><br><br>

<%--动态获取虚拟目录，超链接到查询用户收藏的笔记的Servlet，并且获取用户序号作为参数传递过去--%>
<a href="${pageContext.request.contextPath}/userLikeFavoriteServlet?userId=<%= session.getAttribute("userId") %>&type=2">查看我收藏的笔记</a><br><br>

<%--动态获取虚拟目录，超链接到查询用户评论过的笔记的Servlet--%>
<a href="${pageContext.request.contextPath}/userCommentServlet">查看我评论过的笔记</a><br><br>

<%--如果是管理员则输出管理员相关功能链接--%>
<c:if test="${sessionScope.rank!=0&&sessionScope.rank!=5}">
    <a href="${pageContext.request.contextPath}/findUnCheckServlet?rank=${sessionScope.rank}">审核笔记</a><br>
    <a href="${pageContext.request.contextPath}/noteListServlet2?areaId=${sessionScope.rank}">删除笔记</a>
</c:if>

<c:if test="${sessionScope.rank==5}">
    <a href="${pageContext.request.contextPath}/noteListServlet2?areaId=${sessionScope.rank}">删除笔记</a>
</c:if>
</body>
</html>
