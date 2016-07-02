package ch06.sec04;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 정수 n을 받아서 2부터 n까지의 자연수를 소수와 비소수로 구분하기
 */
public class NumberPartitioningExample {
    public static void main(String[] args) {
        final Map<Boolean, List<Integer>> primeAndNonprimes = partitionPrimes(20);
        System.out.println("Prime : " + primeAndNonprimes.get(Boolean.TRUE));
        System.out.println("NonPrime : " + primeAndNonprimes.get(Boolean.FALSE));
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
            .collect(Collectors.partitioningBy(NumberPartitioningExample::isPrime));
    }
    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
            .noneMatch(i -> candidate % i == 0);
    }
}
