package ch12.sec09;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * submit()은 작업 처리도중 예외가 발생하더라도 종료되지 않고 다음 작업을 위해 재사용된다.
 * execute()는 작업 처리도중 예외가 발생하면 쓰레드가 종료되고 해당 쓰레드는 풀에서 제거된다.
 *
 * 쓰레드 생성 오버헤드를 줄이려면 submit()을 사용하는 것이좋다.
 */
public class ExecuteExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            Runnable runnable = () -> {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
                int poolSize = threadPoolExecutor.getPoolSize();
                String threadName = Thread.currentThread().getName();
                System.out.println("[총 스레드 갯수: " + poolSize + "] 작업 스레드 이름: " + threadName);
                int value = Integer.parseInt("삼");
            };

//            executorService.execute(runnable); // execute는 매번 새로운 쓰레드 이름이 나온다.
            executorService.submit(runnable); // submit는 2개의 쓰레 드를 번갈아 사용한다.
            Thread.sleep(10);
        }
        executorService.shutdown();
    }
}
