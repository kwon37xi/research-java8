package ch02_stream.exec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Stream과 상관없는 Thread 기반 병렬처리
 */
public class Exec01 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        final int processorCount = Runtime.getRuntime().availableProcessors();
        System.out.println("Processor count : " + processorCount);

        // AtomicInteger를 사용하지 않고 배열을 각 쓰레드가 배타적으로 접근하게 하면
        // 동기화로 인한 부하가 안생김.
        int[] counters = new int[processorCount];

        for (int i = 0; i < processorCount; i++) {
            final int currentStep = i + 1;
            final Thread thread = new Thread(() -> {
                int position = 1;
                int step = currentStep;
                while (true) {
                    final int processingIndex = position + step - 1;
                    if (processingIndex >= words.size()) {
                        System.out.println("Done " + step);
                        break;
                    }
                    if (words.get(processingIndex).length() > 12) {
                        counters[step - 1]++;
                    }

                    position++;
                }
            });
            thread.start();
            thread.join();
        }

        int totalCount = 0;
        for (int counter : counters) {
            totalCount = +counter;
        }
        System.out.println("Total count by thread : " + totalCount);

        System.out.println("Total count by parallelSteram : " + words.parallelStream().filter(w -> w.length() > 12).count());
    }
}
