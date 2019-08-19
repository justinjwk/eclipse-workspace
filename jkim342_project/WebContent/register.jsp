<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="jkim342.css">
<title>JK Book Rental</title>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<table>
		<tr>
			<td class="tableImage"><img src="banner.jpg" width=100%
				alt="JK Book Rental Logo" height=100></td>
		</tr>
		<tr>
		<tr>
			<th>JK Book Rental - Register</th>
		</tr>
		<tr>
			<td>
				<form action="registerServlet" method="POST">

					<!-- User information  -->
					<fieldset>
						<legend>New User Registration</legend>
						ID: <input type="text" name="userID"><br> 
						Password: <input type="password" name="password"><br> 
						Name: <input type="text" name="name" value="${user.getName()}" required><br>
						E-mail: <input type="email" name="email"
							value="${user.getEmail()}" required><br> 
						Address: <input	type="text" name="address"><br>
					</fieldset>
					<br>

					<!-- Submit button -->
					<input type="submit" value="Register">
					<!--  <input type="hidden" name="action" value="register">-->
					<!-- <input type="submit" value="cancel"> -->
				</form>
			</td>
		</tr>
	</table>
</body>
</html>