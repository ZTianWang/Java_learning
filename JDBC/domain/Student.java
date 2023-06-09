package domain;

public class Student {
	
	private Integer id;
	private Integer num;
	private String name;
	private Integer age;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer sid) {
		this.id = sid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer snum) {
		this.num = snum;
	}
	public String getName() {
		return name;
	}
	public void setName(String stuName) {
		this.name = stuName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", num=" + num + ", name=" + name + ", age=" + age + "]";
	}

}
