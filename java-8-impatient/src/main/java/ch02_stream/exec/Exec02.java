package ch02_stream.exec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * limit 5가 정해졌을 때 원하는 데이터를 얻고 나서는 스트림 처리 메소드를 호출하지 않음을 확인.
 */
public class Exec02 {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        words.stream().peek(w -> System.out.println("Processing " + w))
                .filter(w -> w.length() > 12)
                .limit(5)
                .forEach(w -> System.out.println(">> Long word : " + w));
    }
}
