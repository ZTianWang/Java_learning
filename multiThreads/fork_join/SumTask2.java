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
public class SumTask2 extends RecursiveTask<Long>	{
	private final int start;
	private final int end;
	private final int THRESHOLD = 10000;
	public SumTask2(int start, int end) {
		this.start = start;
		this.end = end;
	}
	protected Long compute() {
		long sum = 0;
		
		if (end - start + 1 <= THRESHOLD) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		}else {
			int middle = (end - start) / 2;
			SumTask2 task1 = new SumTask2(start, middle);
			SumTask2 task2 = new SumTask2(middle + 1, end);
			task1.fork();
			task2.fork();
			sum = task1.join() + task2.join();
		}
		return sum;
	}
	
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		SumTask2 task = new SumTask2(1, 10000000);
		ForkJoinTask<Long> future = pool.submit(task);
		if (future.isCompletedAbnormally()) {
			System.out.println(future.getException());
		}
		try {
			long sum = future.get();
			System.out.println(sum);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
