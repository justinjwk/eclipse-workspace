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
			<th>JK Book Rental - Inventory Login</th>
		</tr>
		<tr>
			<td>

				<fieldset>
				<legend>Admin Login for Inventory</legend>
					<b>Admin Password is not correct. Please click back button</b>
					<form action="inventory_login.jsp">
						<input type="submit" value="Back">
					</form>
				</fieldset> 
			</td>
		</tr>
	</table>
</body>
</html>