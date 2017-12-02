import java.sql.*;

public class JavaConnect {

	public static Connection getConnection() {
		
		try {
			
			String url = "jdbc:sqlite:library.db";
			
			System.out.println("Connecting");
			
			Connection conn = DriverManager.getConnection(url);
			
			System.out.println("Connected");
			
			return conn;
			
		} catch (Exception e) {
			
			System.out.println(e);
		
		}
		
		return null;
		
	}
	
}
