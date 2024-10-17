<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Video</title>
</head>
<body>
<h1>Edit Video</h1>
<form action="${pageContext.request.contextPath}/admin/video/update" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${video.videoId}"/>

    <label for="title">Title:</label>
    <input type="text" id="title" name="title" value="${video.title}" required/><br/><br/>

    <label for="description">Description:</label>
    <textarea id="description" name="description" required>${video.description}</textarea><br/><br/>

    <label for="file">Select New File:</label>
    <input type="file" id="file" name="file"/><br/>
    <small>Current file: ${video.filename}</small><br/><br/>

    <input type="submit" value="Update Video"/>
</form>
<a href="${pageContext.request.contextPath}/admin/videos">Back to Video List</a>
</body>
</html>
