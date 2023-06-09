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
//	每个task的阈值（计算量：此处表示循序次数）
	private final int THRESHOLD = 10000;
	
	public SumTask(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
//	compute()：The main computation (recursive)
	@Override
	protected Long compute() {
		long sum = 0;
		
		if (end - start + 1 <= THRESHOLD) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		}else {
//			若数量大于阈值，分割任务参数：对半分割
			int middle = (end - start) / 2;
//			初始化分割后的task：
			SumTask task1 = new SumTask(start, middle);
			SumTask task2 = new SumTask(middle + 1, end);
			
//			fork()：将task加入阻塞队列（默认为无界队列）
			task1.fork();
//			invokeAll(task1, task2);
			
//			join()：返回Future对象isDone时的运算结果
			sum = task2.compute() + task1.join();
		}
		return sum;
	}
	
	public static void main(String[] args) {
//		创建ForkJoinPool管理线程池：
		ForkJoinPool pool = new ForkJoinPool();
//		创建总任务：
		SumTask task = new SumTask(1, 20000);
		
		/* 向ForkJoinPool提交task：
		 * invoke() 方法是一个阻塞调用，可以得到任务执行的结果；
		 * execute() 方法是一个异步调用，不会返回任务执行结果；
		 * submit() 方法也是一个异步调用，但可以通过返回的 Future 获取任务的执行结果，此处获取一个Future<Long>对象。
		 * */	
//		Future<Long> future = pool.submit(task);
		ForkJoinTask<Long> future = pool.submit(task);
		
//		判断线程中是否出现异常：
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
