package ch01_lambda;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;

public class LambdaExpressions {
    public static void main(String[] args) {

        String firstStr = "hello";
        String secondStr = "hi";
        final String[] greetings = {firstStr, secondStr};
        Arrays.sort(greetings, (String first, String second) -> {
            if (first.length() < second.length()) {
                return -1;
            } else if (first.length() > second.length()) {
                return 1;
            }
            return 0;
        });
        System.out.println(Arrays.toString(greetings));

        BiFunction<String, String, Integer> comp = (first, second) -> Integer.compare(first.length(), second.length());

//        Runnable sleeper = () -> { System.out.println("Hello world"); Thread.sleep(1000); }; // fail
        Callable<Void> sleeper = () -> {
            System.out.println("Hello World");
            Thread.sleep(1000L);
            return null;
        };

        Arrays.sort(greetings, (String first, String second) -> first.compareToIgnoreCase(second));
        // 위와 아래는 동일하다.
        Arrays.sort(greetings, String::compareToIgnoreCase);
    }
}
