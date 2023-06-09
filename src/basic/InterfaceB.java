package basic;

//@FunctionalInterface
public interface InterfaceB{

	public static final int i = 0;
	public void b(int i, int j);
	
	default Test di() {
		return new Test();
	}
	
	static void m1() {
		System.out.println("a");
	}
}
