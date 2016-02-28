package ch05_datetime.exec;

import java.time.LocalDate;

public class Exec02 {
    public static void main(String[] args) {
        LocalDate day = LocalDate.of(2000, 2, 29);
        System.out.println("PlusYears(1) : " + day.plusYears(1)); // 2/28
        System.out.println("PlusYears(4) : " + day.plusYears(4)); // 2/29
        System.out.println("PlusYears(4) : " + day.plusYears(1).plusYears(1).plusYears(1).plusYears(1)); // 2/28
    }
}
