package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.StuDao;
import domain.Student;
import jdbcUtil.JdbcUtil;

public class StuDaoImpl implements StuDao {

	@Override
	public int add(Student stu) {

		Connection conn = JdbcUtil.getConnection();
		Statement st = null;
		
		String sql = "insert into students (num,name,age) values ("
				+stu.getNum()+",'"+stu.getName()+"',"+stu.getAge()+");";
		System.out.println(sql);
		
		try {
			st = conn.createStatement();
			System.out.println(st.getClass());
			int row = st.executeUpdate(sql);
			System.out.println("Affected rows: " + row);
			return row;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, st, null);
		}
		
		return 0;
	}
	
	@Override
	public int remove(int id) {

		Connection conn = JdbcUtil.getConnection();
		Statement st = null;
		String sql = "delete from students where id = " + id;
		System.out.println(sql);
		
		try {
			st = conn.createStatement();
			int row = st.executeUpdate(sql);
			System.out.println("Affected rows:" + row);
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, st, null);
		}
		
		return 0;
	}

	@Override
	public int update(int id, Student stu) {
		
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "update students set num=?,name=?,age=? where id=?;";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, stu.getNum());
			ps.setString(2, stu.getName());
			ps.setInt(3, stu.getAge());
			ps.setInt(4, id);
			
			//打印传参后的sql语句
			String pstext = ps.toString();
			System.out.println(pstext.substring(pstext.indexOf(": ")+2));
			
			int row = ps.executeUpdate();
			System.out.println("Affected rows: "+row);
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, null);
		}
		
		return 0;
	}

	@Override
	public Student get(int id) {
		
		Student stu = new Student();
		
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from students where id=?;";
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				stu.setId(rs.getInt(1));
				stu.setNum(rs.getInt(2));
				stu.setName(rs.getString("name"));
				stu.setAge(rs.getInt("age"));
			}
			
			return stu;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
		
		return null;
	}

	@Override
	public List<Student> getAll() {
		
		List<Student> students = new ArrayList<>();
		
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from students;";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getInt(1));
				stu.setNum(rs.getInt(2));
				stu.setName(rs.getString(3));
				stu.setAge(rs.getInt(4));
				
				students.add(stu);
			}
			
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
		
		return null;
	}

}
