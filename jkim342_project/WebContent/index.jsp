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
	<%@ page import="edu.jhu.web.proj.business.User"%>
	<%
		HttpSession s = request.getSession();

		User user = (User)s.getAttribute("user");

		if (user == null) {
			user = new User();
		}
		
		
	%>

	<table>
		<tr>
			<td class="tableImage"><img src="banner.jpg" width=100% alt="JK Book Rental Logo"
				height=100></td>
		</tr>
		<tr>
			<th>JK Book Rental</th>
		</tr>
		<tr>
			<td>
					<!-- Login  -->
					<fieldset>
						<legend>Login</legend>
						<form action="ProjServlet" method="POST">
							
							<label>ID : </label>
							<input type="text" name="name"><br> 
							<label>Password : </label>
							<input type="password" name="password"><br>
							<input type="submit" value="Login">
							<input type="hidden" name="action" value="login">
							
						</form>
						<form action="ProjServlet" method="POST">
							<input type="submit" value="New User Register">
							<input type="hidden" name="action" value="register">
						</form>
					</fieldset>
					<br>

					<!-- Search  -->
					<fieldset>
						<legend>Search Books</legend>
						<select name="searchKeyword">
							<option value="title">Title</option>
							<option value="author">Author</option>
							<option value="genre">Genre</option>
							<option value="rating">Rating</option>
							<option value="rentalStatus">Rental Status</option>
						</select>
						<input type="text" name="searchKeyword">
						<input type="submit" value="Search">
						<input type="hidden" name="action" value="search">
					</fieldset>
					<br>

					<!-- Best Seller -->
					<fieldset>
						<legend>Best Seller</legend>				
						
					</fieldset>
					<br>
					
					<!-- New Books -->
					<fieldset>
						<legend>New Books</legend>
					</fieldset>

				</form>
			</td>
		</tr>
		<tr>
			<td><a href="inventory_login.jsp">Admin Page</a></td>
		</tr>
	</table>
</body>
</html>