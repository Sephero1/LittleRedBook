<%--
  Created by IntelliJ IDEA.
  User: Sephero
  Date: 2022/4/5
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改失败</title>
</head>
<body>
<%--使用jstl和正则表达式，根据flag的值输出对应的内容--%>
<h3>修改失败，原因：
    <c:if test="${requestScope.flag==1}">用户名已存在。</c:if>
    <c:if test="${requestScope.flag==2}">邮箱已存在。</c:if>
    <c:if test="${requestScope.flag==3}">两次输入的密码不同。</c:if>
</h3>
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
