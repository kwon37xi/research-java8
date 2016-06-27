package ch05.sec07;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
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
    }
}
