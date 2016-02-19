package ch02_stream.exec;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * characterStram 개선
 */
public class Exec06 {
    public static void main(String[] args) {
        characterStream("Hello World").map(Character::toUpperCase).forEach(System.out::print);
    }

    public static Stream<Character> characterStream(String s) {
        // IntStream의 map 은 int 만 리턴한다. mapToObj 를 사용해야 한다.
        return IntStream.range(0, s.length()).mapToObj(i -> s.charAt(i));
    }
}
