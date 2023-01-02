import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;



@WebServlet("/cancelReservation")
public class CancelReservation extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException,IOException{
		String bookingId = request.getParameter("bookingId");
		PrintWriter out = response.getWriter();
		
		int bId=Integer.parseInt(bookingId);
		String docType =
			      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
			      "Transitional//EN\">\n";
		try {
			DatabaseOperations data1=new DatabaseOperations();
			data1.update(bId);
			out.println(docType +
	                "<HTML>\n" +
	                "<HEAD><center><TITLE>Confirmation</TITLE></center>"
	                + "<form action=\"homePage.html\"></HEAD>\n" +
	                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
	                "<center><H1>Reservation Cancelled</H1>\n" +
	                "<br><br><br>"+
	                "<P> Reservation with booking id "+bId+" has been succesfully Cancelled"+
	                "<input type=\"submit\" value=\"Home Page\"></center></BODY></HTML>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
		

}
