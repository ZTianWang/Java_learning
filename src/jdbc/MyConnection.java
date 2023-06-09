package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	
	static Connection conn = null;

	public static Connection getConn() {
		
		String url = "jdbc:mysql://localhost:3306/testdb";
		String user = "root";
		String password = "wnwn1994";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Build connection failed!");
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	
	public static void close() {
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Close connection failed!");
				e.printStackTrace();
			}
		}
		
	}
	
}
