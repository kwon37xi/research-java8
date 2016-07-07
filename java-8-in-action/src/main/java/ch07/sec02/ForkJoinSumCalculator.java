package ch07.sec02;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 태스크를 잘게 나누어야 작업 훔치기(work strealing)으로 쓰레드간 작업 부하를 유사하게 유지할 수 있다.
 * 두 서브 태스크가 모두 시작된 다음에 join을 호출해야 한다.
 * RecursiveTask 안에서는 ForkJoinPool.invoke()를 호출하면 안된다.
 * 한쪽 작업에는 fork보다는 compute를 호출한다.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;

        if (length < THRESHOLD) {
            return computeSequentially();
        }

        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();

        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResutl = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult + rightResutl;
    }

    private Long computeSequentially() {
        long sum = 0;
        System.out.printf("Processing [%s] start(%d)/end(%d)%n", Thread.currentThread().getName(), start, end );
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }

        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
    public static void main(String[] args) {
        System.out.println("Sum from 1 to 100,000 : " + forkJoinSum(100_000));
    }
}
