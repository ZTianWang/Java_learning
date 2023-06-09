package jdbcTest;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import dao.impl.StuDaoImpl;
import domain.Student;

public class StuTest {

	@Test
	public void add() {

		Student stu = new Student();
		stu.setNum(1104);
		stu.setName("¡ı±∏");
		stu.setAge(33);

		StuDaoImpl dao = new StuDaoImpl();
		dao.add(stu);

	}

	@Test
	public void remove() {

		StuDaoImpl dao = new StuDaoImpl();
		dao.remove(7);
	}

	@Test
	public void update() {

		Student stu = new Student();
		stu.setNum(1105);
		stu.setName("¬¿√…");
		stu.setAge(32);

		StuDaoImpl dao = new StuDaoImpl();
		dao.update(17, stu);
	}

	@Test
	public void get() {

		StuDaoImpl dao = new StuDaoImpl();

		Student stu = dao.get(1);
		System.out.println(stu);
	}

	@Test
	public void getAll() {

		StuDaoImpl dao = new StuDaoImpl();
		List<Student> students = dao.getAll();

		Iterator<Student> it = students.iterator();
		while (it.hasNext()) {
			Student stu = (Student) it.next();
			System.out.println(stu);
		}
	}

}
