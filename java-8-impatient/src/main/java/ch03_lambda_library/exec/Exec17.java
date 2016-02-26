package ch03_lambda_library.exec;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * first와 second를 병렬로 실행하는 doInParallelAsync 구현. 예외 발생시 handler 호출
 */
public class Exec17 {
    public static void main(String[] args) {
        doInParallelAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello!");
            throw new IllegalArgumentException("hello! exception");
        }, () -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("World!");
            throw new IllegalArgumentException("world! exception");
        }, throwable -> System.out.println("Exception occurred : " + throwable.getMessage()));

        System.out.println("Start thread!");
    }
    public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
        final Thread thread1 = new Thread(first);
        thread1.setUncaughtExceptionHandler((thread, ex) -> handler.accept(ex));
        thread1.start();

        Thread thread2 = new Thread(second);
        thread2.setUncaughtExceptionHandler((thread, ex) -> handler.accept(ex));
        thread2.start();
    }
}
