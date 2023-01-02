
import java.sql.*;

public class DBConnection {
	
	public Connection getConnection() throws SQLException {
		final String driver="com.mysql.cb.jdbc.driver";
    	final String username="root";
    	final String password="";
    //String url = "jdbc:odbc:myDSN";
    	String url="jdbc:mysql://localhost:3306/test?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Connection con = DriverManager.getConnection(url,username, password);
    	return con;
	}

}
