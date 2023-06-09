package jdbcTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import jdbcUtil.JdbcUtil;

public class BatchTest {

	// Åú´¦Àí
	@Test
	public void updataNum() {

		Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select num from students";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Integer> nums = new ArrayList<>();
			while (rs.next()) {
				nums.add(rs.getInt("num"));
			}

			sql = "update students set num = ? where num = ?";

			int fNum = 2101;
			long begin = System.currentTimeMillis();
			JdbcUtil.close(null, ps, null);
			ps = conn.prepareStatement(sql);
			for (Integer num : nums) {
				ps.setInt(1, fNum);
				ps.setInt(2, num);
//				JdbcUtil.PrintSql(ps);
//				ps.executeUpdate();
				ps.addBatch();
				fNum++;
			}
			ps.executeBatch();
			ps.clearBatch();
			ps.clearParameters();
			long time = System.currentTimeMillis() - begin;
			System.out.println(time);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
	}

}
