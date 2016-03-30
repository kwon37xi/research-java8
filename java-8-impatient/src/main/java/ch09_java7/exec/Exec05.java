package ch09_java7.exec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 파일의 모든 문자를 읽어와서 역순으로 쓰는 프로그램 작성.
 */
public class Exec05 {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/anne.txt")), StandardCharsets.UTF_8);
        StringBuilder reverted = new StringBuilder(contents.length());
        for (int i = contents.length() - 1; i >= 0; i--) {
            reverted.append(contents.charAt(i));
        }
        Files.write(Paths.get("/tmp/ch09ex05.txt"), reverted.toString().getBytes(StandardCharsets.UTF_8));
    }
}
