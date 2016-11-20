package temporaladjusters;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

// http://www.mkyong.com/java8/java-8-temporaladjusters-examples/
public class TemporalAdjustersEaxmple {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println("current date : " + localDate);

        LocalDate with = localDate.with(TemporalAdjusters.firstDayOfMonth()); // 해당월의 첫째날
        System.out.println("firstDayOfMonth : " + with);

        LocalDate with1 = localDate.with(TemporalAdjusters.lastDayOfMonth()); // 해당월의 마지막날
        System.out.println("lastDayOfMonth : " + with1);

        LocalDate with2 = localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); // 다음 월요일은?
        System.out.println("next monday : " + with2);

        LocalDate with3 = localDate.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("firstDayOfNextMonth : " + with3);
    }
}
