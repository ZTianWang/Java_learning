package jdbcTest;

public class Test {

	public static void main(String[] args) {
		test(6);
	}
	
	public static void test(int a) {
		
		try {
			if (a > 5) {
				throw new RuntimeException("throw");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(11);
		}
		System.out.println("after throwing.");
		
	}
}
