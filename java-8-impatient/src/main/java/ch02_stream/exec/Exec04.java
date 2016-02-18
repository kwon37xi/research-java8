package ch02_stream.exec;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * int stream을 얻으려면?
 */
public class Exec04 {
    public static void main(String[] args) {

        int[] values = {1, 4, 9, 16};
        final Stream<int[]> intArrStream = Stream.of(values); // int[]의 stream

        final IntStream intStream = IntStream.of(values); // IntStream.of 를 써야 올바른 int의 stream이 생긴다.

    }
}
