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
	
		String url = "/index.html";

		ServletContext sc = getServletContext();
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String status = request.getParameter("employmentstatus");
		String courses[] = request.getParameterValues("course");
//		String additionalFees = request.getParameter("additionalFee");
		
		String action = request.getParameter("action");
		if (action == null) {
			action = "cost";
		}
		
		if (action.equals("edit")) {
			url = "/index.html";
		}
		else if (action.equals("cost")) {
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("cost");
			if (user == null) {
				user = new User(name, email, status, courses);
			}
			
			session.setAttribute("cost", user);
			url = "/cost.jsp";
			
		}
		else if (action.equals("confirm")) {
			url = "/checkout.jsp";
		}
		
		sc.getRequestDispatcher(url).forward(request, response);
		
		
	}

}
