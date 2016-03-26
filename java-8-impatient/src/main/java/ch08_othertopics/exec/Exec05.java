package ch08_othertopics.exec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * words.stream().filter(w -> w.length() > 12).count() 를 스트림없이 람다로 하기
 *
 * -> 가장 빠른 : Collection.forEach
 * -> 두번째로 빠른 : stream.filter.count
 * -> 가장 느린 : Collection.removeIf, size
 */
public class Exec05 {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/many_kingjames_bible.txt")), StandardCharsets.UTF_8);
        List<String> words = new ArrayList<>(Arrays.asList(contents.split("[\\P{L}]+")));

        Instant start = Instant.now();
        long count = countByForEach(words);
//        long count = countByRemoveIf(words);
//        long count = countByStream(words);
        Instant end = Instant.now();

        System.out.printf("Count : %d time : %dms%n", count, Duration.between(start, end).toMillis());
    }

    private static long countByForEach(List<String> words) {
        long[] count = {0L};
        words.forEach(w -> {
            if (w.length() > 12) {
                count[0]++;
            }
        });
        return count[0];
    }

    private static long countByStream(List<String> words) {
        return words.stream().filter(w -> w.length() > 12).count();
    }

    private static long countByRemoveIf(List<String> words) {
        words.removeIf(w -> w.length() <= 12);
        return words.size();
    }
}
