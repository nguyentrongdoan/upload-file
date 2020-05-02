<%--
  Created by IntelliJ IDEA.
  User: miyuki
  Date: 5/2/20
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<div style="border: 1px solid #ccc; padding: 5px">
    <a href="${pageContext.request.contextPath}/uploadOneFile">Upload One File</a>&nbsp;|&nbsp;
    <a href="${pageContext.request.contextPath}/uploadMultiFile">Upload Multi File</a>
</div>
</body>
</html>
