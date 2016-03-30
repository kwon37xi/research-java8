package ch09_java7.exec;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Exec07 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.yahoo.com/");
        try (InputStream inputStream = url.openStream()) {
            Files.copy(inputStream, Paths.get("/tmp/ch09ex07.txt"));
        }
    }
}
