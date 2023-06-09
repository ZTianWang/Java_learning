package advanced.thread;

import java.util.concurrent.atomic.AtomicInteger;

// ԭ�Ӳ����������̰߳�ȫ���ʣ�
public class AtomicEg{

	private AtomicCounter atomicCounter = new AtomicCounter();
	private Thread[] threads = new Thread[10];
	
	public void execuseThreads() {
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(()->{
				try {
					Thread.sleep((long)(1000*Math.random()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+": increase "+atomicCounter.increase());
			});
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}
	
	public static void main(String[] args){
		AtomicEg atomicThreads = new AtomicEg();
		atomicThreads.execuseThreads();
	}
	
}

class AtomicCounter{
//	AtomicInteger(int initValue)
	private AtomicInteger ai = new AtomicInteger(0);
	
//	incrementAndGet��ԭ��++
	public int increase() {
		return ai.incrementAndGet();
	}
	
//	ԭ��--
	public int decrease() {
		return ai.decrementAndGet();
	}
	
//	ԭ�Ӽӷ�
	public int add(int i) {
		return ai.addAndGet(i);
	}
	
	public int get() {
		return ai.get();
	}
	
	
}
