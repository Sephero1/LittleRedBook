<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/5
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑笔记</title>
</head>
<body>
<%--采集用户输入数据的表单，以post方式提交给Servlet，并且获取笔记序号作为参数传递过去--%>
    <form action="noteModifyServlet?noteId=<%=request.getParameter("noteId")%>" method="post">
        <label for="title">*标题</label>:<input name="title" id="title" value="<%=request.getParameter("title")%>"><br>
        <label for="text">*正文</label>:<br><textarea name="text" id="text" cols="100" rows="50" ><%=request.getParameter("text")%></textarea><br>
        *分区:<select name="areaId" id="areaId">
        <option value="">--请选择--</option>
        <option value="1">游戏区</option>
        <option value="2">科技区</option>
        <option value="3">美食区</option>
        <option value="4">知识区</option>
    </select><br>
        <label for="tag1">标签1</label>:<input name="tag1" id="tag1" value="<%=request.getParameter("tag1")%>"><br>
        <label for="tag2">标签2</label>:<input name="tag2" id="tag2" value="<%=request.getParameter("tag2")%>"><br>
        <label for="tag3">标签3</label>:<input name="tag3" id="tag3" value="<%=request.getParameter("tag3")%>"><br>
        <input type="submit" value="编辑">
    </form>
</body>
</html>
