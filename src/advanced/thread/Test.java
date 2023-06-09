package advanced.thread;

public class Test {

	public static void main(String[] args) {

		TecketThread tt = new TecketThread();
		
		Thread thread1 = new Thread(tt, "1");
		thread1.start();
		
		Thread thread2 = new Thread(tt, "2");
		thread2.start();
		
		Thread thread3 = new Thread(tt, "3");
		thread3.start();
	}
}
