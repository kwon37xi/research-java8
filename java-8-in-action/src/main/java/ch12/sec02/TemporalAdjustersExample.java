package ch12.sec02;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.*;

import static java.time.temporal.TemporalAdjusters.*;

public class TemporalAdjustersExample {
    public static void main(String[] args) {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY)); // 이번 혹은 차주 일요일 2014/03/23
        System.out.println("이번 혹은 차주 일요일 : " + date2);

        LocalDate date3 = date2.with(lastDayOfMonth()); // 이번달 마지막날
        System.out.println("2014년 3월 마지막 날 : " + date3);

        quiz_12_02();
    }

    public static void quiz_12_02() {
        LocalDate date = LocalDate.of(2016, 07, 15);
//        final LocalDate nextWorkingDay = date.with(new NextWorkingDay());
        LocalDate nextWorkingDay = date.with(TemporalAdjusters.ofDateAdjuster(
            temporal -> {
                DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

                int dayToAdd = 1;
                if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
                else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
                return temporal.plus(dayToAdd, ChronoUnit.DAYS);
            }
        ));
        System.out.println("Quoz 12-02 next working day of 2016/07/15 : " + nextWorkingDay);
    }

    /** 다음 업무일. 토요일 일요일을 건너뛰기 */
    public static class NextWorkingDay implements TemporalAdjuster {

        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }
}
