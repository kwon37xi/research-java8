package ch05_datetime.exec;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

public class Exec03 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        final LocalDate nextSunday = today.with(next(w -> w.getDayOfWeek() == DayOfWeek.SUNDAY));
        System.out.println("NextSunday : " + nextSunday);
    }

    public static TemporalAdjuster next(Predicate<LocalDate> localDatePredicate) {
        return TemporalAdjusters.ofDateAdjuster(localDate -> {
            LocalDate result = localDate;
            while (!localDatePredicate.test(result)) {
                result = result.plusDays(1);
            }
            return result;
        });
    }
}
