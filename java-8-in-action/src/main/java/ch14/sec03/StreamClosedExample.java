package ch14.sec03;

import ch06.sec04.NumberPartitioningExample;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 아래는 제대로 작동 안하는 예제이다.
 */
public class StreamClosedExample {

    public static Stream<Integer> primes(int n) {
        return Stream.iterate(2, i -> i + 1)
            .filter(NumberPartitioningExample::isPrime)
            .limit(n);
    }

    public static IntStream numbers() {
        return IntStream.iterate(2, n -> n + 1);
    }

    public static int head(IntStream numbers) {
        return numbers.findFirst().getAsInt();
    }

    public static IntStream tail(IntStream numbers) {
        return numbers.skip(1);
    }

    public static IntStream primes(IntStream numbers) {
        int head = head(numbers);
        return IntStream.concat(IntStream.of(head), primes(tail(numbers).filter(n -> n % head != 0)));
    }

	/**
     * Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed 오류가 발생한다.
     * @param args
     */
    public static void main(String[] args) {
        primes(numbers()).forEach(value -> {
            System.out.println("Prime Number : " + value);
        });
    }
}
