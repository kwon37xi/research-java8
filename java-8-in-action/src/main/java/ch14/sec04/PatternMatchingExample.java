package ch14.sec04;

import java.util.function.Supplier;

public class PatternMatchingExample {
    public static <T> T myIf(boolean b, Supplier<T> truecase, Supplier<T> falsecase) {
        return b ? truecase.get() : falsecase.get();
    }

    public static void main(String[] args) {
        String a = "hello";

        System.out.println("True : " + myIf(a.equals("hello"), () -> "world", () -> "unknown"));
        System.out.println("false : " + myIf(a.equals("Greeting"), () -> "world", () -> "unknown"));
    }
}
