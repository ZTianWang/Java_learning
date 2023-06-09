package advanced.thread;


public class ThreadEg {

	public static void main(String[] args) {
		
		Thread thread1 = new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				long sleepTime = (long)(Math.random() * 1000);
				try {
//					Thread.sleep(sleepTime);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.printf("第%d次执行： %s\n",i,Thread.currentThread().getName());
			}
			System.out.println(Thread.currentThread().getName()+"执行完毕");
		}, "thread1");
		thread1.start();
		
		Thread thread2 = new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				long sleepTime = (long)(Math.random() * 1000);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.printf("第%d次执行： %s\n",i,Thread.currentThread().getName());
			}
			System.out.println(Thread.currentThread().getName()+"执行完毕");
		}, "thread2");
		thread2.start();
		
		Thread thread3 = new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				long sleepTime = (long)(Math.random() * 1000);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.printf("第%d次执行： %s\n",i,Thread.currentThread().getName());
			}
			System.out.println(Thread.currentThread().getName()+"执行完毕");
		}, "thread3");
		thread3.start();
		
	}
	
}
