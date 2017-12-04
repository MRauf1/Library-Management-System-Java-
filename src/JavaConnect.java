import java.sql.*;

public class JavaConnect {

	public static Connection getConnection() {
		
		try {
			
			//Connect to the database and return the connection
			String url = "jdbc:sqlite:library.db";
			Connection conn = DriverManager.getConnection(url);
			
			return conn;
			
		} catch (Exception e) {
			
			System.out.println(e);
		
		}
		
		return null;
		
	}
	
}
