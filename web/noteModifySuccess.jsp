<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/5
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>笔记编辑成功</title>
</head>
<body>
<h3>笔记编辑成功，请等待重新审核......</h3>
<%--回退按钮--%>
<a href="javascript:void(0);" id="back">返回</a>
<script>
    <%--函数，使用BOM的history回退--%>
    function fun() {
        history.back();
        history.back();
    }
    <%--根据id获取回退按钮对象--%>
    var back=document.getElementById("back");
    <%--绑定单击事件--%>
    back.onclick=fun;
</script>
</body>
</html>
