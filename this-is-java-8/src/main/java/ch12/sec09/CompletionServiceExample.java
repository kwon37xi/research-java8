package ch12.sec09;

import java.util.concurrent.*;

/**
 * 결과가 순서 없이 나옴.
 */
public class CompletionServiceExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        System.out.println("[작업 처리 요청]");
        for (int i = 0; i < 3; i++) {
            final int target = (i + 1) * 10;
            completionService.submit(() -> {
                int sum = 0;
                for (int idx = 1; idx <= target; idx++) {
                    sum += idx;
                }

                return sum;
            });
        }

        System.out.println("[처리 완료된 작업 확인]");
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Future<Integer> future = completionService.take(); // 정확한 갯수 지정안하면 무한정 기다리는 듯.
                        int value = future.get();
                        System.out.println("[처리 결과] " + value);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        break;
                    }
                }
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        executorService.shutdownNow();
    }
}
