<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>sign-up-page</title>
</head>
<body>
	<h1>Sign-Up Here!</h1>
	<form:form action="process-signup" method="POST"
		modelAttribute="signupdto">
	UserName : <form:input path="username" />
		<br>
	Password : <form:password path="password" />
		<br>
		<input type="submit" value="signup" />
	</form:form>
</body>
</html>