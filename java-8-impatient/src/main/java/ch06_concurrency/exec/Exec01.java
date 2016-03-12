package ch06_concurrency.exec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * 다수의 쓰레드에서 관찰하는 가장 긴 문자열을 추적하는 프로그램 작성.
 */
public class Exec01 {
    public static void main(String[] args) throws IOException {
        // 기본으로 가장 짧은 문자열을 지정해야 NullPointerException 발생안함.
        AtomicReference<String> longestString = new AtomicReference<>("");
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/alice.txt")), StandardCharsets.UTF_8);
        String[] words = contents.split("[\\P{L}]+");

        Stream.of(words).parallel() // parallel 로 다수의 쓰레드로 처리
            .forEach(next -> {
                longestString.updateAndGet(currentLongest -> currentLongest.length() > next.length() ? currentLongest : next);
            });

        // accumulator가 왜 필요한지 모르겠음.
        System.out.println("가장 긴 문자열 : " + longestString.get() + "(" + longestString.get().length() + ")");

    }
}
