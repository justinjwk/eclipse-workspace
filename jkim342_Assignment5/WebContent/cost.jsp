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
	<%@ page import="edu.jhu.web.mod5.business.User"%>
	<%
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String status = request.getParameter("employmentstatus");
		String[] courses = request.getParameterValues("course");
		/* String[] additionalFees[] = request.getParameterValues("additionalFee"); */ 

		User user = new User(name, email, status, courses);
	%>

	<table>
		<tr>
			<td class="tableImage" colspan="4"><img src="jhu_logo.png"
				alt="JHU Logo" height=150></td>
		</tr>
		<tr>
			<th class="bottomBorder" colspan="4">JOHNS HOPKINS ANNUAL
				SOFTWARE DEVELOPMENT SEMINAR</th>
		</tr>

		<tr>
			<td colspan="4"><h2>
					<b><%=user.getName()%></b>
				</h2></td>
		</tr>
		<tr>
			<td colspan="4">You are registered as a <b><%=user.getStatus()%></b>
			</td>
		</tr>
		<tr>
			<td colspan="4">Your e-mail confirmation will be sent to: <b><%=user.getEmail()%></b>
			</td>
		</tr>

		<tr>
			<th class="bottomBorder" colspan="2">Your Courses</th>

			<th class="bottomBorder">Cost</th>
			<th class="bottomBorder"></th>
		</tr>
		
		<c:forEach var="course" items="${paramValues['course']}">
			<tr>
				<td class="bottomBorder"><c:out value="${course}" /></td>
				<td class="bottomBorder"></td>
				<td class="cost">$<%=user.getCourseFee()%> <% user.addTotal(user.getCourseFee()); %></td>
				<td class="bottomBorder">
					<form action="" method="post">
						<input type="submit" value="Remove">
						<input type="hidden" name="removeCourse" value="${course}">
						<input type="hidden" name="removeCourseFee" value="${user.getCourseFee()}">
					</form>
				</td>
			</tr>
		</c:forEach>
		
		
		<%-- <c:forEach items="${paramValues['course']}" var="selectedValue">
			<tr>
				<td class="bottomBorder"><c:out value="${selectedValue}" /></td>
				<td class="bottomBorder"></td>
				<td class="cost">$<%=user.getCourseFee()%> <% user.addTotal(user.getCourseFee()); %></td>
				<td class="bottomBorder">
					<form action="" method="post">
						<input type="submit" value="Remove">
					</form>
				</td>
			</tr>
		</c:forEach> --%>
		<tr>
			<td colspan="4"><br></td>
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
				<td class="bottomBorder"></td>
			</tr>
		</c:forEach>
		

		<tr>
			<td class="bottomBorder"></td>
			<td class="cost"><b>Total</b></td>
			<td class="cost">$<%= user.getTotal() %></td>
			<td class="bottomBorder"></td>
		</tr>

		<tr>
			<td>
				<form action="" method="post">
					<input type="hidden" name="action" value="edit">
					<input type="submit" value="Edit Information">
				</form>
			</td>
			<td>
				<form action="" method="post">
					<input type="hidden" name="action" value="add">
					<input type="submit" value="Add More Courses">
				</form>
			</td>
			<td></td>
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