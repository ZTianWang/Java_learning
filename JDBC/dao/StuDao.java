package dao;

import java.util.List;

import domain.Student;

public interface StuDao {

	public int add(Student stu);
	
	public int remove(int id);
	
	public int update(int id, Student stu);
	
	public Student get(int id);
	
	public List<Student> getAll();
	
}
