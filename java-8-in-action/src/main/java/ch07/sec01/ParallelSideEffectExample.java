package ch07.sec01;

import java.util.stream.LongStream;

import static ch07.sec01.ParallelStreamExample.measuresSumPerf;

/**
 *
 */
public class ParallelSideEffectExample {
    public static class Accumulator {
        public long total = 0L;

        // 이 메소드를 synchronized하면 병렬화의 이득이 모두 사라져버림.
        // 안하면 병렬화를 할 수 없음.
        public void add(long value) {
            total += value;
        }
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static void main(String[] args) {
        // 실행 result가 매번 다르게 나옴.
        System.out.println("SideEffect parallel sum done in: " + measuresSumPerf(ParallelSideEffectExample::sideEffectParallelSum, 10_000_000) + " msecs");
    }
}
