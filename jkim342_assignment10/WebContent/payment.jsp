<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="jkim342.css">
<title>JHU Annual Software Development Seminar</title>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="edu.jhu.web.mod10.business.User"%>
	<%
		User user = (User)session.getAttribute("user");		
	%>
	
	<table>
	
		<!-- Logo  -->
		<tr>
			<td class="tableImage" colspan="3"><img src="jhu_logo.png"
				alt="JHU Logo" height=150></td>
		</tr>
		<!-- Title -->
		<tr>
			<th class="bottomBorder" colspan="4">JOHNS HOPKINS ANNUAL
				SOFTWARE DEVELOPMENT SEMINAR</th>
		</tr>

		<!-- User Information -->
		<tr>
			<td colspan="3"><br><br>
				Dear <b><%=user.getName()%></b>, 
				Your total cost is <b>$<%=user.getTotal() %></b>
			</td>
		</tr>
			<td colspan="3">
				<form action="Module10Servlet" method="POST">
				
					<fieldset>
						<legend>Payment Details</legend>
						Credit Card Type
						<input type="radio" name="creditCardType" value="Discover">Discover &nbsp;
						<input type="radio" name="creditCardType" value="Master Card">Master Card &nbsp;
						<input type="radio" name="creditCardType" value="VISA">VISA &nbsp;
						<br>
						Credit Card Number
						<input type="text" name="creditCardNumber">
						<br>
						Expiration Data
						<select name="month">
							<option value="1">January</option>
							<option value="2">February</option>
							<option value="3">March</option>
							<option value="4">April</option>
							<option value="5">May</option>
							<option value="6">June</option>
							<option value="7">July</option>
							<option value="8">August</option>
							<option value="9">September</option>
							<option value="10">October</option>
							<option value="11">November</option>
							<option value="12">December</option>
						</select>
						<select name="date">
							<%
								for(int i = 1; i < 32; i++) {
									out.print("<option value=\"" + i + "\">" + i + "</option>");
								}
							%>
						</select>
					</fieldset>
				</form>
			</td>
		<tr>
			<td>
				<form action="" method="post">
					<input type="hidden" name="action" value="edit"> 
					<input type="submit" value="Edit Information">
				</form>
			</td>
			<td>
				<form action="" method="post">
					<input type="hidden" name="action" value="edit"> 
					<input type="submit" value="Add More Courses">
				</form>
			</td>
			<td>
				<form action="" method="post">
					<input type="hidden" name="action" value="confirm"> 
					<input type="submit" value="Confirm Registration">
				</form>
			</td>
			
		</tr>
	</table>

</body>
</html>