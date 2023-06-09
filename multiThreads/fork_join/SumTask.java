package fork_join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

// Task: Calculate the sum from 1 to 10 million

/*
 * RecursiveTask<V>: Abstract class, have a return value.
 * RecursiveAction<V>: Abstract class, have no return value.
*/
public class SumTask extends RecursiveTask<Long>	{
	
	private static final long serialVersionUID = 1L;
	
	private final int start;
	private final int end;
//	ÿ��task����ֵ�����������˴���ʾѭ�������
	private final int THRESHOLD = 10000;
	
	public SumTask(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
//	compute()��The main computation (recursive)
	@Override
	protected Long compute() {
		long sum = 0;
		
		if (end - start + 1 <= THRESHOLD) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		}else {
//			������������ֵ���ָ�����������԰�ָ�
			int middle = (end - start) / 2;
//			��ʼ���ָ���task��
			SumTask task1 = new SumTask(start, middle);
			SumTask task2 = new SumTask(middle + 1, end);
			
//			fork()����task�����������У�Ĭ��Ϊ�޽���У�
			task1.fork();
//			invokeAll(task1, task2);
			
//			join()������Future����isDoneʱ��������
			sum = task2.compute() + task1.join();
		}
		return sum;
	}
	
	public static void main(String[] args) {
//		����ForkJoinPool�����̳߳أ�
		ForkJoinPool pool = new ForkJoinPool();
//		����������
		SumTask task = new SumTask(1, 20000);
		
		/* ��ForkJoinPool�ύtask��
		 * invoke() ������һ���������ã����Եõ�����ִ�еĽ����
		 * execute() ������һ���첽���ã����᷵������ִ�н����
		 * submit() ����Ҳ��һ���첽���ã�������ͨ�����ص� Future ��ȡ�����ִ�н�����˴���ȡһ��Future<Long>����
		 * */	
//		Future<Long> future = pool.submit(task);
		ForkJoinTask<Long> future = pool.submit(task);
		
//		�ж��߳����Ƿ�����쳣��
		if (future.isCompletedAbnormally()) {
			System.out.println(future.getException());
		}
		
		try {
			long sum = future.get();
			System.out.println(sum);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
//		long sum = pool.invoke(task);
//		System.out.println(sum);
	}
	
}
