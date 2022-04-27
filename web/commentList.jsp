<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/7
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%--获取笔记标题--%>
    <title>${requestScope.title}的评论区</title>
</head>
<body>
<%--采集用户输入数据的表单，以post方式提交给Servlet--%>
<form action="commentAddServlet?textId=${requestScope.noteId}&title=${requestScope.title}&objectId=0" method="post">
    <label for="text">评论</label>:<input name="text" placeholder="请输入评论内容" id="text"><br>
    <input type="submit" value="评论">
</form>
<%--使用jstl，遍历List，将评论信息逐行输出--%>
<c:forEach items="${requestScope.list}" var="comment">
    ${comment.text}<br><hr>
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
