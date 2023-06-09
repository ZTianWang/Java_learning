package advanced;

import java.util.List;


public class GenericEg {
	public static void main(String[] args) {
	}
}

class Generic {
	
	public void showKeyValue(Generic obj){
	   System.out.println("∑∫–Õ≤‚ ‘ string is " + obj.toString());
	}
	
	public void test1(List<? extends Number> list) {
	}
	
	public List<?> test2(List<? extends Number> list, int a) {
		list.remove(a);
		return list;
	}
	
	public Number test3() {
		int intNumber = 10;
		return intNumber;
	}
	
}
