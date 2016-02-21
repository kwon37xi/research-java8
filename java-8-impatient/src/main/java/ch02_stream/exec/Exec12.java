package ch02_stream.exec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/*
 * AtomicInteger를 통해 병렬로 단어 갯수 세기
 */
public class Exec12 {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/alice.txt")), StandardCharsets.UTF_8);
        final String[] words = contents.split("[\\P{L}]+");


        final AtomicInteger counter = new AtomicInteger();

        Stream.of(words).skip(1).parallel().unordered()
            .forEach(w -> {
                    if (w.length() < 12) {
                        counter.getAndIncrement();
                    }
                }
            );

        System.out.println("Short word count : " + counter.get());

        // filter/count 사용
        final long shortWordCount = Stream.of(words).skip(1).parallel().unordered()
            .filter(w -> w.length() < 12).count();
        System.out.println("Short word count with filter/count : " + shortWordCount);
    }
}
