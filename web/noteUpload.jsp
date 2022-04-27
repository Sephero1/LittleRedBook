<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/4
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>笔记上传</title>
</head>
<body>
<%--采集用户输入数据的表单，以post方式提交给Servlet，并且获取用户序号作为参数传递过去--%>
<form action="noteUploadServlet?userId=<%= request.getParameter("userId") %>" method="post">
    <label for="title">*标题</label>:<input name="title" placeholder="请输入标题" id="title"><br>
    <label for="text">*正文</label>:<br><textarea name="text" id="text" cols="100" rows="50"></textarea><br>
    *分区:<select name="areaId" id="areaId">
    <option value="">--请选择--</option>
    <option value="1">游戏区</option>
    <option value="2">科技区</option>
    <option value="3">美食区</option>
    <option value="4">知识区</option>
</select><br>
    <label for="tag1">标签1</label>:<input name="tag1" placeholder="可选，请输入标签1" id="tag1"><br>
    <label for="tag2">标签2</label>:<input name="tag2" placeholder="可选，请输入标签2" id="tag2"><br>
    <label for="tag3">标签3</label>:<input name="tag3" placeholder="可选，请输入标签3" id="tag3"><br>
    <input type="submit" value="上传">
</form>
</body>
</html>
