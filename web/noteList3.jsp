<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/7
  Time: 21:08
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
<%--使用jstl，遍历List，将笔记信息逐行写入到链接中--%>
<c:forEach items="${notes}" var="note">
    <a href="${pageContext.request.contextPath}/isLikeFavoriteServlet?title=${note.title}&text=${note.text}&likes=${note.likes}&favorites=${note.favorites}&noteId=${note.id}">${note.title}</a><br>
    <a href="${pageContext.request.contextPath}/noteDeleteServlet?noteId=${note.id}">删除</a><br><hr>
</c:forEach>

<%--刷新按钮--%>
<a href="javascript:void(0);" id="reload">删除后需点此刷新页面</a>
<script>
    <%--函数，使用BOM的location刷新--%>
    function fun() {
        location.reload();
    }
    <%--根据id获取回退按钮对象--%>
    var reload=document.getElementById("reload");
    <%--绑定单击事件--%>
    reload.onclick=fun;
</script>

<hr><br><br><a href="${pageContext.request.contextPath}/userLoginServlet?username=<%=session.getAttribute("username")%>&password=<%=session.getAttribute("password")%>">返回</a>
</body>
</html>
