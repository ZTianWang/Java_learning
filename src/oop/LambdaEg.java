package oop;

import basic.InterfaceB;

public class LambdaEg {

	public void test(InterfaceB interfaceB, int i, int j) {
		System.out.println("test");
		interfaceB.b(i,j);
	}
	
	public InterfaceB getB() {
		//作为返回值的Lambda表达式
		InterfaceB result = (a, b) -> System.out.println("getB "+(a+b));
		return result;
	}
	
	public static void main(String[] args) {
		LambdaEg lambdaEg = new LambdaEg();
		//作为参数的Lambda表达式
		lambdaEg.test((i, j) -> {
			System.out.println("i+j: "+i+j);
			System.out.println("i*j: "+i*j);
		},3,5);
		
		//作为返回值的
		InterfaceB interfaceB = lambdaEg.getB();
		interfaceB.b(1, 2);
		interfaceB = (a,b) -> System.out.println("renewal obj "+a*b);
		interfaceB.b(3, 4);
	}
}
