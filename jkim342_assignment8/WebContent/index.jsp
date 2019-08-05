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
	<%@ page import="edu.jhu.web.mod8.business.User"%>
	<%
		HttpSession s = request.getSession();

		User user = (User)s.getAttribute("user");

		if (user == null) {
			user = new User();
		}
	%>

	<table>
		<tr>
			<td class="tableImage"><img src="jhu_logo.png" alt="JHU Logo"
				height=150></td>
		</tr>
		<tr>
			<th>JOHNS HOPKINS ANNUAL SOFTWARE DEVELOPMENT SEMINAR</th>
		</tr>
		<tr>
			<td>
				<form action="Module8Servlet" method="POST">

					<!-- User information  -->
					<fieldset>
						<legend>Contact Information</legend>
						Name: <input type="text" name="name" value="${user.getName()}"
							required><br> E-mail: <input type="email"
							name="email" value="${user.getEmail()}" required><br>
					</fieldset>
					<br>

					<!-- Course selection (List Multiple Selection) -->
					<fieldset>
						<legend>Select Your Courses</legend>
						<select name="course" size="6" multiple required>
							<%
								if(user.getCourses() == null) {
									for (String course : user.getAllCourses()) {
										out.print("<option value=\"" + course + "\">" + course + "</option>");
									}
								}
								else {
									for (String course : user.getAllCourses()) {
										
										Boolean found = false;
										for (String selectedCourse : user.getCourses()) {
											if (course.equals(selectedCourse)) {
												found = true;
											}
										}
										if (found) {
											out.print("<option value=\"" + course + "\" selected>" + course + "</option>");	
										}
										else {
											out.print("<option value=\"" + course + "\">" + course + "</option>");	
										}
									}
								}
							%>
						</select>
					</fieldset>
					<br>

					<!-- User status (Radio button) -->
					<fieldset>
						<legend>Employment Status</legend>				
						<input type="radio" name="status" value="JHU Employee" 
							<% if (user.getStatus() != null && user.getStatus().equals("JHU Employee")) out.print("checked");
							%> required >JHU Employee 
						<input type="radio" name="status" value="JHU Student"
							<% if (user.getStatus() != null && user.getStatus().equals("JHU Student")) out.print("checked");
							%> >JHU Student 
						<input type="radio" name="status" value="Speaker"
							<% if (user.getStatus() != null && user.getStatus().equals("Speaker")) out.print("checked");%> 
							>Speaker
						<input type="radio" name="status" value="Other"
							<% if (user.getStatus() != null && user.getStatus().equals("Other")) out.print("checked");%> 
							>Other
					</fieldset>
					<br>

					<!-- Additional Fee (Check Box) No parking if the hotel is selected -->
					<fieldset>
						<legend>Additional Fees and Charges</legend>
						<input type="checkbox" name="hotel"	value="185.00" 
						<% if (user.isHotelChecked()) out.print("checked");%>
						>Hotel Accommodation(Conference Guest Special Fee - Parking Included)<br> 
						<input type="checkbox" name="parking" value="10.00"
						<% if (user.isParkingChecked()) out.print("checked");%>
						>Parking Permit
					</fieldset>
					<br>

					<!-- Submit button -->
					<input type="submit" value="Compute Seminar Costs"> <input
						type="hidden" name="action" value="cart"> <br> <br>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>