package ch08_othertopics;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.stream.Stream;

public class Lecture05_File {
    public static void main(String[] args) throws IOException {
        Path anne = Paths.get("java-8-impatient/anne.txt");

        // Files.lines()
        try (Stream<String> lines = Files.lines(anne).onClose(() -> System.out.println("Closing..."))) {
            lines.filter(s -> s.toLowerCase().contains("uncle"))
                .findFirst().ifPresent(System.out::println);
        }

        // BufferedReader.lines()
        // BufferedReader.lines() 로 얻은 stream을 닫는다고해서 BufferedReader가 close 되지는 않는다.
        // 따라서 try-for-resource에서는 BufferedReader를 받아야한다.
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://kwonnam.pe.kr").openStream()))) {
            reader.lines().forEach(System.out::println);
        }

        // Files.list()
        try (Stream<Path> entries = Files.list(Paths.get("/home/kwon37xi"))) {
            entries.forEach(p -> System.out.println(p.getFileName() + " : " + (Files.isDirectory(p) ? "DIR" : "FILE")));
        }

        // Files.walk()
        try (Stream<Path> walkEntries = Files.walk(Paths.get("/home/kwon37xi/projects"))) {
            walkEntries.forEach(p -> System.out.println(p.getFileName() + " : " + (Files.isDirectory(p) ? "DIR" : "FILE")));
        }

        // Base64
        Base64.Encoder encoder = Base64.getEncoder();
        String original = "손권남:대한민국";
        String encoded = encoder.encodeToString(original.getBytes(StandardCharsets.UTF_8));
        System.out.println("Base64 encoded : " + encoded);

        // Base64 with stream
        Base64.Encoder mimeEncoder = Base64.getMimeEncoder();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(anne, mimeEncoder.wrap(baos));
            baos.flush();

            Base64.Decoder decoder = Base64.getMimeDecoder();
            try (BufferedReader reader  = new BufferedReader(new InputStreamReader(decoder.wrap(new ByteArrayInputStream(baos.toByteArray()))))) {
                reader.lines().forEach(s -> System.out.println("Base64 Decoded : " + s));
            }
        }
    }
}
