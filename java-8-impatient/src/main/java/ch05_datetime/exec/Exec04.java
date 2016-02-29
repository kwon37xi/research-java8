package ch05_datetime.exec;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 주말이 마지막에 오는 달력만들기
 */
public class Exec04 {
    public static void main(String[] args) {
        int month = Integer.valueOf(args[0]);
        int year = Integer.valueOf(args[1]);

        LocalDate start = LocalDate.of(year, month, 1);
        final int width = 4;
        for (int i = 1; i < start.getDayOfWeek().getValue(); i++) {
            System.out.printf("%" + width + "s", "");
        }

        while (start.getMonthValue() == month) {
            System.out.printf("%4d", start.getDayOfMonth());
            if (start.getDayOfWeek() == DayOfWeek.SUNDAY) {
                System.out.println();
            }
            start = start.plusDays(1);
        }
    }
}
