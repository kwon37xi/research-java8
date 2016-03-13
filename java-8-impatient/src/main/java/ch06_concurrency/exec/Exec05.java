package ch06_concurrency.exec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * 다수의 쓰레드가 파일 집합에서 모든 단어를 읽는 애플리케이션을 작성하라, 각 단어가 어느 파일들에서 나타나는지 추적하는데
 * ConcurrentHashMap을 사용하고, 맵을 업데이트하는데 merge 메서드를 사용하라.
 */
public class Exec05 {
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
            words.parallel().forEach(word -> {
                wordsInFile.merge(word.toLowerCase(), new HashSet<>(), (existSet, newSetToBeAdded) -> {
                    existSet.add(path);
                    return existSet;
                });
            });
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
