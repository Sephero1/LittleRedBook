<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/5
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>搜索结果</title>
</head>
<body>
    <h3>搜索结果：</h3>
    <%--使用jstl，遍历List，将笔记信息逐行写入到链接中--%>
    <c:forEach items="${notes}" var="note">
        <a href="${pageContext.request.contextPath}/isLikeFavoriteServlet?title=${note.title}&text=${note.text}&likes=${note.likes}&favorites=${note.favorites}&noteId=${note.id}">${note.title}</a><br>
    </c:forEach>
    <%--回退按钮--%>
    <br><br><hr><br><br><a href="javascript:void(0);" id="back">返回</a>
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
