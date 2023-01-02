import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;



@WebServlet("/customerDetails")
public class CustomerDetails extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException,IOException{
		String firstName=request.getParameter("firstname");
		String lastName=request.getParameter("lastname");
		String phoneNumber=request.getParameter("phoneNumber");
		String roomType=request.getParameter("roomType");
		PrintWriter out = response.getWriter();
		String docType =
			      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
			      "Transitional//EN\">\n";
		int roomNo;
		int bookingId;
		
		
		if(isNotNull(firstName) && isNotNull(lastName)
				&& isNotNull(phoneNumber) && isNotNull(roomType)) {
			Room r1=new Room();
			roomNo=r1.getRoom(roomType);
			Customer c1=new Customer(firstName,lastName,phoneNumber,roomType,roomNo);
			try {
				bookingId=c1.addData();
				r1.updateRoom(roomNo);
				if(bookingId!=0) {
					out.println(docType +
			                "<HTML>\n" +
			                "<HEAD><center><TITLE>Confirmation</TITLE></center>"
			                + "<form action=\"homePage.html\"></HEAD>\n" +
			                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
			                "<H1>Reservation Confirmation</H1>\n" +
			                "<center><TABLE BORDER=1>\n" +
			                "<TR BGCOLOR=\"#EF8429\">"+
			                "<TR>"
			                + "<TD>BooKing ID</TD>"
			                + "<TD>"+bookingId+"</TD></TR>"+
			                "<TR>"
			                + "<TD>First Name</TD>"
			                + "<TD>"+firstName+"<TR>"
			                + "<TD>Last Name"
			                + "<TD>"+lastName+
			                "<TR>"
			                + "<TD>Contact Number"
			                + "<TD>"+phoneNumber+
			                "<TR>"
			                + "<TD>Room Type"
			                + "<TD>"+roomType+
			                "<TR>"
			                + "<TD>Room Number"
			                + "<TD>"+roomNo+
			                "<TR>"
			                +"<TD>Status"
			                +"<TD>ACTIVE"+
			                "</TABLE>"
			                + "<input type=\"submit\" value=\"Home\"></center></BODY></HTML>");
				}else {
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}else {
			RequestDispatcher rd=request.getRequestDispatcher("missingCustomerDetails-form.html");
			rd.forward(request, response);
		}
		
	}
	
	public boolean isNotNull(String value) {
		if(value==null || value.trim().equals("")) {
			return false;
		}else {
			return true;
		}
	}
	

}
