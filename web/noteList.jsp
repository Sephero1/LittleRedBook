<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/4
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>笔记列表</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>笔记序号</th>
            <th>笔记标题</th>
            <th>笔记正文</th>
            <th>分区序号</th>
            <th>点赞数</th>
            <th>收藏数</th>
            <th>评论数</th>
            <th>标签1</th>
            <th>标签2</th>
            <th>标签3</th>
            <th>审核状态</th>
            <th>所属用户序号</th>
        </tr>
        <%--使用jstl，遍历List，将笔记信息逐行加入到表格中--%>
        <c:forEach items="${notes}" var="note">
            <tr>
                <td>${note.id}</td>
                <td>${note.title}</td>
                <td>${note.text}</td>
                <td>${note.areaId}</td>
                <td>${note.likes}</td>
                <td>${note.favorites}</td>
                <td>${note.comments}</td>
                <td>${note.tag1}</td>
                <td>${note.tag2}</td>
                <td>${note.tag3}</td>
                <td>${note.check}</td>
                <td>${note.userId}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
