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
	<%@ page import="edu.jhu.web.mod11.business.User"%>
	<%
		User user = (User)session.getAttribute("user");		
	%>

	<table>
	
		<!-- Logo  -->
		<tr>
			<td class="tableImage" colspan="4"><img src="jhu_logo.png"
				alt="JHU Logo" height=150></td>
		</tr>
		<!-- Title -->
		<tr>
			<th class="bottomBorder" colspan="4">JOHNS HOPKINS ANNUAL
				SOFTWARE DEVELOPMENT SEMINAR</th>
		</tr>
		
		<!-- Registration Completion Message -->
		<tr>
			<th colspan="4">Thank you for the registration!</th>
		</tr>

		<!-- User Information -->
		<tr>
			<td colspan="4">
				<h2><b><%=user.getName()%></b></h2>
			</td>
		</tr>
		<tr>
			<td colspan="4">You are registered as a 
				<b><%=user.getStatus()%></b>
			</td>
		</tr>
		<tr>
			<td colspan="4">Your e-mail confirmation will be sent to: 
				<b><%=user.getEmail()%></b>
			</td>
		</tr>
		<tr>
			<td colspan="4">If you do not receive the e-mail confirmation 
			or if you need to update your registration information, 
			please contact the conference committtee at
			<a href=\"mailto:registration@seminar.jhu.edu\"> 
			registration@seminar.jhu.edu</a> or at (410)410-4100.
			</td>
		</tr>

		<!-- Course List -->
		<tr>
			<th class="bottomBorder" colspan="2">Your Courses</th>

			<th class="bottomBorder">Cost</th>
			<th class="bottomBorder"></th>
		</tr>
		<c:forEach var="course" items="${user.getCourses()}">
			<tr>
				<td class="bottomBorder"><c:out value="${course}" /></td>
				<td class="bottomBorder"></td>
				<td class="cost">$<%=user.getCourseFee()%></td>
				<td class="bottomBorder"></td>
			</tr>
		</c:forEach>

		<tr>
			<td colspan="4"><br></td>
		</tr>

		<!-- Additional Fee List -->		
		<%
			if (user.isHotelChecked()) {
				out.print("<tr>" + "\n" +
							"<td class=\"bottomBorder\">Hotel Accommodation</td>" + "\n" +
							"<td class=\"bottomBorder\"></td>" + "\n" +
							"<td class=\"cost\">" + "$" + user.getHotel() + "</td>" + "\n" +
							"<td class=\"bottomBorder\"></td>" + "\n" +
						  "</tr>"
				);
			}
		%>
		
		<%
			if (user.isParkingChecked()) {
				out.print("<tr>" + "\n" +
							"<td class=\"bottomBorder\">Parking</td>" + "\n" +
							"<td class=\"bottomBorder\"></td>" + "\n" +
							"<td class=\"cost\">" + "$" + user.getParking() + "</td>" + "\n" +
							"<td class=\"bottomBorder\"></td>" + "\n" +
						  "</tr>"
				);
			}
		%>

		<!-- Total -->
		<tr>
			<td class="bottomBorder"></td>
			<td class="cost"><b>Total</b></td>
			<td class="cost"><% user.calculateTotal(); %>$<%= user.getTotal() %></td>
			<td class="bottomBorder"></td>
		</tr>

		<!-- Menu Buttons -->
		<tr>
			<td colspan="4">
				<fieldset>
					<legend>More Actions:</legend>
					<form action="" method="post">
						<input type="hidden" name="action" value="printPDF">
						<input type="submit" value="Print to PDF">
					</form>
					<form action="" method="post">
						<input type="hidden" name="action" value="printExcel">
						<input type="submit" value="Print to Excel">
					</form>
				</fieldset>
			</td>
		</tr>
		<tr>
			<td>
				<form action="" method="post">
					<input type="hidden" name="action" value="edit"> 
					<input type="submit" value="Go Back to Main Page">
				</form>
			</td>
		</tr>
	</table>

</body>
</html>