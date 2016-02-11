package ch01_lambda.practice;

public class Practice07 {
    public static Runnable andThen(Runnable runnable1, Runnable runnable2) {
        return () -> {
            runnable1.run();
            runnable2.run();
        };
    }

    public static void main(String[] args) {
        andThen(() -> System.out.println("first!"), () -> System.out.println("second!")).run();
    }
}
