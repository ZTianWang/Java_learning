package oop;

public class Son1 extends Father{
	
	@SuppressWarnings("unused")
	private String NAME = "son1";
	public int i = 0;
	public String str = "";
	
	public Son1() {
		System.out.println("son1 constructed");
	}
	
	public Son1(int i) {
		System.out.println("son constructed by int: " + i);
	}
	
	@Override
	public void method1() {
		System.out.println("son1 method1");
	}
	
	public void setIntAndStr(int i, String str) {
		this.i = i;
		this.str = str;
		System.out.printf("son1 set: int = %d, str = %s\n", this.i, this.str);
	}
}
