<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
    <title>Add Video</title>
</head>
<body>
<h1>Add New Video</h1>
<form action="${pageContext.request.contextPath}/admin/video/insert" method="post" enctype="multipart/form-data">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required/><br/><br/>

    <label for="description">Description:</label>
    <textarea id="description" name="description" required></textarea><br/><br/>

    <label for="file">Select File:</label>
    <input type="file" id="file" name="file" required/><br/><br/>

    <input type="submit" value="Add Video"/>
</form>
<a href="${pageContext.request.contextPath}/admin/videos">Back to Video List</a>
</body>
</html>
