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
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ page import="edu.jhu.web.mod4.business.User"%>
	<%
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String status = request.getParameter("employmentstatus");
		/* String[] courses = request.getParameter("course"); */
		/* String[] additionalFees[] = request.getParameter("additionalFee"); */

		User user = new User(name, email, status, null);
	%>

	<table>
		<tr>
			<td class="tableImage" colspan="3"><img src="jhu_logo.png"
				alt="JHU Logo" height=150></td>
		</tr>
		<tr>
			<th class="bottomBorder" colspan="3">JOHNS HOPKINS ANNUAL
				SOFTWARE DEVELOPMENT SEMINAR</th>
		</tr>

		<tr>
			<td colspan="3"><h2>
					<b><%=user.getName()%></b>
				</h2></td>
		</tr>
		<tr>
			<td colspan="3">You are registered as a <b><%=user.getStatus()%></b>
			</td>
		</tr>
		<tr>
			<td colspan="3">Your e-mail confirmation will be sent to: <b><%=user.getEmail()%></b>
			</td>
		</tr>

		<tr>
			<th class="bottomBorder" colspan="2">Your Courses</th>

			<th class="bottomBorder">Cost</th>
		</tr>
		<c:forEach items="${paramValues['course']}" var="selectedValue">
			<tr>
				<td class="bottomBorder"><c:out value="${selectedValue}" /></td>
				<td class="bottomBorder"></td>
				<td class="cost">$<%=user.getCourseFee()%> <% user.addTotal(user.getCourseFee()); %>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3"><br></td>
		</tr>

		<c:forEach items="${paramValues['additionalFee']}" var="selectedValue">
			<tr>
				<td class="bottomBorder">
					<c:out value="${selectedValue}" />
				</td>
				<td class="bottomBorder"></td>
				<td class="cost">$ 
					<c:if test = "${selectedValue == 'Hotel Accommodation'}"> 
						<% 
							user.setHotel(185.00);
							user.addTotal(185.00);
							out.print("185.00");
						%> 
						
					</c:if>
					<c:if test = "${selectedValue == 'Parking Permit'}" > 
						<%
							if (user.getHotel() == 0) {
								user.setParking(10.00);
								user.addTotal(10.00);
								out.println("10.0");
							}
							else {
								user.setParking(0.00);
								out.println("0.0");
							}
						 %>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		

		<tr>
			<td class="bottomBorder"></td>
			<td class="cost"><b>Total</b></td>
			<td class="cost">$<%= user.getTotal() %></td>
		</tr>

		<tr>
			<td colspan="3">
				<form action="index.html" method="post">
					<input type="submit" value="Return">
				</form>
			</td>
		</tr>
	</table>

</body>
</html>