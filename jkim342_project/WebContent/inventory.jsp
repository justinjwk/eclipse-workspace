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
			<th>JK Book Rental - Inventory</th>
		</tr>
		<tr>
			<td>
				<fieldset>
					<legend>Register New Book</legend>
					<form action="InventoryServlet" method="POST">
						<label>Title : </label> 
							<input type="text" name="title"><br>
						<label>Author : </label> 
							<input type="text" name="author"><br>
						<label>Genre : </label> 
							<select name="genre">
								<option value="fantasy">Fantasy</option>
								<option value="scienceFiction">Science Fiction</option>
								<option value="romance">Romance</option>
								<option value="thriller">Thriller</option>
								<option value="mystery">Mystery</option>
								<option value="western">Western</option>
								<option value="biography">Biography</option>
								<option value="poetry">Poetry</option>
							</select><br> 
						<label>Description : </label> 
							<input type="text" name="description"><br> 
						<label>Published Date : </label> 
							<input type="date" name="publishedDate"><br>
						<label>Rating : </label> 
							<select name="rating">
								<option value="1.0">1</option>
								<option value="2.0">2</option>
								<option value="3.0">3</option>
								<option value="4.0">4</option>
								<option value="5.0">5</option>
							</select><br> 
						<input type="submit" value="Add New Book">
						<input type="hidden" name="action" value="addNewBook">
					</form>
				</fieldset>
			</td>
		</tr>
		<tr>
			<td>
				<fieldset>
				<legend>Book Inventory</legend>
					${sqlResult}
				</fieldset>	
			</td>
		</tr>
	</table>
	
</body>
</html>