import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseOperations {
	
	public ArrayList displayBooking(int value) throws SQLException {
		DBConnection d1= new DBConnection();
		Connection con=d1.getConnection();
		ArrayList result=new ArrayList();
		String query="select * from bookings where bookingId='"+value+"'";
		Statement s1=con.createStatement();
		ResultSet rs=s1.executeQuery(query);
		if(rs.next()) {
			String bookingId=Integer.toString(rs.getInt(1));
			String roomId=Integer.toString(rs.getInt(5));
			result.add(bookingId);
			result.add(rs.getString(2));
			result.add(rs.getString(3));
			result.add(rs.getString(4));
			result.add(roomId);
			result.add(rs.getString(6));
		}else {
			
		}
		con.close();
		return result;
	}
	
	public void update(int value) throws SQLException {
		DBConnection d1= new DBConnection();
		Connection con=d1.getConnection();
		Statement s1=con.createStatement();
		String query1="UPDATE bookings SET status='CANCELED' where bookingId="+value+"";
		s1.executeUpdate(query1);
		String query="Select roomId from bookings where bookingId="+value+""; 
		ResultSet rs=s1.executeQuery(query);
		if(rs.next()) {
			int roomId=rs.getInt(1);
			String query2="UPDATE rooms SET availability='Y' where roomId="+roomId+"";
			s1.executeUpdate(query2);
		}
		
		con.close();
	}

}
