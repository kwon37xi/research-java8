package ch13.sec03;

import java.util.stream.LongStream;

/**
 *
 */
public class FactorialExample {
    public static int factorialIterative(int n) {
        int r = 1;
        for (int i = 1; i <= n; i++) {
            r *= i;
        }

        return r;
    }

    public static long factorialRecursive(long n) {
        return n == 1 ? 1 : n * factorialRecursive(n - 1);
    }

    public static long factorialStream(long n) {
        return LongStream.rangeClosed(1, n)
            .reduce(1, (long a, long b) -> a * b);
    }

    public static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    private static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n - 1);
    }

    public static void main(String[] args) {
        System.out.println("Factorial Iterative (10) : " + factorialIterative(10));
        System.out.println("Factorial Recursive (10) : " + factorialRecursive(10));
        System.out.println("Factorial Stream (10) : " + factorialStream(10));
        System.out.println("Factorial Tail Recursive (10) : " + factorialTailRecursive(10));
    }
}
