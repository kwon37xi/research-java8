package ch05.sec07;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GeneratingStreamExample {
    public static void main(String[] args) {

        final Stream<String> stringStream = Stream.of("Java 8", "Lambdas", "in", "Action");
        stringStream.map(String::toUpperCase).forEach(System.out::println);

        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println("Sum of number array stream : " + sum);

        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("java-8-impatient/anne.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("unique words of anne.txt : " + uniqueWords);

        Stream.iterate(0, n -> n + 2)
            .limit(100)
            .forEach(System.out::println);

        // quiz 5-4
        System.out.println("Fibonacci numbers : " + Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
            .limit(10)
            .map(f -> String.format("(%d,%d)", f[0], f[1]))
            .collect(Collectors.joining(",")));

        Stream.generate(Math::random)
            .limit(5)
            .forEach(System.out::println);

        IntStream ones = IntStream.generate(() -> 1);
        ones.limit(5).forEach(System.out::println);

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };

        System.out.println("Fibonacci with IntStream");
        IntStream.generate(fib).limit(10).forEach(System.out::println);

    }
}
