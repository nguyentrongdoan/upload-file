<%--@elvariable id="description" type="form"--%>
<%--
  Created by IntelliJ IDEA.
  User: miyuki
  Date: 5/2/20
  Time: 9:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Result</title>
</head>
<body>
<jsp:include page="_menu.jsp"/>
<h3>Uploaded Files:</h3>
Description: ${description}
<br/>

<%--@elvariable id="uploadedFiles" type="java.util.List"--%>
<c:forEach items="${uploadedFiles}" var="file">
    - ${file}<br>
</c:forEach>
</body>
</html>
