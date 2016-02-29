package ch05_datetime.exec;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 지금까지 살아온 날 수 구하기
 */
public class Exec05 {
    public static void main(String[] args) {
        int year = Integer.valueOf(args[0]);
        int month = Integer.valueOf(args[1]);
        int day = Integer.valueOf(args[2]);

        LocalDateTime birthday = LocalDateTime.of(year, month, day, 0, 0);
        LocalDateTime today = LocalDateTime.now();
        final Duration duration = Duration.between(birthday, today);
        System.out.println(duration.toDays());

    }
}
