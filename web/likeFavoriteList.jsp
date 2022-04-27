<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/6
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <%--根据type的值来输出，type=1为点赞，type=2为收藏--%>
        <c:if test="${requestScope.type==1}">我点赞的笔记</c:if>
        <c:if test="${requestScope.type==2}">我收藏的笔记</c:if>
    </title>
</head>
<body>
<%--使用jstl，遍历List，将笔记信息逐行写入到链接中--%>
<c:forEach items="${notes}" var="note">
    <a href="${pageContext.request.contextPath}/isLikeFavoriteServlet?title=${note.title}&text=${note.text}&likes=${note.likes}&favorites=${note.favorites}&noteId=${note.id}">${note.title}</a><br>
</c:forEach>

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
