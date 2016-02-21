package ch02_stream.exec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * 12번 문제를 filter/groupingBy,counting 과 조합
 */
public class Exec13 {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/alice.txt")), StandardCharsets.UTF_8);
        final String[] words = contents.split("[\\P{L}]+");

        final Map<Integer, Long> collect = Stream.of(words)
            .parallel()
            .filter(w -> w.length() < 12)
            .collect(Collectors.groupingBy(w -> w.length(), Collectors.counting()));

        System.out.println("짧은 문자열들의 길이별 갯수: " + collect);
    }
}
