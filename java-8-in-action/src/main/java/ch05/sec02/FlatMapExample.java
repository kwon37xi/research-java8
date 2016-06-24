package ch05.sec02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FlatMapExample {
    public static void main(String[] args) {
        String[] arraysOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWords = Arrays.stream(arraysOfWords);

        List<String> words = Arrays.asList("Hello", "World");

        final List<String> distinctChars = words.stream()
            .map(w -> w.split(""))
            .flatMap(Arrays::stream)
            .distinct()
            .collect(toList());

        System.out.println("Distinct Chars : " + distinctChars);

        // quiz 5-2.1
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Quiz 5-2.1 : " + numbers.stream()
            .map(i -> i * i)
            .collect(toList()));

        // quiz 5-2.2
        List<Integer> firstNumbers = Arrays.asList(1, 2, 3);
        List<Integer> secondNumbers = Arrays.asList(3, 4);

        System.out.println("Quiz 5-2.2 : " + firstNumbers.stream()
            .flatMap(fn -> secondNumbers.stream().map(sn -> Arrays.asList(fn, sn)))
            .collect(toList()));

        // quiz 5-2.3 : 5-2.2에서 합이 3으로 나누어 떨어지는 것만
        System.out.println("Quiz 5-2.3 : " + firstNumbers.stream()
            .flatMap(fn -> secondNumbers.stream().map(sn -> Arrays.asList(fn, sn)))
            .filter(l -> (l.get(0) + l.get(1)) % 3 == 0)
            .collect(toList()));


    }
}
