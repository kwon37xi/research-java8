package ch05_datetime.exec;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 20세기 모든 13일의 금요일
 * 1901~2000
 */
public class Exec06 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(1901, 1, 13);

        int count = 0;
        while (date.getYear() < 2001) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                System.out.println("Date " + date + " is Friday");
                count++;
            }
            date = date.plusMonths(1);
        }

        System.out.println("Total 13th, Friday in 20th century is " + count + ".");
    }
}
