package oop;

public class Father {
	
	public Father() {
//		System.out.println("father constructed");
	}
	
	public void method1() {
		System.out.println("father method1");
	}
	
	public int method1(int i) {
		System.out.println("father overloaded method1");
		return i;
	}
	
	static void method2() {
		System.out.println("father method2");
	}
	
	protected static void method2(int i) {
		System.out.println("father reloaded method2");
	}
}

