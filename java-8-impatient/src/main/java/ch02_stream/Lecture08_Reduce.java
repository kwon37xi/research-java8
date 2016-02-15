package ch02_stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class Lecture08_Reduce {
    public static void main(String[] args) throws IOException {
        Stream<Integer> values = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        Optional<Integer> sum = values.reduce((x, y) -> x + y); // 스트림을 모두 더한다.
//        Integer sum = values.reduce(0, (x, y) -> x + y); // 무조건 항등값 0이 존재하므로 Optional이 아니어도됨.
        Optional<Integer> sum = values.reduce(Integer::sum);
        sum.ifPresent(s -> System.out.println("Sum : " + s)); // 55

        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/alice.txt")), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("[\\P{L}]+")).skip(1);
        final Integer totalLength = words.reduce(0, (total, word) -> total + word.length(),
                (total1, total2) -> total1 + total2);// total : int, word : string
//        int totalLength = words.mapToInt(String::length).sum();
        System.out.println("TotalLength : " + totalLength);
    }
}
