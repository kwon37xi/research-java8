package ch06.infinitestream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PrimesFailure {
    public static boolean isPrime(final int number) {
        return number > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(number))
            .noneMatch(divisor -> number % divisor == 0);
    }

    public static List<Integer> primes(final int number) {
        if (isPrime(number)) {
            System.out.println("current prime : " + number);
            return concat(number, primes(number + 1));
        }
        return primes(number + 1);
    }

    private static List<Integer> concat(int number, List<Integer> otherPrimes) {
        List<Integer> primes = new ArrayList<>();
        primes.add(number);
        primes.addAll(otherPrimes);
        return primes;
    }

    public static void main(String[] args) {
        System.out.println(primes(1));
    }
}
