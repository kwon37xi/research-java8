package ch12.sec02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class DateTimeFormatterExample {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2014, 3, 18);
        System.out.println("BASIC_ISO_DATE : " + date.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("ISO_LOCAL_DATE : " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));

        System.out.println("parse BASIC_ISO_DATE : " + LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("parse ISO_LOCAL_DATE : " + LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Custom format : " + date.format(formatter));
        System.out.println("Custom format parse : " + LocalDate.parse("18/03/2014", formatter));

//        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);

        DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
            .appendText(ChronoField.DAY_OF_MONTH)
            .appendLiteral(". ")
            .appendText(ChronoField.MONTH_OF_YEAR)
            .appendLiteral(" ")
            .appendText(ChronoField.YEAR)
            .parseCaseInsensitive()
            .toFormatter(Locale.ITALIAN);
        System.out.println("Italian format : " + date.format(italianFormatter));
        System.out.println("Italian format parse : " + LocalDate.parse("18. marzo 2014", italianFormatter));

    }
}
