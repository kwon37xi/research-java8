package ch05_datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class Lecture03 {
    public static void main(String[] args) {
        // 특정 월의 첫번째 수요일
        LocalDate firstTuesday = LocalDate.of(2016, 3, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));
        System.out.println("2016년 3월 첫번째 수요일 : " + firstTuesday);
//
//        TemporalAdjuster NEXT_WORKDAY = w -> {
//            LocalDate result = (LocalDate) w;
//            do {
//                result = result.plusDays(1);
//            } while (result.getDayOfWeek().getValue() >= DayOfWeek.SATURDAY.getValue());
//            return result;
//        };

        TemporalAdjuster NEXT_WORKDAY = TemporalAdjusters.ofDateAdjuster(w -> {
            LocalDate result = w;
            do {
                result = result.plusDays(1);
            } while (result.getDayOfWeek().getValue() >= DayOfWeek.SATURDAY.getValue());
            return result;
        });

        final LocalDate today = LocalDate.now();
        LocalDate nextWorkDay = today.with(NEXT_WORKDAY);
        System.out.println("Today : " + today + ", next work day : " + nextWorkDay);
    }
}
