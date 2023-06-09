package jdbcUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {

//	private static String url = "jdbc:mysql://localhost:3306/testdb";
//	private static String user = "root";
//	private static String password = "wnwn1994";
	
	private static Properties info = new Properties();
	private static String url;
	private static String user;
	private static String password;
	
	static {
		InputStream input = JdbcUtil.class.getClassLoader()
				.getResourceAsStream("./config.properties");
		try {
			info.load(input);
			
			Class.forName(info.getProperty("driver"));
			
			url = info.getProperty("url");
			user = info.getProperty("user");
			password = info.getProperty("password");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection connection, Statement statement, ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
//				System.out.print("ResultSet closed! ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
//				System.out.print("Statement closed! ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
//				System.out.println("Connection closed! ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void PrintSql(PreparedStatement preparedStatement) {
		
		StringBuilder sb = new StringBuilder(preparedStatement.toString());
		System.out.println(sb.substring(sb.indexOf(": ") + 1));
	}
	
}
