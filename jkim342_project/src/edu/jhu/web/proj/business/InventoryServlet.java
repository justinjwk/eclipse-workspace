package edu.jhu.web.proj.business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.jhu.web.proj.sql.util.SQLUtil;

@WebServlet("/InventoryServlet")
public class InventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public InventoryServlet() {
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
		
		String url = "/inventory.jsp";
		
		String action = request.getParameter("action");
		
		
		String dbURL;
		String dbUserName;
		String dbPassword;
		Connection connection;
		
		String sqlResult = "";
		String selectAllBooksSQL= "SELECT * FROM Book;";
//		String sqlStatement="SELECT 'title', 'author', 'genre', "
//				+ "'publishedDate', 'rating' "
//				+ "FROM Books";
		
		 try {
			 
	        	// get a connection
	           
			    Class.forName("com.mysql.jdbc.Driver"); 
	            dbURL = "jdbc:mysql://remotemysql.com:3306/g1ib8PipYv";
	            dbUserName = "g1ib8PipYv";
	            dbPassword = "f76Xu64PP5";
	            connection = DriverManager.getConnection(
	                    dbURL, dbUserName, dbPassword);

	            // create a statement
	            Statement statement = connection.createStatement();

	            // parse the SQL string
	            

	            ResultSet resultSet
	                            = statement.executeQuery(selectAllBooksSQL);
	            
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
		
		if (action.equals("login")) {
			String inventoryPassword = request.getParameter("inventoryPassword");
			
			if(inventoryPassword.equals("1234")) {
				url = "/inventory.jsp";
			}
			else {
				url = "/inventory_login_fail.jsp";
			}
		}
		else if (action.equals("addNewBook")) {
			
			System.out.println("Why???");
			
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String genre = request.getParameter("genre");
			String description = request.getParameter("description");
			String publishedDate = request.getParameter("publishedDate");
			
			System.out.println("Title = " + title);
			System.out.println("Author = " + author);
			System.out.println("Genre = " + genre);
			System.out.println("Description = " + description);
			System.out.println("Publish Date = " + publishedDate);
					
					
					
////			Double rating = Double.parseDouble(request.getParameter("rating"));
//			
//			System.out.println("genre = " + request.getParameter("genre"));
//			System.out.println("rating = " + request.getParameter("rating"));
//			
//			Book book = new Book(title, description, author, 
//					genre, publishedDate, 4.5,
//					"", true);
//			
//		
//			String sqlResult = "";
//			String selectAllBooksSQL= "SELECT * FROM Book;";
////			String sqlStatement="SELECT 'title', 'author', 'genre', "
////					+ "'publishedDate', 'rating' "
////					+ "FROM Books";
//			
//			 try {
//				 
//		        	// get a connection
//		           
//				    Class.forName("com.mysql.jdbc.Driver"); 
//		            dbURL = "jdbc:mysql://remotemysql.com:3306/g1ib8PipYv";
//		            dbUserName = "g1ib8PipYv";
//		            dbPassword = "f76Xu64PP5";
//		            connection = DriverManager.getConnection(
//		                    dbURL, dbUserName, dbPassword);
//
//		            // create a statement
//		            Statement statement = connection.createStatement();
//
//		            // parse the SQL string
//		            
//		            ResultSet resultSet
//		                            = statement.executeQuery(selectAllBooksSQL);
//		            
//		            sqlResult = SQLUtil.getHtmlUserTable(resultSet);
//		            
//		            
//		            resultSet.close();
//		            statement.close();
//		            connection.close();
//
//		        } catch (ClassNotFoundException e) {
//		            sqlResult = "<p>Error loading the databse driver: <br>"
//		                    + e.getMessage() + "</p>";
//		        } catch (SQLException e) {
//		            sqlResult = "<p>Error executing the SQL statement: <br>"
//		                    + e.getMessage() + "</p>";
//		        }
//			

		}
		
		
		
		
		
				
		
		HttpSession session = request.getSession();
		session.setAttribute("sqlResult", sqlResult);

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
		
		
		
		
	}

}
