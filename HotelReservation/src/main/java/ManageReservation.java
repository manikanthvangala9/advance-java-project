import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/manageReservation")
public class ManageReservation extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException,IOException{
		String bookingId = request.getParameter("bookingId");
		PrintWriter out = response.getWriter();
		DatabaseOperations data1=new DatabaseOperations();
		String docType =
			      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
			      "Transitional//EN\">\n";
		if(isNotNull(bookingId)) {
			try {
				ArrayList booking=new ArrayList();
				int bId=Integer.parseInt(bookingId);
				booking=data1.displayBooking(bId);
				if(booking.size()==0) {
					RequestDispatcher rd=request.getRequestDispatcher("invalidBookingId-form.html");
					rd.forward(request, response);
				}else {
					out.println(docType +
			                "<HTML>\n" +
			                "<HEAD><center><TITLE>Confirmation</TITLE></center>"
			                + "<form action=\"cancelReservation\"></HEAD>\n" +
			                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
			                "<H1>Reservation Details</H1>\n" +
			                "<center>Booking Id: <input TYPE=\"text\" NAME=\"bookingId\" VALUE="+bookingId+"></center> <br>"+
			                "<center><TABLE BORDER=1>\n" +
			                "<TR BGCOLOR=\"#EF8429\">"+
			                "<TR>"
			                + "<TD>BooKing ID</TD>"
			                + "<TD>"+bookingId+"</TD></TR>"+
			                "<TR>"
			                + "<TD>First Name</TD>"
			                + "<TD>"+booking.get(1)+"<TR>"
			                + "<TD>Last Name"
			                + "<TD>"+booking.get(2)+
			                "<TR>"
			                + "<TD>Contact Number"
			                + "<TD>"+booking.get(3)+
			                "<TR>"
			                + "<TD>Room Number"
			                + "<TD>"+booking.get(4)+
			                "<TR>"
			                + "<TD>Room Status"
			                + "<TD>"+booking.get(5)+
			                "</TABLE>"
			                + "<input type=\"submit\" value=\"Cancel Reservation\"></center></BODY></HTML>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			RequestDispatcher rd=request.getRequestDispatcher("missingBookingId-form.html");
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