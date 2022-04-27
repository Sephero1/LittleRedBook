<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/4
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%--获取笔记标题--%>
    <title><%=request.getParameter("title")%></title>
</head>
<body>
<%--获取笔记正文--%>
<%= request.getParameter("text") %><br><br><hr><br><br>
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
<br>
<%--点赞按钮--%>
<c:if test="${requestScope.likeFlag==true}"><a href="${pageContext.request.contextPath}/unLikeFavoriteServlet?title=${requestScope.title}&text=${requestScope.text}&likes=${requestScope.likes}&favorites=${requestScope.favorites}&noteId=${requestScope.noteId}&likeFlag=${requestScope.likeFlag}&favoriteFlag=${requestScope.favoriteFlag}&type=1">已点赞：${requestScope.likes}</a></c:if>
<c:if test="${requestScope.likeFlag==false}"><a href="${pageContext.request.contextPath}/likeFavoriteServlet?title=${requestScope.title}&text=${requestScope.text}&likes=${requestScope.likes}&favorites=${requestScope.favorites}&noteId=${requestScope.noteId}&likeFlag=${requestScope.likeFlag}&favoriteFlag=${requestScope.favoriteFlag}&type=1">点赞：${requestScope.likes} </a></c:if>
<br>
<%--收藏按钮--%>
<c:if test="${requestScope.favoriteFlag==true}"><a href="${pageContext.request.contextPath}/unLikeFavoriteServlet?title=${requestScope.title}&text=${requestScope.text}&likes=${requestScope.likes}&favorites=${requestScope.favorites}&noteId=${requestScope.noteId}&likeFlag=${requestScope.likeFlag}&favoriteFlag=${requestScope.favoriteFlag}&type=2">已收藏：${requestScope.favorites}</a></c:if>
<c:if test="${requestScope.favoriteFlag==false}"><a href="${pageContext.request.contextPath}/likeFavoriteServlet?title=${requestScope.title}&text=${requestScope.text}&likes=${requestScope.likes}&favorites=${requestScope.favorites}&noteId=${requestScope.noteId}&likeFlag=${requestScope.likeFlag}&favoriteFlag=${requestScope.favoriteFlag}&type=2">收藏：${requestScope.favorites} </a></c:if>
<br>
<%--评论区链接--%>
<a href="${pageContext.request.contextPath}/commentFindTextIdServlet?textId=${requestScope.noteId}&title=${requestScope.title}">进入评论区</a>
</body>
</html>
