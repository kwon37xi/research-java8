package ch01_lambda.practice;

/**
 * Callable 사용시에는 Void라 하더라도 lambda 표현식 내에서 return이 필요하다.
 */
public class Practice06 {
    public interface RunnableEx {
        void run() throws Exception;
    }

    public static Runnable uncheck(RunnableEx runnableEx) {
        return () -> {
            try {
                runnableEx.run();
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        };
    }

    public static void main(String[] args) {
        new Thread(uncheck(() -> {
            System.out.println(Thread.currentThread().getName() + " -> hello world");
            Thread.sleep(1000);
        })).start();
    }
}
