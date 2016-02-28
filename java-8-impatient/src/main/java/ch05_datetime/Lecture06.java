package ch05_datetime;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Lecture06 {
    public static void main(String[] args) {
        ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0, ZoneId.of("America/New_York"));

        String formatted = DateTimeFormatter.ISO_DATE_TIME.format(apollo11launch);
        System.out.println(formatted);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        System.out.println(formatter.format(apollo11launch)); //1969년 7월 16일 (수) 오전 9시 32분 00초

        System.out.println("French : " + formatter.withLocale(Locale.FRENCH).format(apollo11launch)); // 16 juillet 1969 09:32:00 EDT

        formatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
        System.out.println("With custom formatter : " + formatter.format(apollo11launch)); //수 1969-07-16 09:32

        LocalDate churchsBirthday = LocalDate.parse("1903-06-14"); // ISO_LOCAL_DATE
        System.out.println("churchsBirthday : " + churchsBirthday);

        System.out.println("Apollo11 launch with text : " + ZonedDateTime.parse("1969-07-16 03:32:00-0400",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx")));
    }
}
