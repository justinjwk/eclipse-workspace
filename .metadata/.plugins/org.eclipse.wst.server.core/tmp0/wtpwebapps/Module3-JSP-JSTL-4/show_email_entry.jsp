<!doctype html public "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
  <title>Email List application</title>
</head>
<body>
<%@ page import="edu.jhu.web.mod3.business.User, edu.jhu.web.mod3.data.UserIO, java.util.*, java.io.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 

    String fileName = "/tmp/jhu/UserEmail.txt";
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    String emailAddress = request.getParameter("emailAddress");
    
    User user = new User(firstName, lastName, emailAddress);
    UserIO.addRecord(user,fileName);
 
    Vector v =  (Vector) UserIO.readRecords(fileName);
    pageContext.setAttribute("vector", v);
    
%>

<h1>Thanks for joining our email list</h1>

<p>Here is the information that you entered:</p>

  <table cellspacing="5" cellpadding="5" border="1">
    <tr>
      <td align="right">First name:</td>
      <td><%= user.getFirstName() %>&nbsp;</td>
    </tr>
    <tr>
      <td align="right">Last name:</td>
      <td><%= user.getLastName() %>&nbsp;</td>
    </tr>
    <tr>
      <td align="right">Email address:</td>
      <td><%= user.getEmail() %>&nbsp;</td>
    </tr>
  </table>
  
 
  <hr>
    <h1>Registered Users</h1>
	<table cellspacing="5" cellpadding="5" border="1">
		<tbody>
			<tr><th>First Name</th><th>Last Name</th><th>Email </th></tr>
				<c:forEach items="${vector}" var="item" >
					<tr><td><c:out value="${item.firstName}"></c:out></td>
					<td><c:out value="${item.lastName}"></c:out></td>
					<td><c:out value="${item.email}"></c:out></td></tr>
				</c:forEach>
		</tbody>
	</table>



<p>To enter another email address, click on the Back <br>
button in your browser or the Return button shown <br>
below.</p>

<form action="join_email_list.html" method="post">
  <input type="submit" value="Return">
</form>
</body>
</html>