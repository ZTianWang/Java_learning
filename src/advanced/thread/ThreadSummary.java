package advanced.thread;

public class ThreadSummary {
	
	public static void main(String[] args) throws InterruptedException{
		
//1.	��ȡ��ǰ�̣߳�����name, id
		System.out.println("Main thread Name: " + Thread.currentThread().getName() + ", Id: " +Thread.currentThread().getId());
		
//3		ͨ��Runnable�����߳�: optional param: thread name
		Thread runnerThread = new Thread(new Runner(), "RunnerThread");
//5		�����߳�
		runnerThread.start();
		
//4		ͨ��Thread�����̣߳�
		Thread myThread = new MyThread("myThread");
		myThread.start();
		
//6		ͨ�������ڲ����Lambda���ʽ�����߳�:
		Thread lambdaThread = new Thread(()->{
			System.out.println(Thread.currentThread().getName() +  " �߳�ִ��");
//9		�߳��ò�����ǰ�߳��ó�CPUʹ��Ȩ����ֻ�����ø����ȼ���ͬ����ߵ��߳�		
			Thread.yield();
			System.out.println(Thread.currentThread().getName() +  " �߳̽���");
		}, "Lambda thread");
		lambdaThread.start();
		
//7		�������ȼ��� Ĭ��NORM_PRIORITY (5)
		myThread.setPriority(Thread.MAX_PRIORITY);
		
//8		�ȴ��߳̽�����
//			��ʱ���̱߳��������ȴ�myThread����
//			����һ���߳���������һ���̵߳����н��
		myThread.join();
		
//11.	�߳�ͬ��(synchronized, lock)��TecketThread
//12.	�̰߳�ȫ���߳�ͨ�ţ�ThreadSecurityNComm	
		System.out.println("���߳̽���");
	}
}

//�����߳�1��ʵ��Runnable
class Runner implements Runnable{
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " �߳�ִ��");
		try {
			long sleepTime = (long)(1000*Math.random());
//			2. �߳����� : sleep(long millis)
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() +  " �߳̽���");
	}
}

//�����߳�2�� �̳�Thread��
class MyThread extends Thread{
	boolean stop = true;
	public MyThread() {
		super();
	}
	public MyThread(String name) {
		super(name);
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() +  " �߳�ִ��");
		try {
			Thread.sleep((long)(1000*Math.random()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//10	�߳�ֹͣ���Ƽ�ͨ�����ý������������߳�ֹͣ
		while (!stop) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("������ѭ���߳���");
			
		}
		System.out.println(Thread.currentThread().getName() +  " �߳̽���");
	}
}


















