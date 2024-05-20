<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login-Page</title>
</head>
<body>
	<h1>My Custom Login Page</h1>
	<form:form action="process-login" method="POST">
UserName : <input type="text" name="username" />
		<br/>
Password : <input type="password" name="password" />
		<br/>
		<input type="submit" value="Login" />
	</form:form>
</body>
</html>