package ch06_concurrency.exec;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class Exec02 {
    public static void main(String[] args) throws InterruptedException {
        // LongAdder는 증분 값을 계속 따로 저장하고 있다가 마지막에 sum을 하는 형태이기 때문에
        // 지속적으로 증분한 sum 값을 요청해야 하면 오히려 성능 저하가 발생할 것으로 보이며,
        // 증분시키면서 바로 그 순간의 값을 get할수 없고 증분 후 따로 sum()을 호출하기 때문에
        // 증분과 sum() 호출의 사이에 다른 증분이 발생하여 중복 값이 될 가능성이 있다.

        // 아래 실행 결과에 대해 sort | uniq 명령을 내려보면 중복이 발생한 것을 확인할 수 있다.
        LongAdder longAdder = new LongAdder();

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 10000; j++) {
                    longAdder.increment();
                    System.out.println(longAdder.sum());
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10L, TimeUnit.SECONDS);
    }
}
