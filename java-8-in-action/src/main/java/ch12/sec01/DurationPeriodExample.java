package ch12.sec01;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class DurationPeriodExample {
    public static void main(String[] args) {
        System.out.println("Duration 3 minutes : "+ Duration.between(LocalTime.parse("13:31:00"), LocalTime.parse("13:34:00")));
        System.out.println("Duration 1 hour : "+ Duration.between(LocalDateTime.of(2014, 8, 21, 13, 31, 44), LocalDateTime.of(2014, 8, 21, 14, 31, 44)));
        System.out.println("Duration 24 hours with instant : "+ Duration.between(Instant.ofEpochMilli(0), Instant.ofEpochSecond(60 * 60 * 24)));

        Duration.ofMinutes(3);
        Duration.of(3, ChronoUnit.MINUTES);

        Period tenDays = Period.between(LocalDate.of(2014, 3, 8), LocalDate.of(2014, 3, 18));
        System.out.println("Period ten days : " + tenDays);
        Period.ofDays(10);
        Period.ofWeeks(3);
        Period.of(2, 6, 1); // year , month, day
    }
}
