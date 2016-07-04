package ch06.sec05;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * 소수(prime number) collector 구현
 */
public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>>  {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{
            put(Boolean.TRUE, new ArrayList<>());
            put(Boolean.FALSE, new ArrayList<>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (acc, candidate) -> acc.get(isPrime(acc.get(Boolean.TRUE), candidate)).add(candidate);
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        // 병렬실행 불가이므로 실제로는 호출될 수 없음.
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(Boolean.TRUE).addAll(map2.get(Boolean.TRUE));
            map1.get(Boolean.FALSE).addAll(map2.get(Boolean.FALSE));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        // 병렬 실행불가
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public static boolean isPrime(List<Integer> primes, int candidate) {

        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
            .stream()
            .noneMatch(primeNumber -> candidate % primeNumber == 0);
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    public static void main(String[] args) {
        final Map<Boolean, List<Integer>> primesAndNonPrimes = IntStream.rangeClosed(2, 100).boxed().collect(new PrimeNumbersCollector());

        System.out.println("Primes  : " + primesAndNonPrimes.get(Boolean.TRUE));
        System.out.println("Non Primes  : " + primesAndNonPrimes.get(Boolean.FALSE));
    }
}
