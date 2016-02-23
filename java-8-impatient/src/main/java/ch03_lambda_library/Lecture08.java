package ch03_lambda_library;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Exception 처리
 */
public class Lecture08 {
    public static void doInOrder(Runnable first, Runnable second) {
        first.run(); // 예외가 발생하면
        second.run(); // 이건 아예 실행이 안된다.
        // 호출자가 예외 다룸
    }

    public static void doInOrderAsync(Runnable first, Runnable second) {
        Thread t = new Thread() {
            public void run() {
                first.run(); // 예외를 던지면
                second.run(); // 실행안되고
            }
        };
        // 별도 쓰레드라서 예외가 사라져버린다.
        t.start();
    }

    /*
    비동기로 코드를 실행할 때는 예외 핸들러를 두는 것이 좋다.
     */
    public static void doInOrderAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
        Thread t = new Thread() {
            public void run() {
                try {
                    first.run();
                    second.run();
                } catch (Throwable t) {
                    handler.accept(t);
                }
            }
        };
        t.start();
    }

    /* first가 생산한 것을 second가 사용하고 비동기로 실행 */
    public static <T> void doInOrderAdync(Supplier<T> first, Consumer<T> second, Consumer<Throwable> handler) {
        Thread t = new Thread() {
            public void run() {
                try {
                    T result = first.get();
                    second.accept(result);
                } catch (Throwable t) {
                    handler.accept(t);
                }
            }
        };
        t.start();
    }

    /* checked exception 을 던지는 Supplier 생성 */
    public static <T> Supplier<T> unchecked(Callable<T> f) {
        return () -> {
            try {
                return f.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } catch (Throwable t) {
                throw t;
            }
        };
    }

    public static void main(String[] args) {
        final Supplier<String> fileReader = unchecked(() -> new String(Files.readAllBytes(Paths.get("/etc/passwd")), StandardCharsets.UTF_8));
        System.out.println(fileReader.get());
    }
}
