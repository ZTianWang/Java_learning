package oop;

public class Son extends Father{
	
	@SuppressWarnings("unused")
	private String NAME = "son";
	public int i = 0;
	public String str = "";

	public Son() {
		System.out.println("son constructed");
	}
	
	public Son(int i) {
		System.out.println("son constructed by int: " + i);
	}
	
	@Override
	public void method1() {
		System.out.println("son method1");
	}
	
	//子类可以隐藏父类的静态方法，不可以重写
//	@Override
	protected static void method2() {
		System.out.println("son method2");
	}
	
	public void setIntAndStr(int i, String str) {
		this.i = i;
		this.str = str;
		System.out.printf("son set: int = %d, str = %s\n", this.i, this.str);
	}
}
