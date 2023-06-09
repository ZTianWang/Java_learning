package advanced;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBEg {

	private static String url;
	private static Properties info = new Properties();

	static {
		InputStream input = DBEg.class.getClassLoader().getResourceAsStream("./config.properties");
		try {
			info.load(input);
			System.out.println("Loading configuration file successfully.");
			url = info.getProperty("url");
		} catch (IOException e) {
			System.out.println("Loading configuration file failed!");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		try {

			loadDriver();

//			connByURL();
//			connByProp();
//			connByPropFile();

//			queryWithParam();
//			add(3, "test3");
//			for (int i = 3; i < 6; i++)  add(i, "test"+i);
//			delete("Name","test3");
//			update("Cindy", 2);
			queryWithoutParam();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void loadDriver() throws ClassNotFoundException {

		try {
			String driverClassName = info.getProperty("driver");
			@SuppressWarnings("unused")
			Class<?> dbLClz = Class.forName(driverClassName);
			System.out.println("Loading driver successfully.");
		} catch (ClassNotFoundException e) {
			System.out.println("Loading driver failed!");
			throw e;
		}
	}

	static void connByURL() throws SQLException {

//		String url1 = "jdbc:mysql://localhost/testDB";
		String url1 = url + "?user=root&password=wnwn1994";
//				+ "&verifyServerCertificate=false&useSSL=false";
		try (Connection conn = DriverManager.getConnection(url1, info);) {
			System.out.println("Connecting successfully.");
		} catch (SQLException e) {
			System.out.println("Connecting failed!");
			throw e;
		}
	}

	static void connByProp() throws SQLException {

//		String url = "jdbc:mysql://localhost/testDB";

		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "wnwn1994");

		try (Connection conn = DriverManager.getConnection(url, info);) {
			System.out.println("Connecting successfully.");
		} catch (SQLException e) {
			System.out.println("Connecting failed!");
			throw e;
		}
	}

	static Connection connByPropFile() throws SQLException {

//		Properties info = new Properties();

//		try (InputStream in = DBEg.class.getClassLoader().getResourceAsStream("config.properties");) {
//			info.load(in);
//		} catch (IOException e) {
//			System.out.println("Property file load failed");
//			throw e;
//		}

//		String url = "jdbc:mysql://localhost/testDB";

		try {
			Connection conn = DriverManager.getConnection(url, info);
			System.out.println("Connected.");
			return conn;
		} catch (SQLException e) {
			System.out.println("Connecting failed!");
			throw e;
//			e.printStackTrace();
//			return null;
		}
	}

	static void queryWithoutParam() throws SQLException {

//		String sql = "select * from persons where Id=(select max(Id) from persons)";
		String sql = "select * from persons";

		String name = "";

		try ( // 建立连接
				Connection conn = connByPropFile();
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery();) {
//			if (resultSet.next()) {
			while (resultSet.next()) {
//				name = resultSet.getString(2);
				name = resultSet.getString("Name");
//				System.out.println(resultSet);
				System.out.println(resultSet.getInt(1) + ": " + name);
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	static ResultSet queryWithParam() throws SQLException {

		String sql = "select * from persons where Id >= ? order by Id";

		try (Connection conn = connByPropFile();
				PreparedStatement statement = conn.prepareStatement(sql);) {

			statement.setInt(1, 2);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
//				String name = resultSet.getString("Name");
				System.out.printf("Id: %d	Name: %s\n", id, name);
			}
			return resultSet;
		} catch (SQLException e) {
			throw e;
		}
	}

	static int add(int id, String name) throws SQLException {
		String sql = "insert into persons values (?,?)";

		try (Connection conn = connByPropFile(); 
				PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setInt(1, id);
			statement.setString(2, name);

			int count = statement.executeUpdate();
			System.out.printf("加入了%d条数据\n", count);
			return count;
		} catch (SQLException e) {
			throw e;
		}
	}

	static <T> int delete(String field, T value) throws SQLException {
		String sql = "delete from persons where " + field + "=?";

		try (Connection conn = connByPropFile(); 
				PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setObject(1, value);

			int count = statement.executeUpdate();
			System.out.printf("删除了%d条数据\n", count);
			return count;
		} catch (SQLException e) {
			throw e;
		}
	}

	static int update(String value, int id) throws SQLException {
		String sql = "update persons set Name=? where Id=?";

		try (Connection conn = connByPropFile(); 
				PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setString(1, value);
			statement.setInt(2, id);

			int count = statement.executeUpdate();
			System.out.printf("更新了%d条数据\n", count);
			return count;
		} catch (SQLException e) {
			throw e;
		}
	}
}
