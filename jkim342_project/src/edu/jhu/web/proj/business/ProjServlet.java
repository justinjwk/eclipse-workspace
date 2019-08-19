package edu.jhu.web.proj.business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.jhu.web.proj.sql.util.SQLUtil;

@WebServlet("/ProjServlet")
public class ProjServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProjServlet() {

    }

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		
		String url = "/index.jsp";

		ServletContext sc = getServletContext();
		
		
		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
        String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
//		double hotel = 0.0;
//		double parking = 0.0;
//		
//		if (request.getParameter("hotel") != null) {
//			hotel = Double.parseDouble(request.getParameter("hotel"));
//		}
//		
//		if (request.getParameter("parking") != null) {
//			parking = Double.parseDouble(request.getParameter("parking")); 
//		}
		
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		String action = request.getParameter("action");
				
		if (action == null) {
			action = "cart";
		}
		
		if (action.equals("edit")) {
			url = "/index.jsp";
		}
		else if (action.equals("login")) {
			url = "/login.jsp";
		}
		else if (action.equals("cart")) {
			
			if (user == null) {
//				user = new User(name, email, status, courses, hotel, parking);
			}
			else {
				user.setName(name);
				user.setEmail(email);
//				user.setStatus(status);
//				user.setCourses(courses);
//				user.setHotel(hotel);
//				user.setParking(parking);
			}
				
			session.setAttribute("user", user);
			url = "/cart.jsp";
			
		}
		
		else if (action.equals("register")) {
			
			url = "/register.jsp";
			
//			getServletContext().getRequestDispatcher(url).forward(request, response);

		}
		
		
		else if (action.equals("confirm")) {
			
			url = "/checkout.jsp";
			
			/**********  DB related ************/
			String sqlResult = "";
			       
			try {
	 
	        	// get a connection
	            Class.forName("com.mysql.jdbc.Driver");
	            String dbURL = "jdbc:mysql://remotemysql.com:3306/g1ib8PipYv";
	            String dbUsername = "g1ib8PipYv";
	            String dbPassword = "f76Xu64PP5";
	            Connection connection = DriverManager.getConnection(
	                    dbURL, dbUsername, dbPassword);

	            // create a statement
	            Statement statement = connection.createStatement();

	            // SQL Statement
	            // Insert an user to User table
	            String userInsertSQL = "";
//	            userInsertSQL = "INSERT INTO User "
//						+ "(userName, userEmail, userStatus) " + 
//						"VALUES ('" + user.getName() + "', '" + 
//						user.getEmail() + "', '" + user.getStatus() + "');";
	            
	            int userId = 0;
	            
	            PreparedStatement pstmt = connection.prepareStatement(userInsertSQL, 
	            		Statement.RETURN_GENERATED_KEYS);
	            pstmt.executeUpdate();
	            ResultSet rs = pstmt.getGeneratedKeys();
	            if (rs.next()) {
	            	userId = rs.getInt(1);
	            }

	            // SQL Statement
	            // Insert a registration to Registration table
//	            String registrationInsertSQL = "";
//	            registrationInsertSQL = "INSERT INTO Registration "
//						+ "(userID, hotelFee, parkingFee, total) " + 
//						"VALUES (" + userId + ", " + 
////						user.getHotel() + ", " + user.getParking() + ", " +
////						user.getTotal() + ");";
//	            
//	            int registrationId = 0;
	            
//	            pstmt = connection.prepareStatement(registrationInsertSQL, 
//	            		Statement.RETURN_GENERATED_KEYS);
//	            pstmt.executeUpdate();
//	            rs = pstmt.getGeneratedKeys();
//	            if (rs.next()) {
//	            	registrationId = rs.getInt(1);
//	            }
	            
	            // SQL Statement
	            // Insert selected courses to Reg_Courses table
//	            String reg_coursesInsertSQL = "";
//	            reg_coursesInsertSQL = "INSERT INTO Reg_Courses " +
//	            		"(registrationID, courseID, courseFee) " +
//	            		"VALUES ";
//	            for (String course: user.getCourses()) {
//	            	reg_coursesInsertSQL += "(" + registrationId + ", " + 
//	            			course.charAt(1) + ", " + user.getCourseFee() +"),";
//	            }
//	            reg_coursesInsertSQL = reg_coursesInsertSQL.substring(0, reg_coursesInsertSQL.length() - 1);
//	            reg_coursesInsertSQL += ";";
//	            
//	            int reg_coursesId = 0;
////	            
//	            pstmt = connection.prepareStatement(reg_coursesInsertSQL, 
//	            		Statement.RETURN_GENERATED_KEYS);
//	            pstmt.executeUpdate();
//	            rs = pstmt.getGeneratedKeys();
//	            if (rs.next()) {
//	            	reg_coursesId = rs.getInt(1);
//	            }
	           

//	            // To retrieve user information from DB
//	            String getUserSQL = "SELECT userName, userEmail, userStatus "
//	            		+ "FROM User "
//	            		+ "WHERE userID = " + userId;
//	            
//	            ResultSet resultSet = statement.executeQuery(getUserSQL);
//	            sqlResult = SQLUtil.getHtmlUserTable(resultSet);
//	            
//	            
//	            // To retrieve selected courses information from DB
//	            String getCoursesSQL = "SELECT courseName, courseFee "
//	            		+ "FROM Reg_Courses RC, Course C "
//	            		+ "WHERE RC.courseID = C.courseID "
//	            		+ "AND registrationID = " + registrationId;
//	            
//	            resultSet = statement.executeQuery(getCoursesSQL);
//	            sqlResult += SQLUtil.getHtmlCoursesTable(resultSet);
//	            
//	            
//	            // To retrieve all fees from DB
//	            String getFeesSQL = "SELECT hotelFee, parkingFee, total "
//	            		+ "FROM Registration "
//	            		+ "WHERE registrationID = " + registrationId;
//	            
//	            resultSet = statement.executeQuery(getFeesSQL);
//	            sqlResult += SQLUtil.getHtmlFeesTable(resultSet);
//	            
//	            
//	            resultSet.close();
//	            statement.close();
//	            connection.close();
	            
	        } catch (ClassNotFoundException e) {
	            sqlResult = "<p>Error loading the databse driver: <br>"
	                    + e.getMessage() + "</p>";
	        } catch (SQLException e) {
	            sqlResult = "<p>Error executing the SQL statement: <br>"
	                    + e.getMessage() + "</p>";
	        }

	        
	        session.setAttribute("sqlResult", sqlResult);

	        url = "/checkout.jsp";
	        getServletContext()
	                .getRequestDispatcher(url)
	                .forward(request, response);		
		
		}

		else if (action.equals("remove")) {
//			String removedCourse = request.getParameter("removeCourse");
//			user.removeCourse(removedCourse);
//			url = "/cart.jsp";
		}
		
		sc.getRequestDispatcher(url).forward(request, response);
		
	}

}
