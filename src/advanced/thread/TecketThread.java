package advanced.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ���ڱ�ʾ�ٽ���Դ����Ͷ��߳�ͬ��
public class TecketThread implements Runnable {

	private int sum = 800;
	
//	��������
	Lock lock = new ReentrantLock();

	@Override
	public void run() {

		for (int i = 1; i < 1000; i++) {
			
//			todo();
			
//			����2��ʹ��synchronized��䣺 ����Ϊ��Ҫͬ���Ķ��󣨼�MessageQueue�ࣩ
//			synchronized (this) {
			
//			����3���Դ�������
			lock.lock();
			try {
				if (sum > 0) {
					System.out.println("�ڴ���:" + Thread.currentThread().getName() + " ���˵�" + sum + "��Ʊ��");
					sum--;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
//				��finally���ͷ���
				lock.unlock();
			}
			
//			}
		}
	}
	
//  ����1���Է�������
	private synchronized void todo() {
		if (sum > 0) {
			System.out.println("�ڴ���:" + Thread.currentThread().getName() + " ���˵�" + sum + "��Ʊ��");
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
//    	��ʱ�����Գ���ͬһ��message������̼߳�������ʱ��Щ�̵߳���ͬһ��MessageQueue����
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
