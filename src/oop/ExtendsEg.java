package oop;

public class ExtendsEg {
	public static void main(String[] args) {
		Father fs = new Son();
		fs.method1();
		Son son = new Son();
//		son.method1();
		son.method1(2);
		Son.method2();
		Son.method2(1);
	}

}



