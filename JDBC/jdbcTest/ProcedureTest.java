package jdbcTest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.junit.Test;

import jdbcUtil.JdbcUtil;

public class ProcedureTest {

	@Test
	public void callProcedure() {
		
		Connection conn = JdbcUtil.getConnection();
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall("{ call students_getName(?,?) }");
			cs.setInt(1, 1);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.execute();
			System.out.println(cs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, cs, null);
		}
	}
}
