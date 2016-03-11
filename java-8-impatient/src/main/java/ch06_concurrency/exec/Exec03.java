package ch06_concurrency.exec;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 현재 내 PC에서 longAdder가 압도적으로 이김.
 */
public class Exec03 {
    public static void main(String[] args) {
        String type = args[0];
        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        AtomicLong atomicLong = new AtomicLong();
        LongAdder longAdder = new LongAdder();

        Instant start = Instant.now();
        switch (type) {
            case "atomiclong":
                executeAtomicLong(executorService, atomicLong);
                break;
            case "longadder":
                executeLongAdder(executorService, longAdder);
                break;
            default:
                System.err.println("Unknown type " + type);
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
        }
        Instant end = Instant.now();
        final Duration between = Duration.between(start, end);
        System.out.println(type + " time elapsed : " + between.toMillis() + " millis with " + (type.equals("atomiclong") ? atomicLong.get() : longAdder.sum()));


    }

    private static void executeAtomicLong(Executor executor, AtomicLong atomicLong) {

        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> {
                    for (int j = 0; j < 100000; j++) {
                        atomicLong.incrementAndGet();

                    }
                }
            );
        }
    }

    private static void executeLongAdder(Executor executor, LongAdder longAdder) {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> {
                    for (int j = 0; j < 100000; j++) {
                        longAdder.increment();
                    }
                }
            );
        }
    }
}
