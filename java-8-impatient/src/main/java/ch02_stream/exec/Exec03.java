package ch02_stream.exec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Exec03 {
    public static void main(String[] args) throws IOException, InterruptedException {


        // 첫번째 실행은 JVM 뜰때의 부하로 인해 올바르게 나오지 않음.
        filterWithParallelStream();
        filterWithStream();
        System.out.println("============");
        // 두번째 실행 parallel이 빠르고 그냥 stream은 좀 느림.
        filterWithParallelStream();
        filterWithStream();
    }

    private static void filterWithParallelStream() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/many_kingjames_bible.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        long start = System.currentTimeMillis();
        long longWordCount = words.parallelStream().filter(w -> w.length() > 12).count();
        long end = System.currentTimeMillis();

        System.out.printf("Long words count with parallel stream : %d / %d ms%n", longWordCount, end - start);
    }

    private static void filterWithStream() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/many_kingjames_bible.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        long start = System.currentTimeMillis();
        long longWordCount = words.stream().filter(w -> w.length() > 12).count();
        long end = System.currentTimeMillis();

        System.out.printf("Long words count with stream : %d / %d ms%n", longWordCount, end - start);
    }
}
