package temporaladjusters;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CustomTemporalAdjusterExample {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println("current date : " + localDate);

        localDate = localDate.with(new NextChristmas());
        System.out.println("Next XMas : " + localDate);

        localDate = localDate.with(new NextChristmas());
        System.out.println("Next XMas of the next XMas : " + localDate);

        localDate = localDate.plus(1, ChronoUnit.DAYS);
        localDate = localDate.with(new NextChristmas());
        System.out.println("Next XMas of the next XMas + 1 day : " + localDate);
    }
}
