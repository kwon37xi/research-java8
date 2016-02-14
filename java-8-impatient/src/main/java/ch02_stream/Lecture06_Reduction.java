package ch02_stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class Lecture06_Reduction {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/alice.txt")), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("[\\P{L}]+")).skip(1);

//        final Optional<String> largest = words.max(String::compareToIgnoreCase);
        final Optional<String> longest = words.max((o1, o2) -> Integer.compare(o1.length(), o2.length()));
        longest.ifPresent(s -> System.out.println("longest: " + s));

        words = Stream.of(contents.split("[\\P{L}]+")).skip(1);
        words.filter(s -> s.startsWith("Q")).findFirst().ifPresent(s -> System.out.println("with Q : " + s));

        words = Stream.of(contents.split("[\\P{L}]+")).skip(1);
        words.parallel().filter(s -> s.startsWith("Q")).findAny().ifPresent(s -> System.out.println("with Q any : " + s));

        words = Stream.of(contents.split("[\\P{L}]+")).skip(1);
        final boolean anyStatsWithQ = words.parallel().anyMatch(s -> s.startsWith("Q"));
        System.out.println("any starts with Q " + anyStatsWithQ);
    }
}
