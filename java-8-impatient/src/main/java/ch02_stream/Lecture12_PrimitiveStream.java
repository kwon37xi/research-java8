package ch02_stream;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Primitive Stream은
 * - toArray 메소드는 기본 타입 배열을 리턴한다.
 * - OptionalInt, OptionalLong, OptionalDouble
 * - sum, average, max, min..
 */
public class Lecture12_PrimitiveStream {
    public static void main(String[] args) {
        // IntStream
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        intStream = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 0, 10);

        IntStream zeroToNinetyNine = IntStream.range(0, 100);// 0~99
        IntStream zeroToHundred = IntStream.rangeClosed(0, 100); // 0~100
        System.out.printf("zeroToNinetyNine max : %d, zeroToHundred max : %d%n", zeroToNinetyNine.max().getAsInt(), zeroToHundred.max().getAsInt());

        String sentence = "\uD835\uDD46 is the set of octonions.";
        IntStream codes = sentence.codePoints(); // 16진수로 구성된 스트림

        // Object Stream to primitive stream
        Stream<String> words = Stream.of("Hello", "World", "Hi~");
        IntStream lengths = words.mapToInt(String::length);
        lengths.forEach(System.out::println); // 5 5 3

        Stream<Integer> integers = IntStream.range(0, 100).boxed();
        System.out.printf("integers max : %d%n", integers.max(Integer::compare).get());

        // Random
        Random random = new Random();
        random.ints().limit(3).forEach(i -> System.out.println("Random int : " + i));
    }
}
