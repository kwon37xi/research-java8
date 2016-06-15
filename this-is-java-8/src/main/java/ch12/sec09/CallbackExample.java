package ch12.sec09;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class CallbackExample {
    private ExecutorService executorService;

    private CompletionHandler<Integer, Void> callback = new CompletionHandler<Integer, Void>() {
        @Override
        public void completed(Integer result, Void attachment) {
            System.out.println("completed() 실행 : " + result);
        }

        @Override
        public void failed(Throwable exc, Void attachment) {
            System.out.println("failed() 실행 : " + exc.toString());
        }
    };

    public CallbackExample() {
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void doWork(final String x, final String y) {
        Runnable task = () -> {
            try {
                int intX = Integer.parseInt(x);
                int intY = Integer.parseInt(y);
                int result = intX + intY;
                callback.completed(result, null);
            } catch (NumberFormatException ex) {
                callback.failed(ex, null);
            }
        };

        executorService.submit(task);
    }

    public void finish() {
        executorService.shutdown();
    }

    public static void main(String[] args) {
        CallbackExample example = new CallbackExample();
        example.doWork("3", "3");
        example.doWork("3", "삼");

        example.finish();
    }
}
