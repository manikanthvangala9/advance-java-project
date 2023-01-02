import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;



@WebServlet("/AllReservations")
public class AllReservations extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException,IOException{
		DBConnection d1= new DBConnection();
		PrintWriter out = response.getWriter();
		try {
			Connection con=d1.getConnection();
			Statement s1=con.createStatement();
			ResultSet rs=s1.executeQuery("Select * from bookings");
			String docType =
				      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
				      "Transitional//EN\">\n";
			out.println(docType +
	                "<HTML>\n" +
	                "<HEAD><center><TITLE>Confirmation</TITLE></center>"
	                + "<form action=\"homePage.html\"></HEAD>\n" +
	                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
	                "<H1>Reservation Details</H1>\n"+
	                "<center><TABLE BORDER=1>\n" +
	                "<TR BGCOLOR=\"#EF8429\">"
	                + "<TR>"
	                + "<TD>Booking Id"
	                + "<TD>Firstname"
	                + "<TD>Lastname"
	                + "<TD>Phone Number"
	                + "<TD>Room Number"
	                + "<TD>Status");
			
			while(rs.next()) {
				out.println("<TR>"
						+ "<TD>"+rs.getInt(1)
						+"<TD>"+rs.getString(2)
						+"<TD>"+rs.getString(3)
						+"<TD>"+rs.getString(4)
						+"<TD>"+rs.getInt(5)
						+"<TD>"+rs.getString(6));
			}
			out.println("</table><input type=\"submit\" value=\"Home Page\"></center><center>");  
            out.println("</html></body>");  
            con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
