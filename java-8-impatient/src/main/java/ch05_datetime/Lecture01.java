package ch05_datetime;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Lecture01 {
    public static void main(String[] args) {
        Instant start = Instant.now();
        runAlgorithm();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        long millis = timeElapsed.toMillis();
        System.out.println("runAlgorithm took " + millis + "ms");

        System.out.println("x 10 : " + timeElapsed.multipliedBy(10).toMillis());
        System.out.println("/ 5 : " + timeElapsed.dividedBy(5).toMillis());

        Instant start2 = Instant.now();
        runAlgorithm();
        Instant end2 = Instant.now();
        Duration timeElapsed2 = Duration.between(start2, end2);
        long millis2 = timeElapsed2.toMillis();

        System.out.println("runeAlgorithm2 took " + millis2);
        System.out.println("timeElapsed - timeElapsed2 : " + timeElapsed.minus(timeElapsed2).toMillis() +
            ", isNegative : " + timeElapsed.minus(timeElapsed2).isNegative());

    }

    private static void runAlgorithm() {
        try {
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
