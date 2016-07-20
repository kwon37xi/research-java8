package ch12.sec03;

import java.time.*;
import java.time.chrono.*;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.TimeZone;

public class TimeZoneExample {
    public static void main(String[] args) {
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        System.out.println("romeZone : " + romeZone);

        System.out.println("TimeZone to ZoneId : " + TimeZone.getDefault().toZoneId());

        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        System.out.println("date.atStartOfDay(romeZone) : " + zdt1);

        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);
        System.out.println("dateTime.atZone(romeZone) : " + zdt2);

        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);
        System.out.println("instant.atZone(romeZone) 현재의 로마 시간이 나옴 : " + zdt3);

        LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, romeZone);
        System.out.println("timeFromInstant (로마 현재시각) : " + timeFromInstant);

        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");

        OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dateTime, newYorkOffset);
        System.out.println("DateTimeInNewYrok : " + dateTimeInNewYork);

        JapaneseDate japaneseDate = JapaneseDate.from(date);
        System.out.println("japaneseDate : " + japaneseDate);

        Chronology japaneseChronology = Chronology.ofLocale(Locale.JAPAN);
        ChronoLocalDate japaneseChronologyNow = japaneseChronology.dateNow();
        System.out.println("japaneseChronology.dateNow() : " + japaneseChronologyNow);

        HijrahDate ramadanDate = HijrahDate.now().with(ChronoField.DAY_OF_MONTH, 1)
            .with(ChronoField.MONTH_OF_YEAR, 9);

        System.out.println("Ramadan starts on " +
            IsoChronology.INSTANCE.date(ramadanDate) +
            " and ends on " +
            IsoChronology.INSTANCE.date(ramadanDate.with(TemporalAdjusters.lastDayOfMonth())));

    }
}
