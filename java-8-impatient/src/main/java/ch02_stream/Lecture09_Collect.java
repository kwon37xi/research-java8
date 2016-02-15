package ch02_stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lecture09_Collect {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/alice.txt")), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("[\\P{L}]+")).skip(1);

//        String[] result = words.filter(s -> s.length() > 14).toArray(String[]::new); // Stream -> Array
//        System.out.println("words : " + Arrays.toString(result));

        // HashSet<String> result = stream.collect(HashSet::new, HashSet::add, HashSet::addAll);

        final String result = words.filter(s -> s.length() > 14).collect(Collectors.joining(" | "));
        System.out.println("words : " + result);

        words = Stream.of(contents.split("[\\P{L}]+")).skip(1);
        final IntSummaryStatistics collect = words.collect(Collectors.summarizingInt(String::length));
        System.out.println("Sum : " + collect.getSum());
    }

}
