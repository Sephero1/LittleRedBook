<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/7
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <c:if test="${requestScope.rank==1}">游戏区</c:if>
        <c:if test="${requestScope.rank==2}">科技区</c:if>
        <c:if test="${requestScope.rank==3}">美食区</c:if>
        <c:if test="${requestScope.rank==4}">知识区</c:if>
        未审核笔记列表
    </title>
</head>
<body>
<%--使用jstl，遍历List，将笔记信息逐行写入到链接中--%>
<c:forEach items="${list}" var="note">
    <a href="${pageContext.request.contextPath}/noteUnCheckDetail.jsp?title=${note.title}&text=${note.text}&rank=<%=request.getParameter("rank")%>&noteId=${note.id}">${note.title}</a><br>
</c:forEach>

<hr><br><br><a href="${pageContext.request.contextPath}/userLoginServlet?username=<%=session.getAttribute("username")%>&password=<%=session.getAttribute("password")%>">返回</a>
</body>
</html>
