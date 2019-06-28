package edu.jhu.web.mod4.business;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Module4Servlet
 */
@WebServlet("/Module4Servlet")
public class Module4Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Module4Servlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url = "cost.jsp";

		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String status = request.getParameter("employmentstatus");
		String[] courses = request.getParameterValues("course");		

		User user = new User(name, email, status, courses);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/cost.jsp");

		dispatcher.forward(request, response);

		
	}

}
