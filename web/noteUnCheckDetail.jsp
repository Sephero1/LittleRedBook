<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/7
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=request.getParameter("title")%></title>
</head>
<body>
<%=request.getParameter("text")%>
<br><br><br><hr>
<%--采集用户输入数据的表单，以post方式提交给Servlet--%>
<form action="noteCheckServlet?noteId=<%=request.getParameter("noteId")%>&rank=<%=request.getParameter("rank")%>" method="post">
    审核结果:<input type="radio" name="check" value="1">通过<input type="radio" name="check" value="2">不通过<br>
    <input type="submit" value="提交">
</form>
</body>
</html>
