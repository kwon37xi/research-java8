package ch06_concurrency.exec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * reduceEntries를 사용하여 ConcurrentHashMap<String, Long> 에서 값이 제일큰 key 찾기.
 */
public class Exec07 {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/kingjames_bible.txt")), StandardCharsets.UTF_8);
        String[] words = contents.split("[\\P{L}]+");
        ConcurrentHashMap<String, Long> wordLengths = new ConcurrentHashMap<>();

        // 단어를 수집하여 단어의 길이를 값으로 저장.
        Stream.of(words).parallel()
            .filter((w) -> w.length() > 0)
            .forEach((w) -> wordLengths.putIfAbsent(w.toLowerCase(), Long.valueOf(w.length())));

        final Map.Entry<String, Long> longestEntry = wordLengths.reduceEntries(100, (e1, e2) -> e1.getValue() > e2.getValue() ? e1 : e2);
        System.out.println("Longest word : " + longestEntry.getKey() + "'s length is " + longestEntry.getValue());
    }
}
