package advanced.thread;

import java.util.ArrayList;
import java.util.List;

// 线程安全及线程通信：
// wait()：阻塞当前线程并进入等待队列等待被其他线程唤醒，让出CPU使用权（不要在run()中调用wait()）
// notify() / notifyAll()：唤醒一个或所有在等待队列中的线程，使其进入就绪状态
public class ThreadSecurityNComm {

	public static void main(String[] args) throws InterruptedException {

		Stack stack = new Stack();

		//第一个生产者
		Thread push1 = new Thread(() -> {
			char c;
			for (int i = 0; i < 5; i++) {
				

				c = (char) (Math.random() * 26 + 'A');
				stack.push(c);
				System.out.println("push1:" + c);
				// 每入出栈一次就睡眠一次
				try {
					Thread.sleep((int) (Math.random() * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println("1 pushing over");
		}, "thread push");

		//消费者
		Thread pop = new Thread(() -> {
			char c;
			for (int i = 0; i < 10; i++) {
				c = stack.pop();
				System.out.println("pop: " + c);
				// 每出栈一次就睡眠一次
				try {
					Thread.sleep((int) (Math.random() * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("poping over");
		}, "thread pop");
		
		//第二个生产者
		Thread push2 = new Thread(() -> {
			char c;
			for (int i = 0; i < 5; i++) {

				c = (char) (Math.random() * 26 + 'A');
				stack.push(c);
				System.out.println("push2:" + c);
				// 每入出栈一次就睡眠一次
				try {
					Thread.sleep((int) (Math.random() * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println("2 pushing over");
		}, "thread push1");

		push1.start();
		push2.start();
		pop.start();
		
		push1.join();
		push2.join();
		pop.join();

		Queue queue = new Queue();

		Thread inQueue = new Thread(() -> {
			char c;
			for (int i = 0; i < 10; i++) {
				c = (char) (Math.random() * 26 + 'A');
				// 对访问对象queue的代码块加互斥锁
				synchronized (queue) {
					queue.in(c);
					try {
						Thread.sleep((long) (Math.random() * 1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("enqueuing over");
		}, "thread in");

		Thread outQueue = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				synchronized (queue) {
					queue.out();
					try {
						Thread.sleep((long) (Math.random() * 1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("dequeuing over");
		}, "thread out");

		inQueue.start();
		outQueue.start();
	}
}
	
class Stack {

	private int pointer = 0;
	private char[] data = new char[5];

	// 在类内部对方法加锁
	public synchronized void push(char c) {
//		必须使用while而不是if
		while (pointer == data.length) {
			try {
				System.out.println("push wait");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
//		System.out.println("push notify");
		data[pointer] = c;
		pointer++;
	}

	public synchronized char pop() {
		while (pointer == 0) {
			try {
				System.out.println("pop wait");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
//		System.out.println("pop notify");
		pointer--;
		return data[pointer];
	}
}

class Queue {

	private List<Character> queueList = new ArrayList<>();
	private static int MAX = 5;

//	public synchronized boolean in(char c) {
	public boolean in(char c) {
		while (queueList.size() == MAX) {
			try {
				System.out.println("in wait");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		System.out.println("in :" + c);
		return queueList.add(c);
	}

//	public synchronized char out() {
	public char out() {
		while (queueList.size() == 0) {
			try {
				System.out.println("out wait");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		char data = queueList.get(0);
		System.out.println("out: " + data);
		queueList.remove(0);
		return data;
	}
}
