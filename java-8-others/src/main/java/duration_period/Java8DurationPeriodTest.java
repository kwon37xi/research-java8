package duration_period;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * <a href="https://www.tutorialspoint.com/java8/java8_periodduration.htm">Java 8 Duration/Period</a>
 *
 * Period : 날짜 기반 시간의 양
 * Duration : 시간 기반 시간의 양
 */
public class Java8DurationPeriodTest {
    public static void main(String[] args) {
        testPeriod();
        testDuration();
    }

    private static void testPeriod() {
        LocalDate date1 = LocalDate.now();
        System.out.println("Current date: " +  date1);

        LocalDate date2 = date1.plus(1, ChronoUnit.MONTHS);
        System.out.println("Next month: " + date2);

        Period period = Period.between(date2, date1);
        System.out.println("Period: " + period + ", " + period.getMonths());
    }

    private static void testDuration() {
        LocalTime time1 = LocalTime.now();
        Duration twoHours = Duration.ofHours(2);

        LocalTime time2 = time1.plus(twoHours);

        Duration duration = Duration.between(time1, time2);
        System.out.println("Duration: " + duration + ", " + duration.getSeconds());

        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = localDateTime1.plusDays(2).plusHours(3).plusMinutes(7).plusSeconds(31);

        Duration dateTimeDuration = Duration.between(localDateTime1, localDateTime2);
        System.out.println("LocalDateTime duration : " + dateTimeDuration + ", " + dateTimeDuration.getSeconds());
    }
}
