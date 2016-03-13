package ch06_concurrency.exec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * copmuteIfAbsent를 사용하여 05번 문제 풀기.
 * 이 방식을 사용하니까 불필요하게 빈 HashSet 객체를 만들 필요가 없어졌다.
 */
public class Exec06 {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Set<Path>> wordsInFile = new ConcurrentHashMap<>();

        Path[] paths = new Path[]{
            Paths.get("java-8-impatient/alice.txt"),
            Paths.get("java-8-impatient/anne.txt"),
            Paths.get("java-8-impatient/frankenstein.txt"),
            Paths.get("java-8-impatient/kingjames_bible.txt"),
            Paths.get("java-8-impatient/war_and_peace.txt")
        };
        Stream.of(paths).parallel().forEach(path -> {
            System.out.println(Thread.currentThread().getName() + "  - path : " + path);
            final Stream<String> words = streamOfFileWords(path);

            // 여기서 Set을 CocurrentHashMap으로부터 만들어야만 할 것으로 보인다.
            // HashSet도 작동상의 문제는 없었으나, 이는 하나의 Key에 대한 동시 접근이 그리 많지 않았기 때문으로 보이고,
            // 값이 되는 Set을 concurrent를 처리할 수 있게 만드는 것이 원칙에 맞는 것으로 보임.
            words.parallel().forEach(word -> wordsInFile.computeIfAbsent(word.toLowerCase(), (wordToPut) -> ConcurrentHashMap.<Path>newKeySet()).add(path));
        });

        System.out.println(wordsInFile);
    }

    public static Stream<String> streamOfFileWords(Path path) {

        try {
            String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            String[] words = contents.split("[\\P{L}]+");
            return Stream.of(words).filter((word) -> word.length() != 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
