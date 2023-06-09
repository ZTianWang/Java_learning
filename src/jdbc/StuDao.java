package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StuDao {
	
	Connection conn = null;
	Statement st = null;

	public StuDao(Connection conn) {
		super();
		this.conn = conn;
	}


	public int add(StuDomain stu) {
		
//		int id = stu.getSid();
		Integer num = stu.getSnum();
		String name = stu.getStuName();
		Integer age = stu.getAge();
		
		String sql = "insert into student (snum,stu_name,age) value (" +  num + ",'" + name + "',"
				+ age + ")";
		System.out.println(sql);
		
		try {
			st = conn.createStatement();
			int row = st.executeUpdate(sql);
			return row;
		} catch (SQLException e) {
			System.out.println("Add data failed!");
			e.printStackTrace();
		}finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return 0;
	}
}
