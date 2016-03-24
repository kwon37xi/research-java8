package ch08_othertopics.exec;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * JDK src.zip 파일의 압축을 풀고 Files.walk로 transient와 volatile을 포함하는 모든 java 파일 찾기
 */
public class Exec10 {
    public static void main(String[] args) throws IOException {
        Path srcZip = Paths.get("/tmp/src");
        Files.walk(srcZip)
            .filter(p -> !Files.isDirectory(p))
            .filter(p -> {
                try {
                    return Files.readAllLines(p).stream().anyMatch(s -> s.contains("transient") || s.contains("volatile"));
                } catch (IOException e) {
                    throw new IllegalStateException(e.getMessage(), e);
                }
            }).forEach(System.out::println);
    }
}
