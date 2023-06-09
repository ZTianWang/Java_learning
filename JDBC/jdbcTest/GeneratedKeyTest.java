package jdbcTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jdbcUtil.JdbcUtil;

// 获得自动增长的主键id
public class GeneratedKeyTest {

	public static void main(String[] args) throws Exception {
		
		Connection conn = JdbcUtil.getConnection();
		String sql = "insert into students (num,name,age) values (?,?,?)";
		
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ps.setInt(1, 2118);
		ps.setString(2, "孔明");
		ps.setInt(3, 28);
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			int id = rs.getInt(1);
			System.out.println(id);
		}
		
		JdbcUtil.close(conn, ps, rs);
	}
}
