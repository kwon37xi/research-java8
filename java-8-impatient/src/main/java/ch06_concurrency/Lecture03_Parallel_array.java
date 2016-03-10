package ch06_concurrency;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Lecture03_Parallel_array {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/alice.txt")), StandardCharsets.UTF_8);
        String[] words = contents.split("[\\P{L}]+");
//        Arrays.parallelSort(words);
        Arrays.parallelSort(words, words.length / 2, words.length);
        System.out.println(Arrays.toString(words));

        int[] values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.parallelSetAll(values, i -> i  % 10); // i는 요소의 인덱스이다. 배열의 값이 아니다!
        System.out.println("parallelSetAll : " + Arrays.toString(values));

        values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.parallelPrefix(values, (x, y) -> x * y);
        System.out.println("parallelPrefix : " + Arrays.toString(values));
    }
}
