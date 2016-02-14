package ch02_stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Lecture04 {
    public static void main(String[] args) throws IOException {
        // 무한 random 생성 Stream에서 100개만 추출
        final Stream<Double> randoms = Stream.generate(Math::random).limit(100);
        final int[] i = {1};
        randoms.forEach(r -> {
            System.out.printf("Random %03d: %.10f%n", i[0], r);
            i[0]++;
        });

        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/alice.txt")), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("[\\P{L}]+")).skip(1);

        final Stream<Character> combined = Stream.concat(characterStream("Hello"), characterStream("World"));
        combined.forEach(System.out::println);

        // peck은 원본과 동일한 요소들을 포함하는 또 다른 스트림을 돌려주지만, 전달받은
        // 함수를 요수 추출 시마다 호출 -> 디버깅에 유용하다.
        final Object[] objects = Stream.iterate(1.0, p -> p * 2)
                .peek(e -> System.out.println("Fetching " + e))
                .limit(20).toArray();
        System.out.println(Arrays.toString(objects));
    }

    public static Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();

        for (char c: s.toCharArray()) result.add(c);
        return result.stream();
    }
}
