package advanced.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 用于表示临界资源问题和多线程同步
public class TecketThread implements Runnable {

	private int sum = 800;
	
//	可重入锁
	Lock lock = new ReentrantLock();

	@Override
	public void run() {

		for (int i = 1; i < 1000; i++) {
			
//			todo();
			
//			方法2：使用synchronized语句： 参数为需要同步的对象（见MessageQueue类）
//			synchronized (this) {
			
//			方法3：对代码块加锁
			lock.lock();
			try {
				if (sum > 0) {
					System.out.println("在窗口:" + Thread.currentThread().getName() + " 买到了第" + sum + "张票。");
					sum--;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
//				在finally中释放锁
				lock.unlock();
			}
			
//			}
		}
	}
	
//  方法1：对方法加锁
	private synchronized void todo() {
		if (sum > 0) {
			System.out.println("在窗口:" + Thread.currentThread().getName() + " 买到了第" + sum + "张票。");
			sum--;
		}
	}
	
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

class MessageQueue {
    private List<String> messages = new ArrayList<>();

    public void put(String message) {
//    	此时代码块对持有同一个message对象的线程加锁（此时这些线程调用同一个MessageQueue对象）
        synchronized(messages) {
            messages.add(message);
        }
    }

    public String take() {
        synchronized(messages) {
            if (messages.isEmpty()) {
                return null;
            }
            return messages.remove(0);
        }
    }
}
