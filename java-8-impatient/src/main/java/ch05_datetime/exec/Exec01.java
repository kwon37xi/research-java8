package ch05_datetime.exec;

import java.time.LocalDate;

/**
 * plusDays 없이 프로그래머의 날(매년 256번째 날)을 계산해보라.
 */
public class Exec01 {
    public static void main(String[] args) {
        LocalDate firstDayOf2016 = LocalDate.of(2016, 1, 1);
        System.out.println("2016 Programmer's day: " + calculateProgrammersDay(firstDayOf2016));

        LocalDate firstDayOf2015 = LocalDate.of(2015, 1, 1);
        System.out.println("2015 Programmer's day : " + calculateProgrammersDay(firstDayOf2015));

        // plusDays 대신 plus(Period)를 사용할 수도 있다.
    }

    // 프로그래머의 날은 항상 평년 9월 13일, 윤년 9월 12일이다.
    public static LocalDate calculateProgrammersDay(LocalDate date) {
        if (date.isLeapYear()) {
            return LocalDate.of(date.getYear(), 9, 12);
        }
        return LocalDate.of(date.getYear(), 9, 13);
    }
}
