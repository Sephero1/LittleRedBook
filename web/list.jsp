<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/3/31
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户信息列表</title>
</head>
<body>
<table border="1">
    <tr>
        <th>用户序号</th>
        <th>用户名</th>
        <th>密码</th>
        <th>性别</th>
        <th>生日</th>
        <th>邮箱</th>
        <th>权限等级</th>
        <th>禁用分区</th>
        <th>点赞笔记序号</th>
        <th>收藏笔记序号</th>
    </tr>
    <%--使用jstl，遍历List，将用户信息逐行加入到表格中--%>
    <c:forEach items="${users}" var="user">
    <tr>
        <td>${user.id}</td>
        <td>${user.userName}</td>
        <td>${user.password}</td>
        <td>${user.sex}</td>
        <td>${user.birthday}</td>
        <td>${user.email}</td>
        <td>${user.rank}</td>
        <td>${user.blacklist}</td>
        <td>${user.likes}</td>
        <td>${user.favorites}</td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
