package advanced.thread;

public class ThreadSummary {
	
	public static void main(String[] args) throws InterruptedException{
		
//1.	获取当前线程，及其name, id
		System.out.println("Main thread Name: " + Thread.currentThread().getName() + ", Id: " +Thread.currentThread().getId());
		
//3		通过Runnable创建线程: optional param: thread name
		Thread runnerThread = new Thread(new Runner(), "RunnerThread");
//5		启动线程
		runnerThread.start();
		
//4		通过Thread创建线程：
		Thread myThread = new MyThread("myThread");
		myThread.start();
		
//6		通过匿名内部类或Lambda表达式创建线程:
		Thread lambdaThread = new Thread(()->{
			System.out.println(Thread.currentThread().getName() +  " 线程执行");
//9		线程让步：当前线程让出CPU使用权，但只限于让给优先级相同或更高的线程		
			Thread.yield();
			System.out.println(Thread.currentThread().getName() +  " 线程结束");
		}, "Lambda thread");
		lambdaThread.start();
		
//7		设置优先级： 默认NORM_PRIORITY (5)
		myThread.setPriority(Thread.MAX_PRIORITY);
		
//8		等待线程结束：
//			此时主线程被阻塞，等待myThread结束
//			用于一个线程依赖于另一个线程的运行结果
		myThread.join();
		
//11.	线程同步(synchronized, lock)：TecketThread
//12.	线程安全及线程通信：ThreadSecurityNComm	
		System.out.println("主线程结束");
	}
}

//创建线程1：实现Runnable
class Runner implements Runnable{
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " 线程执行");
		try {
			long sleepTime = (long)(1000*Math.random());
//			2. 线程休眠 : sleep(long millis)
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() +  " 线程结束");
	}
}

//创建线程2： 继承Thread类
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
		System.out.println(Thread.currentThread().getName() +  " 线程执行");
		try {
			Thread.sleep((long)(1000*Math.random()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//10	线程停止：推荐通过设置结束变量控制线程停止
		while (!stop) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("进入死循环线程体");
			
		}
		System.out.println(Thread.currentThread().getName() +  " 线程结束");
	}
}


















