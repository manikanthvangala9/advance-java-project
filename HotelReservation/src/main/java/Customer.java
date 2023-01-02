import java.sql.Connection;
import java.sql.*;

public class Customer {
	private String firstname;
	private String lastname;
	private String phoneNumber;
	private String roomType;
	private int roomNo;
	
	public Customer(String fname,String lName,String phNo,String roomType,int roomNo) {
		this.firstname=fname;
		this.lastname=lName;
		this.phoneNumber=phNo;
		this.roomType=roomType;
		this.roomNo=roomNo;
	}
	
	public int addData() throws SQLException {
		DBConnection d1=new DBConnection();
		Connection dbcon=d1.getConnection();
		Statement s1=dbcon.createStatement();
		int bookingId=0;
		String query="INSERT INTO bookings (firstname,lastname,phoneNumber,roomId) values "
				+ "('"+firstname+"','"+lastname+"','"+phoneNumber+"',"+roomNo+")";
		String query1="Select MAX(bookingId) from bookings";
		s1.executeUpdate(query);
		
		ResultSet rs=s1.executeQuery(query1);
		if(rs.next()) {
			bookingId=rs.getInt(1);
		}
		dbcon.close();
		return bookingId;
		
	}
	


	public void setFirstName(String fName) {
		this.firstname=fName;
	}
	
	public void setLastName(String lName) {
		this.lastname=lName;
	}
	
	public void setPhoneNumber(String phNumber) {
		this.phoneNumber=phNumber;
	}
	
	public void setRoomtype(String r) {
		this.roomType=r;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastName() {
		return lastname;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getRoomType() {
		return roomType;
	}
	
	public void setRoomNo(int roomNo) {
		this.roomNo=roomNo;
	}
	
	public int getRoomNo() {
		return roomNo;
	}
	
	

}
