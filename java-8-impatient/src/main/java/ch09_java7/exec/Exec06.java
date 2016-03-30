package ch09_java7.exec;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * 파일의 모든 행을 읽어서 역순으로 쓰는 프로그램 작성.
 */
public class Exec06 {
    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Paths.get("java-8-impatient/alice.txt"));
        Collections.reverse(lines);
        Files.write(Paths.get("/tmp/ch09ex06.txt"), lines);
    }
}
