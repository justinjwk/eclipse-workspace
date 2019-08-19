package edu.jhu.web.mod11.business;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import edu.jhu.web.mod11.util.MailUtilGmail;

@WebServlet("/Module11Servlet")
public class Module11Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Module11Servlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
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
		else if (action.equals("checkout")) {
			
			url="/payment.jsp";
			
			sc.getRequestDispatcher(url).forward(request, response);
		}
		else if (action.equals("printPDF")) {
			
			Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
		            Font.BOLD);
		    Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
		            Font.NORMAL, BaseColor.RED);
		    Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
		            Font.BOLD);
		    Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
		            Font.BOLD);
			
			
			try {
				
				System.out.println("Here Print PDF Method!!!!!!! ------- BEGING");
				
				ServletOutputStream os = response.getOutputStream();
				response.setContentType("application/pdf");
				
				Document document = new Document();
				
//				PdfWriter.getInstance(document, new FileOutputStream("registration.pdf"));
				PdfWriter.getInstance(document, os);
				
				document.open();
				
				// Meta data
		        document.addTitle("Registration");
		        document.addSubject("Using iText");
		        document.addKeywords("Java, PDF, iText");
		        document.addAuthor("Justin Kim");
		        document.addCreator("Justin Kim");
				
				// Document Contents
				Paragraph preface = new Paragraph();
				
				addEmptyLine(preface, 1);
			
				preface.add(new Paragraph("Johns Hopkins Annual "
						+ "Software Development Seminar", catFont));
				preface.add(new Paragraph("........................."
						+ "........................................."
						+ ".........................................", catFont));
				
				addEmptyLine(preface, 1);
				
				preface.add(new Paragraph(user.getName(), subFont));
				
				addEmptyLine(preface, 1);
				
				preface.add(new Paragraph("You are registered as " 
						+ user.getStatus(), subFont));
				
				addEmptyLine(preface, 1);
				
				preface.add(new Paragraph("If you do not receive "
						+ "the email confirmation or "
						+ "you need to update your registration information," 
						+ "please contact the conference committee "
						+ "at registration@seminar.jhu.edu or "
						+ "at (410) 410-4100.", subFont));
				
				addEmptyLine(preface, 1);
				
				document.add(preface);
				
				PdfPTable table = new PdfPTable(2);
				
				
				PdfPCell courseHeaderCell = new PdfPCell(new Phrase("Your Course"));
				courseHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				courseHeaderCell.setBorder(0);
				
				table.addCell(courseHeaderCell);
				
				PdfPCell courseFeeCell = new PdfPCell(new Phrase("Cost"));
				courseFeeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				courseFeeCell.setBorder(0);
				table.addCell(courseFeeCell);
				table.setHeaderRows(1);
				
				
				for (String course : user.getCourses()) {
		        	table.addCell(course);
		        	table.addCell("$" + user.getCourseFee());
				}
				
				PdfPCell addionalFeeHeaderCell = new PdfPCell(new Phrase("Additional Fees"));
				addionalFeeHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				addionalFeeHeaderCell.setBorder(0);
				table.addCell(addionalFeeHeaderCell);
				
				PdfPCell addionalFeeCell = new PdfPCell(new Phrase(""));
				addionalFeeCell.setBorder(0);
				table.addCell(addionalFeeCell);
				
				if (user.isHotelChecked()) {
					table.addCell("Hotel Accommodation");
					table.addCell("$" + user.getHotel());
				}
				if (user.isParkingChecked()) {
					table.addCell("Parking");
					table.addCell("$" + user.getParking());
				}

				PdfPCell totalHeaderCell = new PdfPCell(new Phrase("Total: "));
				totalHeaderCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				totalHeaderCell.setBorder(0);
				table.addCell(totalHeaderCell);
				

				PdfPCell totalCell = new PdfPCell(new Phrase("$" + user.getTotal()));
				totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				totalCell.setBorder(0);
				table.addCell(totalCell);
			
				
				document.add(table);
				
				System.out.println("Here Print PDF Method!!!!!!! ------- END");
				
				response.setHeader("content-disposition",
		                "attachment; filename=registration.pdf");
		        response.setHeader("cache-control", "no-cache"); 

		        document.close();



			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
		}
		
		else if (action.equals("printExcel")) {
			// create workbook and sheet for spreadsheet
	        Workbook workbook = new HSSFWorkbook();
	        Sheet sheet = workbook.createSheet(user.getName());
	        
	        Row row = sheet.createRow(0);
	        row.createCell(0).setCellValue(user.getName());
	        row = sheet.createRow(1);
	        row.createCell(0).setCellValue("You are registered as "
	        		+ user.getStatus()  
	        		+ ". An email confirmation has been sent to "
	        		+ user.getEmail());
	        row = sheet.createRow(2);
	        row.createCell(0).setCellValue("If you do not receive "
	        		+ "the email confirmation or "
	        		+ "you need to update your registration information, "
	        		+ "Please contact the conference committee "
	        		+ "at registration@seminar.jhu.edu "
	        		+ "or at (410) 410-4100.");
	        row = sheet.createRow(4);
	        row.createCell(0).setCellValue("Your Courses");
	        row.createCell(1).setCellValue("Cost");
	        
	        
	        int rowNum = 5;
	        for (String course : user.getCourses()) {
	        	row = sheet.createRow(rowNum);
	        	row.createCell(0).setCellValue(course);
	        	row.createCell(1).setCellValue("$" + user.getCourseFee());
	        	rowNum++;
			}
	        
	        rowNum++;
	        	        
	        row = sheet.createRow(rowNum);
	        row.createCell(0).setCellValue("Additional Fees");
	        
	        rowNum++;
	        
	        if (user.isHotelChecked()) {
				row = sheet.createRow(rowNum);
				row.createCell(0).setCellValue("Hotel Accommodation");
				row.createCell(1).setCellValue("$" + user.getHotel());
				rowNum++;
			}
			if (user.isParkingChecked()) {
				row = sheet.createRow(rowNum);
				row.createCell(0).setCellValue("Parking");
				row.createCell(1).setCellValue("$" + user.getParking());
				rowNum++;
			}
			
			rowNum++;
			
			row = sheet.createRow(rowNum);
			row.createCell(0).setCellValue("Total");
			row.createCell(1).setCellValue("$" + user.getTotal());
			rowNum++;


	        // set the response headers to return an attached .xls file
	        response.setHeader("content-disposition",
	                "attachment; filename=registration.xls");
	        response.setHeader("cache-control", "no-cache");        

	        // get the output stream and send the workbook to the browser
	        OutputStream out = response.getOutputStream();
	        workbook.write(out);
	        out.close();
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
			
//			user.calculateTotal();
			
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
