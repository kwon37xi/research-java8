package ch06_concurrency.exec;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Arrays.parallelSort가 Arrays.sort 보다 빨라지는 배열의 크기는?
 *
 * 상황에 따라 다르지만 대체로 배열 크기가 3000 정도일 때 parallel이 더 빨라졌다.
 * Intel(R) Core(TM) i7-5500U CPU @ 2.40GHz Quad Core
 */
public class Exec08 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/kingjames_bible.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        System.out.println("Words size : " + words.size());

        for (int arrayLength = 1000; arrayLength < words.size(); arrayLength += 1000) {
            String[] arrayForParallel = words.toArray(new String[]{});
            String[] arrayForSingle = words.toArray(new String[]{});

            Instant parallelStart = Instant.now();
            Arrays.sort(arrayForSingle, 0, arrayLength);
            Instant parallelEnd = Instant.now();

            Instant singleStart = Instant.now();
            Arrays.sort(arrayForParallel, 0, arrayLength);
            Instant singleEnd = Instant.now();

            final long singleDurationMillis = Duration.between(singleStart, singleEnd).toMillis();
            final long parallelDurationMillis = Duration.between(parallelStart, parallelEnd).toMillis();

            System.out.printf("arrayLength : %d single : %d ms parallel : %d ms%n", arrayLength, singleDurationMillis, parallelDurationMillis);
            if (singleDurationMillis > parallelDurationMillis) {
                System.out.println("### parallel is faster than single at array length " + arrayLength + ".");
                break;
            }
            TimeUnit.SECONDS.sleep(2L);
        }
    }


}
