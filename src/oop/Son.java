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
	
	//����������ظ���ľ�̬��������������д
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
