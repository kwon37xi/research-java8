package ch02_stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

// 상태 유지 변환
public class Lecture05 {
    public static void main(String[] args) throws IOException {
        final Stream<String> uniqueWords = Stream.of("merrily", "merrily", "merrily", "gently").distinct();
        uniqueWords.forEach(System.out::println);

        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/alice.txt")), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("[\\P{L}]+")).skip(1);

        final Stream<String> longestFirst = words
                .filter(s -> s.length() > 10)
                .sorted(Comparator.comparing(String::length).reversed());// String.length()를 키로하여 정렬
        longestFirst.forEach(System.out::println);

    }
}
