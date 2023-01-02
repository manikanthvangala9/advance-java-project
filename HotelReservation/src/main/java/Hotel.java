import java.sql.*;

public class Hotel {
	
	public Hotel() {
		
	}
	
	public String validateCredentials(String value1, String value2) throws SQLException {
		String errMessage=null;
		DBConnection d1=new DBConnection();
		Connection dbcon=d1.getConnection();
		String query="select pass from credentilas where userId='"+value1+"'";
		Statement s1=dbcon.createStatement();
		ResultSet rs=s1.executeQuery(query);
		if(rs.next()) {
			if(rs.getString(1).equals(value2)){
				errMessage=null;
			}else {
				errMessage="Invalid Password";
			}
		}else {
			errMessage="Invalid UserID";
		}
		dbcon.close();
		return errMessage;
	
	}
	
	

}
