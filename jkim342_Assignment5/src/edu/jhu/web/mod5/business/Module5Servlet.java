package edu.jhu.web.mod5.business;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Module5Servlet")
public class Module5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Module5Servlet() {

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
