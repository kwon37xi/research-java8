package ch03_lambda_library;

import java.util.Arrays;
import java.util.function.IntConsumer;

public class Lecture02 {
    public static void main(String[] args) {
        final String[] languages = {"Java", "C", "C++", "Python", "Ruby", "Kotlin", "Scala", "Groovy"};
        Arrays.sort(languages, (s, t) -> Integer.compare(s.length(), t.length()));
        System.out.println(Arrays.toString(languages));

        // repeat
        repeat(10, i -> System.out.println("Countdown: " + (9 - i)));
        repeat(10, i -> System.out.println("Hello world!"));
    }

    public static void repeat(int n, IntConsumer action) {
        for (int i = 0; i < n; i++) {
            action.accept(i);
        }
    }

    public static void repeat(int n, Runnable action) {
        for (int i = 0; i < n; i++) {
            action.run();
        }
    }
}
