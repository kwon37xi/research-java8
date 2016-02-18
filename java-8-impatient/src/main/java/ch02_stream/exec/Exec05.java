package ch02_stream.exec;

import java.util.stream.Stream;

/**
 * Stream.iterate로 Random 생성하기.
 */
public class Exec05 {
    public static void main(String[] args) {
        randomStream(23, 54, 567, System.currentTimeMillis()).limit(1000).forEach(System.out::println);
    }

    public static Stream<Long> randomStream(long a, long c, long m, long seed) {
        // 메소드 안에서 컴파일러가 값의 변경이 없음만 확신한다면 각 파라미터에 final 불필요.
        return Stream.iterate(seed, currentSeed -> (a * currentSeed + c) % m);
    }
}
