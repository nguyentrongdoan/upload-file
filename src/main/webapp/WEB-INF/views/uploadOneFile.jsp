<%--
  Created by IntelliJ IDEA.
  User: miyuki
  Date: 5/1/20
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Upload One File</title>
</head>
<body>
<jsp:include page="_menu.jsp"/>
<h3>Upload One File:</h3>

<form:form modelAttribute="myUploadForm" method="POST" action=""
enctype="multipart/form-data">
    Description:
    <br>
    <form:input path="description" cssStyle="width: 300px;"/>
    <br/><br/>
    File to upload : <form:input path="fileDatas" type="file"/><br/>


    <input type="submit" value="Upload">

</form:form>
</body>
</html>
