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
		User user = (User)session.getAttribute("user");		
	%>

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
				<form action="index.jsp">

					<!-- User information  -->
					<fieldset>
						<legend>Thank you for registration!</legend>
						<%=user.getName() %> is registered successfully.<br>
						ID : <%=user.getUserID() %><br>
						Email : <%=user.getEmail() %><br>
						Address : <%=user.getAddress() %><br>
					</fieldset>
					<br>

					<!-- Submit button -->
					<input type="submit" value="Home">
					<!--  <input type="hidden" name="action" value="register">-->
					<!-- <input type="submit" value="cancel"> -->
				</form>
			</td>
		</tr>
	</table>
</body>
</html>