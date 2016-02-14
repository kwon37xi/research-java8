package ch02_stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Lecture03 {
    public static void main(String[] args) throws IOException {
        final Path alicePath = Paths.get("java-8-impatient/alice.txt");
        String contents = new String(Files.readAllBytes(alicePath), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("[\\P{L}]+"));

        final Stream<String> lowercaseWords = words.map(String::toLowerCase);// toLowerCase
        System.out.println("=== lowercase");
        lowercaseWords.forEach(System.out::println);

        words = Stream.of(contents.split("[\\P{L}]+")).skip(1);
        final Stream<Character> firstChars = words.map(s -> s.charAt(0));// String -> char
        System.out.println("=== firstChars");
        firstChars.forEach(System.out::println);

        words = Stream.of(contents.split("[\\P{L}]+")).skip(1);
        final Stream<Stream<Character>> charStreamStreams = words.map(Lecture03::characterStream);

        // Stream<Char>들을 펼친 스트림으로 변환.
        words = Stream.of(contents.split("[\\P{L}]+")).skip(1);
        final Stream<Character> letters = words.flatMap(Lecture03::characterStream);
//        letters.forEach(System.out::println);
    }

    public static Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();

        for (char c: s.toCharArray()) result.add(c);
        return result.stream();
    }
}
