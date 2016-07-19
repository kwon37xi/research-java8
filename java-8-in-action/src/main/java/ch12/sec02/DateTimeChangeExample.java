package ch12.sec02;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class DateTimeChangeExample {
    public static void main(String[] args) {
        withExample();
        addOrSubtractExample();
        quiz12_01();
    }

    public static void withExample() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.withYear(2011);
        System.out.println("date2 : " + date2);

        LocalDate date3 = date2.withDayOfMonth(25);
        System.out.println("date3 : " + date3);

        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println("date4 : " + date4);

        System.out.println("all same? " + ((date1 == date2) && (date2 == date3) && (date3 == date4)));
    }

    private static void addOrSubtractExample() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.plusWeeks(1);
        System.out.println("date2 plusWeek(1) : " + date2);

        LocalDate date3 = date2.minusYears(3);
        System.out.println("date3 minusYears(3) : " + date3);

        LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS);
        System.out.println("date4 plus(6, ChronoUnit.MONTHS) : " + date4);
    }

    private static void quiz12_01() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(ChronoField.MONTH_OF_YEAR, 9); // to 2014년 9월 18일
        date = date.plusYears(2).minusDays(10); // 2016 년 9월 8일
        date.withYear(2011); // date 변수에 영향을 못 끼침

        System.out.println("quiz 12-01 final date : " + date); // 2016/09/8
    }
}
