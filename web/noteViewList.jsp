<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/5
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的笔记</title>
</head>
<body>
<%--使用jstl，遍历List，将笔记信息逐行写入到链接中--%>
<c:forEach items="${notes}" var="note">
    <a href="${pageContext.request.contextPath}/isLikeFavoriteServlet?title=${note.title}&text=${note.text}&likes=${note.likes}&favorites=${note.favorites}&noteId=${note.id}">${note.title}</a><br>
    <a href="${pageContext.request.contextPath}/noteModify.jsp?noteId=${note.id}&title=${note.title}&text=${note.text}&areaId=${note.areaId}&tag1=${note.tag1}&tag2=${note.tag2}&tag3=${note.tag3}">编辑</a>
    <a href="${pageContext.request.contextPath}/noteDeleteServlet?noteId=${note.id}">删除</a><br><br><br>
</c:forEach>

<br>
<%--刷新按钮--%>
<a href="javascript:void(0);" id="reload">编辑或删除后需点此刷新页面</a>

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
<br><br><hr><br><br>

<%--回退按钮--%>
<a href="javascript:void(0);" id="back">返回</a>

<script>
    <%--函数，使用BOM的history回退--%>
    function fun() {
        history.back();
    }
    <%--根据id获取回退按钮对象--%>
    var back=document.getElementById("back");
    <%--绑定单击事件--%>
    back.onclick=fun;
</script>
</body>
</html>
