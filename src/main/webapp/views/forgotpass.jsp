<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form action="/laptrinhWebbuoi2/views/forgotpass" method="post">
			<label for="email">Email:</label> 
			<input type="email" id="email" name="email" class="input-field" required><br> 
			${alert}<br>
			<button class="btn btn-primary" type="submit">Lay lai mat khau</button><br>
			<a href="/laptrinhWebbuoi2/views/login">Quay lai Login</a>
		</form>
</body>
</html>