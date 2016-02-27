package ch05_datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * API 설계자들은 절대 시간 인스턴스를 표현하려는 경우 외에는 구역(zoned) 시간을 사용하지 않도록 권장한다.
 */
public class Lecture02 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate alonzosBirthday = LocalDate.of(1903, 6, 14);
        // or
        alonzosBirthday = LocalDate.of(1903, Month.JUNE, 14);
        System.out.println("today : " + today);
        System.out.println("alonzosBirthday : " + alonzosBirthday);

        LocalDate programmersDay = LocalDate.of(2016, Month.JANUARY, 1).plusDays(255); // 매년 256번째날
        System.out.println("Programmers day : " + programmersDay);

        System.out.println("Programmers day next year : " + programmersDay.plus(Period.ofYears(1)));

        final LocalDate christmas = LocalDate.of(today.getYear(), 12, 25);
        final Period untilChristmas = today.until(christmas);
        // 5개월 20일 남았다면 Period.getMonths() 는 5를, getDays()는 20을 리턴한다.
        // 정확한 날짜수를 원한다면 until 시에 ChronoUnit 지정필요.
        System.out.println("to christmas : " + today.until(christmas, ChronoUnit.DAYS));

        System.out.println(LocalDate.of(2016, 1, 31).plusMonths(1));
        System.out.println(LocalDate.of(2016, 3, 31).minusMonths(1));

        System.out.println("DayOfWeek of 1900/01/01 : " + LocalDate.of(1900, 1, 1).getDayOfWeek().getValue());
        System.out.println(DayOfWeek.SATURDAY.plus(3));
    }
}
