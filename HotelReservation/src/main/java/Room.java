import java.sql.*;

public class Room {
	
	
	public Room() {
		
	}
	
	public int getRoom(String type) {
		int roomId=0;
		DBConnection d1=new DBConnection();
		try {
			Connection dbcon=d1.getConnection();
			String query="select roomId from rooms where roomType='"+type+"' and availability='Y' LIMIT 1";
			Statement s1=dbcon.createStatement();
			ResultSet rs=s1.executeQuery(query);
			if(rs.next()) {
				roomId=rs.getInt(1);
			}else {
				roomId=0;
			}
			dbcon.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return roomId;
	}
	
	public void updateRoom(int value) {
		DBConnection d1=new DBConnection();
		Connection dbcon;
		try {
			dbcon = d1.getConnection();
			String query="UPDATE rooms SET availability='N' where roomId="+value+"";
			Statement s1=dbcon.createStatement();
			s1.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
