package edu.jhu.web.mod9.business;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.jhu.web.mod9.util.MailUtilGmail;

@WebServlet("/Module9Servlet")
public class Module9Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Module9Servlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url = "/index.jsp";

		ServletContext sc = getServletContext();
	
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String status = request.getParameter("status");
		String courses[] = request.getParameterValues("course");
		double hotel = 0.0;
		double parking = 0.0;
		
		if (request.getParameter("hotel") != null) {
			hotel = Double.parseDouble(request.getParameter("hotel"));
		}
		
		if (request.getParameter("parking") != null) {
			parking = Double.parseDouble(request.getParameter("parking")); 
		}
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		String action = request.getParameter("action");
				
		if (action == null) {
			action = "cart";
		}
		
		if (action.equals("edit")) {
			url = "/index.jsp";
		}
		else if (action.equals("cart")) {
			
			if (user == null) {
				user = new User(name, email, status, courses, hotel, parking);
			}
			else {
				user.setName(name);
				user.setEmail(email);
				user.setStatus(status);
				user.setCourses(courses);
				user.setHotel(hotel);
				user.setParking(parking);
			}
				
			session.setAttribute("user", user);
			url = "/cart.jsp";
			
		}
		else if (action.equals("confirm")) {
			
			String to = user.getEmail();
			String from = "jkim342jhu@gmail.com";
			String subject = "JHU Software Development Seminar Reistration Confirmation";
			String body = "";
			boolean isBodyHTML = true;
			
			String courseSelectionListMessage = "";			
			for (String course : user.getCourses()) {
				courseSelectionListMessage += "<tr>\n" + 
						"				<td class=\"bottomBorder\">" + course + "</td>\n" + 
						"				<td class=\"bottomBorder\"></td>\n" + 
						"				<td class=\"cost\">" + user.getCourseFee() + "</td>\n" + 
						"               <td class=\"bottomBorder\"></td>\n" + 
						"			</tr>";
			}
			
			String hotelParkingFeeMessage = "";
			if (user.isHotelChecked()) {
				hotelParkingFeeMessage += "<tr>\n" +
						"<td class=\"bottomBorder\">Hotel Accommodation</td>\n" +
						"<td class=\"bottomBorder\"></td>\n" +
						"<td class=\"cost\">" + "$" + user.getHotel() + "</td>\n" +
						"<td class=\"bottomBorder\"></td>\n" +
					  "</tr>\n";
			}
			if (user.isParkingChecked()) {
				hotelParkingFeeMessage += "<tr>\n" +
						"<td class=\"bottomBorder\">Parking</td>\n" +
						"<td class=\"bottomBorder\"></td>\n" +
						"<td class=\"cost\">" + "$" + user.getParking() + "</td>\n" +
						"<td class=\"bottomBorder\"></td>\n" +
					  "</tr>\n";
			}
			
			String cssString = "<head>\n" + 
								"<style>\n" + 
					"body {background-color: black;}\n" + 
					"table {\n" + 
					"	border-collapse: collapse;\n" + 
					"	background-color: #FFFFE0;\n" + 
					"}\n" +  
					"td {\n" + 
					"	padding: 8px;\n" +  
					"	text-align: left;\n" + 
					"}\n" + 
					"th {\n" + 
					"	text-algin: center;\n" + 
					"	font-family: Arial;\n" + 
					"	padding: 20px;\n" + 
					"}\n" + 
					"img {\n" + 
					"	display: block;\n" + 
					"	margin-left: auto;\n" + 
					"	margin-right: auto;\n" + 
					"}\n" + 
					".tableImage {\n" + 
					"	background-color: #1e376b;\n" + 
					"}\n" + 
					".cost {\n" + 
					"    text-align: right;\n" + 
					"    border-bottom: 1px solid;\n" + 
					"}\n" + 
					".bottomBorder {\n" + 
					"    border-bottom: 1px solid;\n" + 
					"}\n" + 
					"</style>\n" + 
					"</head>\n"; 
			
			user.calculateTotal();
			
			body += cssString +
					"<body>\n" +
					"<table>\n" +
					"<!-- Title -->\n" + 
					"		<tr>\n" + 
					"			<th class=\"bottomBorder\" colspan=\"4\">JOHNS HOPKINS ANNUAL\n" + 
					"				SOFTWARE DEVELOPMENT SEMINAR</th>\n" + 
					"		</tr>\n" + 
					"\n" + 
					"		<!-- User Information -->\n" + 
					"		<tr>\n" + 
					"			<td colspan=\"4\">\n" + 
					"				<h2><b>" + user.getName() + "</b></h2>\n" + 
					"			</td>\n" + 
					"		</tr>\n" + 
					"		<tr>\n" + 
					"			<td colspan=\"4\">You are registered as a \n" + 
					"				<b>" + user.getStatus() + "</b>\n" + 
					"			</td>\n" + 
					"		</tr>\n" + 
					"		<tr>\n" + 
					"			<td colspan=\"4\">Your e-mail confirmation will be sent to: \n" + 
					"				<b>" + user.getEmail() + "</b>\n" + 
					"			</td>\n" + 
					"		</tr>\n" +
					"       <tr>\n" +
					"           <td>If you do not receive the e-mail confirmation or " +
					"               if you need to update your registration information, " +
					"               please contact the conference committtee at" +
					"               <a href=\"mailto:registration@seminar.jhu.edu\">registration@seminar.jhu.edu</a>" +
					"               or at (410)410-4100." +
					"           </td>\n" +
					"       </tr>\n" +
					"       <!-- Course List -->\n" + 
					"		<tr>\n" + 
					"			<th class=\"bottomBorder\" colspan=\"2\">Your Courses</th>\n" + 
					"\n" + 
					"			<th class=\"bottomBorder\">Cost</th>\n" + 
					"			<th class=\"bottomBorder\"></th>\n" + 
					"		</tr>\n" + courseSelectionListMessage +
					"<tr>\n" + 
					"			<td colspan=\"4\"><br></td>\n" + 
					"		</tr>\n" + 
					"		<!-- Additional Fee List -->		\n" + 
					hotelParkingFeeMessage + "\n" + 
					"		<!-- Total -->\n" + 
					"		<tr>\n" + 
					"			<td class=\"bottomBorder\"></td>\n" + 
					"			<td class=\"cost\"><b>Total</b></td>\n" + 
					"			<td class=\"cost\">" + user.getTotal() + "</td>\n" + 
					"			<td class=\"bottomBorder\"></td>\n" + 
					"		</tr>\n" + 
					"</table>\n" +
					"</body>\n";
			
			try {
				MailUtilGmail.sendMail(to, from, subject, body, isBodyHTML);
			} catch (MessagingException e) {
				String errorMessage = "ERROR: Unable to send email. " 
						+ "Check Tomcat logs for details.<br>"
						+ "NOTE: You may need to configure your system " 
						+ "as described in chapter 14.<br>"
						+ "ERROR MESSAGE: " + e.getMessage();
				request.setAttribute("errorMessage", errorMessage);
				this.log(
						"Unable to send email. \n" 
						+ "Here is the email you tried to send: \n"
						+ "=====================================\n" 
						+ "TO: " + email + "\n" 
						+ "FROM: " + from + "\n"
						+ "SUBJECT: " + subject + "\n" 
						+ "\n" 
						+ body + "\n\n");
			}
			
			url = "/checkout.jsp";
		}

		else if (action.equals("remove")) {
			String removedCourse = request.getParameter("removeCourse");
			user.removeCourse(removedCourse);
			url = "/cart.jsp";
		}
		
		sc.getRequestDispatcher(url).forward(request, response);
		
	}

}
