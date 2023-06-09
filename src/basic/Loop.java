package basic;

public class Loop {

	public Loop(int i) {
	}
	
	public static void main(String[] args) {
		
//		for (int i = 1; i <= 9; i++) {
//			for (int j = 1; j <= i; j++) {
//				System.out.printf("%d * %d = %d  ",j,i,j*i);
//			}
//			System.out.println();
//		}
		
		LABLE:
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				System.out.printf("%d * %d = %d\t",j,i,j*i);
				if (j == i) {
//					break;
					System.out.println();
					continue LABLE;
				}
			}
//			System.out.println();
		}
	
//		±éÀúÑ­»·£º
		int[] array = {1,2,3,4,5};
		for (int i : array) {
			System.out.println(array[i-1]);
		}
	}
}
