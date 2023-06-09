package advanced.thread;

import java.util.ArrayList;
import java.util.List;

// �̰߳�ȫ���߳�ͨ�ţ�
// wait()��������ǰ�̲߳�����ȴ����еȴ��������̻߳��ѣ��ó�CPUʹ��Ȩ����Ҫ��run()�е���wait()��
// notify() / notifyAll()������һ���������ڵȴ������е��̣߳�ʹ��������״̬
public class ThreadSecurityNComm {

	public static void main(String[] args) throws InterruptedException {

		Stack stack = new Stack();

		//��һ��������
		Thread push1 = new Thread(() -> {
			char c;
			for (int i = 0; i < 5; i++) {
				

				c = (char) (Math.random() * 26 + 'A');
				stack.push(c);
				System.out.println("push1:" + c);
				// ÿ���ջһ�ξ�˯��һ��
				try {
					Thread.sleep((int) (Math.random() * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println("1 pushing over");
		}, "thread push");

		//������
		Thread pop = new Thread(() -> {
			char c;
			for (int i = 0; i < 10; i++) {
				c = stack.pop();
				System.out.println("pop: " + c);
				// ÿ��ջһ�ξ�˯��һ��
				try {
					Thread.sleep((int) (Math.random() * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("poping over");
		}, "thread pop");
		
		//�ڶ���������
		Thread push2 = new Thread(() -> {
			char c;
			for (int i = 0; i < 5; i++) {

				c = (char) (Math.random() * 26 + 'A');
				stack.push(c);
				System.out.println("push2:" + c);
				// ÿ���ջһ�ξ�˯��һ��
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
				// �Է��ʶ���queue�Ĵ����ӻ�����
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

	// �����ڲ��Է�������
	public synchronized void push(char c) {
//		����ʹ��while������if
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
