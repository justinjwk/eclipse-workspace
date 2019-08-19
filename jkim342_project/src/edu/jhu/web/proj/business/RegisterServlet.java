package edu.jhu.web.proj.business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RegisterServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		String url = "register.jsp";

		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
        String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		
		
		User user = new User (userID, password, name, email, address);
		
		
		
		String sqlResult = "";
		
		try {
			
			// get a connection
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://remotemysql.com:3306/g1ib8PipYv";
			String dbUsername = "g1ib8PipYv";
			String dbPassword = "f76Xu64PP5";
			Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			
			System.out.println("Here");
			
			
			// create a statement
			Statement statement = connection.createStatement();
			
			String findUserSQL= "";
			findUserSQL = "SELECT userID FROM User "
					+ "WHERE userID='" + userID + "';";
			
			System.out.println(findUserSQL);
			
			ResultSet resultSet = statement.executeQuery(findUserSQL);
			System.out.println("resultSet = " + resultSet);
			
			resultSet.next();
			
			sqlResult = resultSet.getString(1).toString();
			
			System.out.println("hello" + sqlResult);
			
			
			if (!userID.equals(sqlResult)) {
				
				String insertUserSQL= "";
				insertUserSQL = "INSERT INTO User "
						+ "(userID, name, address, email, password) "
						+ "VALUES('" + userID + "', '"
								+ name + "', '"
								+ address + "', '"
								+ email + "', '"
								+ password + "');";
				
				System.out.println(insertUserSQL);
				
				int userIndex = 0;
				
				PreparedStatement pstmt = connection.prepareStatement(insertUserSQL, 
	            		Statement.RETURN_GENERATED_KEYS);
	            pstmt.executeUpdate();
	            ResultSet rs = pstmt.getGeneratedKeys();
	            if (rs.next()) {
	            	userIndex = rs.getInt(1);
	            }
				
				
			}
			else {
				url = "existing_user.jsp";
				System.out.println("userID is already exist");
			}
			
			
			
		} catch (ClassNotFoundException e) {
            sqlResult = "<p>Error loading the databse driver: <br>"
                    + e.getMessage() + "</p>";
        } catch (SQLException e) {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
        }
		
        HttpSession session = request.getSession();
        session.setAttribute("sqlResult", sqlResult);
        session.setAttribute("user", user);

        url = "/register_complete.jsp";
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
		
	}

}
