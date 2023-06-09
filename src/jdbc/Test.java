package jdbc;

import java.sql.Connection;

public class Test {

	public static void main(String[] args) {
		
		Connection conn = MyConnection.getConn();
		Test.addStu(conn);
		
		MyConnection.close();
		
	}
	
	public static void addStu(Connection conn) {
		
		StuDomain stu = new StuDomain();
		stu.setStuName("Àî°×");
		stu.setSnum(1025);
		stu.setAge(25);
		
		StuDao dao = new StuDao(conn);
		int row = dao.add(stu);
		System.out.println("Add " + row + "data.");
	}
}
