package ch02_stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Stream
 *  - Stream은 요소들을 보관하지 않는다.
 *  - Stream 연산은 원본을 변경하지 않고 새로운 스트림을 만든다.
 *  - Stream 연산은 "가능하면" Lazy 처리된다.
 */
public class Lecture01 {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        long count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println("words longer than 12 : " + count);

        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println("words longer than 12 with parallel stream : " + count);
    }
}
