package basic;

public class Test{
	
	private static int count = 0;
	
	public static void main(String[] args) {
		Test.inA();
		Child c = new Child();
		c.getFather();
    }
	
	public static void hanio(int n, char a, char b, char c) {
		if (n == 1) {
			count++;
			System.out.println("��" + count + "��:" + a + "����>" + c);
			return;
		}
		hanio(n-1, a, c, b);
		
		count++;
		System.out.println("��" + count + "��:" + a + "����>" + c);
		
		hanio(n-1, b, a, c);
	}
	
	public static InterfaceA inA() {
		InterfaceA result;
		
		result = () -> 1;
//				new InterfaceA() {
//			
//			@Override
//			public int a() {
//				// TODO Auto-generated method stub
//				return 1;
//			}
			
//		};
		System.out.println(result.a());
		return result;
	}
	
	public void t1(GrandSon grandSon) {
		System.out.println(grandSon.getString());
	}
	
	public void t2(Father father ) {
		System.out.println(father.getString());
	}
	
	public void t3(InterfaceB interfaceB, int i, int j) {
		System.out.println("t3");
		interfaceB.b(3,4);
	}
    
    
    
}

abstract class Father{
	
	public Father() {
		// TODO Auto-generated constructor stub
		System.out.println("construct father");
	}
	
	public String getString() {
		System.out.println("father getString");
		return "father string";
	}
	
	public Father getFather() {
		System.out.println("father getFather");
		this.getString();	// Down call
		return this;
	}
	
	public void fa() {
		a();
	}
	
	public void a() {
		System.out.println("father");
	}
	
	public abstract void f();
}

class Child extends Father{
	
	Integer i = new Integer(1);
	
	public Integer getNum() {
		return i;
	}
	
	public void print() {
		System.out.println(i);
	}

	public Child() {
		// TODO Auto-generated constructor stub
		System.out.println("construct child");
	}
		
	@Override
	public String getString() {
		System.out.println("child getString");
		return "child string";
	}

	@Override
	public void f() {
		// TODO Auto-generated method stub
		System.out.println("child f");
	}
	
	public void a() {
		System.out.println("child");
	}
	
}

class Son extends Father{
	Father father;
	
	public Son() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public String getString() {
		return "son string";
	}
	public Father getFather() {
		return super.getFather();
	}

	@Override
	public void f() {
		// TODO Auto-generated method stub
		System.out.println("son f");
	}
}

class GrandSon extends Child{
	public String getString() {
		return "grandson string";
	}
	
	public void a() {
		System.out.println("grandson");
	}
}

	
