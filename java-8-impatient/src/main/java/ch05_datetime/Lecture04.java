package ch05_datetime;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Lecture04 {
    public static void main(String[] args) {
        LocalTime rightNow = LocalTime.now();

        System.out.println("rightNow : " + rightNow);

        LocalTime bedTime = LocalTime.of(22, 30, 0);
        System.out.println("bedTime : " + bedTime);

        LocalTime wakeup = bedTime.plusHours(8);
        System.out.println("wakeup time : " + wakeup);

        LocalDateTime now = LocalDateTime.now();
        System.out.println("LocalDateTime now : " + now);
    }
}
