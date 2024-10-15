<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Video List</title>
</head>
<body>
<h1>Video List</h1>
<a href="${pageContext.request.contextPath}/admin/video/add">Add New Video</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>File Name</th>
        <th>Upload Date</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="video" items="${videos}">
        <tr>
            <td>${video.id}</td>
            <td>${video.title}</td>
            <td>${video.description}</td>
            <td>${video.fileName}</td>
            <td>${video.uploadDate}</td>
            <td>
                <a href="${pageContext.request.contextPath}/views/admin/video/edit?id=${video.id}">Edit</a> |
                <a href="${pageContext.request.contextPath}/admin/video/delete?id=${video.id}"
                   onclick="return confirm('Are you sure you want to delete this video?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
