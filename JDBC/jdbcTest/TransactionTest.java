package jdbcTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcUtil.JdbcUtil;

public class TransactionTest {

	public static void main(String[] args) {

		transfer("张三", "李四", 1000);
	}

	public static void transfer(String tranOutPerson, String tranInPerson, double money) {

		Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from account where name =?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, tranOutPerson);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new RuntimeException("Can not search first account!");
			}
			double balance = rs.getDouble("money");
			if (balance < money) {
				throw new RuntimeException("Not sufficient funds!");
			}

			ps.setString(1, tranInPerson);
			JdbcUtil.close(null, null, rs);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new RuntimeException("Can not search second account!");
			}

			try {
				// 设置不自动提交
				conn.setAutoCommit(false);
	
				sql = "update account set money = money-? where name =?";
				JdbcUtil.close(null, ps, null);
				ps = conn.prepareStatement(sql);
				ps.setDouble(1, money);
				ps.setString(2, tranOutPerson);
				ps.executeUpdate();
	
//				int a = 1/0;
				
				sql = "update account set money = money+? where name =?";
				JdbcUtil.close(null, ps, null);
				ps = conn.prepareStatement(sql);
				ps.setDouble(1, money);
				ps.setString(2, tranInPerson);
				ps.executeUpdate();
	
				conn.commit();
				System.out.println("Transfer success!");
			} catch (Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
					System.out.println("Rollback!");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
	}
}
