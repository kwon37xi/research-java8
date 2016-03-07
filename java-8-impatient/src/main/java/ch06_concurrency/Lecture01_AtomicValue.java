package ch06_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class Lecture01_AtomicValue {
    public static AtomicLong nextNumber = new AtomicLong();

    public static AtomicLong largest = new AtomicLong();

    public static void main(String[] args) throws InterruptedException {
        long id = nextNumber.incrementAndGet();
        System.out.println("Atomic Long : " + id);

        // 멀티 쓰레드 상황에서 최대값 저장하기
        long oldValue = 0;
        long newValue = 0;
        long observed = 5;
        do {
            oldValue = largest.get();
            newValue = Math.max(oldValue, observed);
            // 기존 largest가 oldValue 인지 여부를 비교하고 그럴 때만 newValue로 set.
        } while (!largest.compareAndSet(oldValue, newValue));

        // lambda로 처리
        final long finalObserved = observed;
        final long largestValue = largest.updateAndGet(x -> Math.max(x, finalObserved));// x 는 oldValue의 역할을 한다.
        System.out.println("largest value : " + largestValue);

        // 혹은
        observed = 7;
        System.out.println("accumulateAndGet largest value : " + largest.accumulateAndGet(observed, Math::max));

        // LongAdder : 멀티 쓰레드 숫자값증가를 더욱 빠르게
        ExecutorService executorService = Executors.newCachedThreadPool();
        final LongAdder adder = new LongAdder();

        for (int i = 0; i < 15; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    adder.increment();
                    // adder.add(1);
                }
            });
        }

        TimeUnit.SECONDS.sleep(2);
        System.out.println("Final adder result : " + adder.sum());

        // LongAccumulator : 연산과, 연산에 대한 중립 요소를 통해 빠른 연산 제공.
        // 연산은 결합 법칙과 교환 법칙이 성립해야 한다. 즉, 최종 결과가
        // 중간 값들을 결합하는 순서와 무관해야한다.
        LongAccumulator accAdder = new LongAccumulator(Long::sum, 0L);

        for (int i = 0; i <20; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    accAdder.accumulate(3);
                }
            });
        }

        TimeUnit.SECONDS.sleep(3);
        System.out.println("Final accumulator adder result " + accAdder.get());

        shutdownExecutor(executorService);
    }

    private static void shutdownExecutor(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5L, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (Exception ex) {
            executorService.shutdownNow();
        }
    }

}
