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
		${sqlResult}

		<!-- Menu Buttons -->
		<tr>
			<td>
				<form action="" method="post">
					<input type="hidden" name="action" value="edit"> <input
						type="submit" value="Go Back to Main Page">
				</form>
			</td>
		</tr>

	</table>