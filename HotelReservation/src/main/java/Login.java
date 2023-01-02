import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException,IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Hotel h1=new Hotel();
		String title="Login Page";
		PrintWriter out = response.getWriter();
		String docType =
			      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
			      "Transitional//EN\">\n";
		if(isNotNull(username) && isNotNull(password)) {
			try {
				String errMessage=h1.validateCredentials(username, password);
				if(errMessage==null) {
					RequestDispatcher rd=request.getRequestDispatcher("homePage.html");
					rd.forward(request, response);
				}else {
					if(errMessage.equals("Invalid UserID")) {
						RequestDispatcher rd=request.getRequestDispatcher("invalidUser.html");
						rd.forward(request, response);
					}else {
						RequestDispatcher rd=request.getRequestDispatcher("invalidPassword.html");
						rd.forward(request, response);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			RequestDispatcher rd=request.getRequestDispatcher("missingLogin-form.html");
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