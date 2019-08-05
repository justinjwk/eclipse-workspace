package edu.jhu.web.mod7.business;

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

import edu.jhu.web.mod7.sql.util.SQLUtil;

@WebServlet("/Module7Servlet")
public class Module7Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Module7Servlet() {

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
		
		int id = 1;
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
//			url = "/index.jsp";
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
			
			/**********  DB related ************/
			String sqlResult = "";
			

			String selectAllStatement = "SELECT * FROM Registration";
			
//			// SELECT statement for checking User table
//			String getRegistration_ByID_SQL = ""
//					+ "SELECT "
//					+ "	* "
//					+ "from "
//					+ "	Registration R"
//					+ "		LEFT JOIN"
//					+ "			User U"
//					+ "		ON"
//					+ "			U.userID = R.registration_id"
//					+ "WHERE"
//					+ "	R.registration_id = " + geID+ " AND"
//					+ "	userID = " + getUserIDFomSession +;	
//			
//			String getDetailRegistration_ByID_SQL = ""
//					+ "SELECT "
//					+ "	* "
//					+ "from "
//					+ "	Reg_Courses RC"
//					+ "		LEFT JOIN"
//					+ "			Course C"
//					+ "		ON"
//					+ "			C.courseID = RC.regCoursesID"
//					+ "WHERE"
//					+ "	RC.registrationID = " + geID+ ";";	
	       
			try {
	 
	        	// get a connection
	            Class.forName("com.mysql.jdbc.Driver");
	            String dbURL = "jdbc:mysql://remotemysql.com:3306/GUwoVc4A5G";
	            String username = "GUwoVc4A5G";
	            String password = "FC4xecsubH";
	            Connection connection = DriverManager.getConnection(
	                    dbURL, username, password);

	            // create a statement
	            Statement statement = connection.createStatement();

	            // SQL Statement to insert an user to User table
	            String userInsertSQL = "";
	            userInsertSQL = "INSERT INTO User "
						+ "(userName, userEmail, userStatus) " + 
						"VALUES ('" + user.getName() + "', '" + 
						user.getEmail() + "', '" + user.getStatus() + "');";
	            
	            int userId = 0;
	            
	            PreparedStatement pstmt = connection.prepareStatement(userInsertSQL, 
	            		Statement.RETURN_GENERATED_KEYS);
	            pstmt.executeUpdate();
	            ResultSet rs = pstmt.getGeneratedKeys();
	            if (rs.next()) {
	            	userId = rs.getInt(1);
	            }

	            // SQL Statement to insert a registration to Registration table
	            String registrationInsertSQL = "";
	            registrationInsertSQL = "INSERT INTO Registration "
						+ "(userID, hotelFee, parkingFee, total) " + 
						"VALUES (" + userId + ", " + 
						user.getHotel() + ", " + user.getParking() + ", " +
						user.getTotal() + ");";
	            
	            int registrationId = 0;
	            
	            pstmt = connection.prepareStatement(registrationInsertSQL, 
	            		Statement.RETURN_GENERATED_KEYS);
	            pstmt.executeUpdate();
	            rs = pstmt.getGeneratedKeys();
	            if (rs.next()) {
	            	registrationId = rs.getInt(1);
	            }
	            
	            // SQL Statement to insert courses to Reg_Courses table
	            String reg_coursesInsertSQL = "";
	            reg_coursesInsertSQL = "INSERT INTO Reg_Courses " +
	            		"(registrationID, courseID, courseFee) " +
	            		"VALUES ";
	            for (String course: user.getCourses()) {
	            	reg_coursesInsertSQL += "(" + registrationId + ", " + 
	            			course.charAt(1) + ", " + user.getCourseFee() +"),";
	            }
	            reg_coursesInsertSQL = reg_coursesInsertSQL.substring(0, reg_coursesInsertSQL.length() - 1);
	            reg_coursesInsertSQL += ";";
	            
	            int reg_coursesId = 0;
	            
	            pstmt = connection.prepareStatement(reg_coursesInsertSQL, 
	            		Statement.RETURN_GENERATED_KEYS);
	            pstmt.executeUpdate();
	            rs = pstmt.getGeneratedKeys();
	            if (rs.next()) {
	            	reg_coursesId = rs.getInt(1);
	            }
	            
	            System.out.println("UserID = " + userId);
	            System.out.println("RegistrationID = " + registrationId);
	            System.out.println("SQL Statment for REG_COURSES = " + reg_coursesInsertSQL);
	            
	            // Adding courses to Couser Table
//	            INSERT INTO Course (courseName)
//	            VALUES ('A1 - Web Services'),
//	            	   ('A2 - J2EE Design Patterns'),
//	                   ('A3 - Service Oriented Architectures'),
//	                   ('A4 - Enterprise Service Bus'),
//	                   ('A5 - Secure Messaging'),
//	                   ('A6 - Web Service Security');
	                   

	            
	            // check the User table after insert the user
	            ResultSet resultSet = statement.executeQuery(selectAllStatement);
	            sqlResult = SQLUtil.getHtmlTable(resultSet);
	            resultSet.close();


	            statement.close();
	            connection.close();
	        } catch (ClassNotFoundException e) {
	            sqlResult = "<p>Error loading the databse driver: <br>"
	                    + e.getMessage() + "</p>";
	        } catch (SQLException e) {
	            sqlResult = "<p>Error executing the SQL statement: <br>"
	                    + e.getMessage() + "</p>";
	        }

	        
	        session.setAttribute("sqlResult", sqlResult);
//	        session.setAttribute("sqlStatement", userInsert);

	        url = "/sqlgateway.jsp";
	        getServletContext()
	                .getRequestDispatcher(url)
	                .forward(request, response);		
		
		}

		else if (action.equals("remove")) {
			String removedCourse = request.getParameter("removeCourse");
			user.removeCourse(removedCourse);
			url = "/cart.jsp";
		}
		
		sc.getRequestDispatcher(url).forward(request, response);
		
	}

}