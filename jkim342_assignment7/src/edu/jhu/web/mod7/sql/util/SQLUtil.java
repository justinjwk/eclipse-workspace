package edu.jhu.web.mod7.sql.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUtil {

    public static String getHtmlUserTable(ResultSet results)
            throws SQLException {
        
        StringBuilder htmlTable = new StringBuilder();
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();
        
        List<String> userInfo = new ArrayList<>();
        
        while (results.next()) {
            for (int i = 1; i <= columnCount; i++) {
                userInfo.add(results.getString(i));
            }
        }
        
        String userName = userInfo.get(0).toString();
        String userEmail = userInfo.get(1).toString();
        String userStatus = userInfo.get(2).toString();
        
        htmlTable.append("<tr>\n" + 
        		"			<td colspan=\"4\">\n" + 
        		"				<h2><b>" + userName + "</b></h2>\n" + 
        		"			</td>\n" + 
        		"		</tr>\n" + 
        		"		<tr>\n" + 
        		"			<td colspan=\"4\">You are registered as a " + 
        		"				<b>" + userStatus + "</b>\n" + 
        		"			</td>\n" + 
        		"		</tr>\n" + 
        		"		<tr>\n" + 
        		"			<td colspan=\"4\">Your e-mail confirmation will be sent to: " + 
        		"				<b>" + userEmail + "</b>\n" + 
        		"			</td>\n" + 
        		"		</tr>");
        
        return htmlTable.toString();
    }
    
    public static String getHtmlCoursesTable(ResultSet results)
            throws SQLException {
        
        StringBuilder htmlTable = new StringBuilder();
        
        htmlTable.append("<tr>\n" + 
        		"			<th class=\"bottomBorder\" colspan=\"2\">Your Courses</th>\n\n" + 
        		"			<th class=\"bottomBorder\">Cost</th>\n" + 
        		"			<th class=\"bottomBorder\"></th>\n" + 
        		"	  	 </tr>");
        
     // add all other rows
        while (results.next()) {
            htmlTable.append("<tr>\n" + 
            		"			<td class=\"bottomBorder\">" + results.getString(1) + "</td>\n" + 
            		"			<td class=\"bottomBorder\"></td>\n" + 
            		"			<td class=\"cost\">$" + results.getString(2) + "</td>\n" + 
            		"			<td class=\"bottomBorder\"></td>\n" + 
            		"	 	  </tr>");
        }
        
        htmlTable.append("<tr>\n" + 
        		"			<td colspan=\"4\"><br></td>\n" + 
        		"		  </tr>");
        
        return htmlTable.toString();
    }
    
    
    public static String getHtmlFeesTable(ResultSet results)
            throws SQLException {
        
        StringBuilder htmlTable = new StringBuilder();
        results.next();

        htmlTable.append("<tr>\n" + 
        		"			<td class=\"bottomBorder\">Hotel Accommodation</td>\n" +
        		"			<td class=\"bottomBorder\"></td>\n" +
        		"			<td class=\"cost\">$" + results.getString(1) + "</td>\n" + 
        		"			<td class=\"bottomBorder\"></td>\n" +
				"		  </tr>");
        
        htmlTable.append("<tr>\n" + 
        		"			<td class=\"bottomBorder\">Parking</td>\n" +
        		"			<td class=\"bottomBorder\"></td>\n" +
        		"			<td class=\"cost\">$" + results.getString(2) + "</td>\n" + 
        		"			<td class=\"bottomBorder\"></td>\n" +
				"		  </tr>");
        
        htmlTable.append("<tr>\n" + 
        		"			<td class=\"bottomBorder\"></td>\n" + 
        		"			<td class=\"cost\"><b>Total</b></td>\n" + 
        		"			<td class=\"cost\">$" + results.getString(3) + "</td>\n" + 
        		"			<td class=\"bottomBorder\"></td>\n" + 
        		"		  </tr>");
     
        
        return htmlTable.toString();
    }
    
    
    
    
    
    
    
    
    
}