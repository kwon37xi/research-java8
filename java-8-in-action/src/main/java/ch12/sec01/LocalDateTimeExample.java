package ch12.sec01;

import java.time.*;
import java.time.temporal.ChronoField;

public class LocalDateTimeExample {
    public static void main(String[] args) {
//        LocalDate date = LocalDate.of(2014, 3, 18);
        LocalDate date = LocalDate.parse("2014-03-18");
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();

        System.out.printf("%s year %s month %d day(%s) length of month - %d, leapYear %s%n", year, month.toString(), day, dow.name(), len, leap);
        System.out.printf("%d년 %d월 %d일%n", date.get(ChronoField.YEAR), date.get(ChronoField.MONTH_OF_YEAR), date.get(ChronoField.DAY_OF_MONTH));

//        LocalTime time = LocalTime.of(13, 45, 20);
        LocalTime time = LocalTime.parse("13:45:20");
        System.out.printf("%d시 %d분 %d초%n", time.getHour(), time.getMinute(), time.getSecond());

        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        System.out.printf("all dt? are the same? : %s%n", (dt1.equals(dt2) && dt2.equals(dt3) && dt3.equals(dt4) && dt4.equals(dt5)));

        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();

        System.out.printf("date.equals(date1) ? %s, time.equals(time1) ? %s%n", date.equals(date1), time.equals(time1));

        System.out.printf("All same instant? %s, %s, %s, %s%n",
            Instant.ofEpochMilli(3),
            Instant.ofEpochSecond(3, 0),
            Instant.ofEpochSecond(2, 1_000_000_000),
            Instant.ofEpochSecond(4, -1_000_000_000)
        );
    }
}
