package ch06_concurrency.exec;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.Stream;

/**
 * 누적 요소들의 최댓값 또는 최솟값을 계산하는데 LongAccumulator를 사용하라.
 * -> 마음대로 해석해보자면, "random 숫자의 스트림이 있을 때 이를 multi thread로 돌면서
 * 최대값과 최소값을 구해보라."는 뜻으로 보인다.
 */
public class Exec04 {
    public static void main(String[] args) {
        LongAccumulator minAccumulator = new LongAccumulator(Math::min, Long.MAX_VALUE);
        LongAccumulator maxAccumulator = new LongAccumulator(Math::max, Long.MIN_VALUE);


        Stream.generate(() -> ThreadLocalRandom.current().nextLong()).limit(100000000)
            .parallel().forEach(randomLong -> {
            minAccumulator.accumulate(randomLong);
            maxAccumulator.accumulate(randomLong);
        });

        System.out.println("Min : " + minAccumulator.get() + ", Max : " + maxAccumulator.get());
    }
}
