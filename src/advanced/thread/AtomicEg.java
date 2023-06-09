package advanced.thread;

import java.util.concurrent.atomic.AtomicInteger;

// 原子操作（无锁线程安全访问）
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
	
//	incrementAndGet：原子++
	public int increase() {
		return ai.incrementAndGet();
	}
	
//	原子--
	public int decrease() {
		return ai.decrementAndGet();
	}
	
//	原子加法
	public int add(int i) {
		return ai.addAndGet(i);
	}
	
	public int get() {
		return ai.get();
	}
	
	
}
