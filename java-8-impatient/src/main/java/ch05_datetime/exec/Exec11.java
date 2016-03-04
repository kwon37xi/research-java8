package ch05_datetime.exec;

import java.time.*;

/**
 * 돌아오는 항공기가 프랑크프루트에서 14:05에 출발해서 16:40에 로스엔젤레스에 도착함. 비행시간은?
 */
public class Exec11 {
    public static void main(String[] args) {
        // LA Timezone : America/Los_Angeles
        // Frankfrut : CET

        ZonedDateTime departure = ZonedDateTime.of(LocalDate.of(2016,1,1), LocalTime.of(14,05), ZoneId.of("CET"));
        ZonedDateTime arrival = ZonedDateTime.of(LocalDate.of(2016,1,1), LocalTime.of(16,40), ZoneId.of("America/Los_Angeles"));
        Duration duration = Duration.between(departure, arrival);
        System.out.printf("%d hours %d munites%n", duration.toHours(), duration.toMinutes() % 60);
    }
}
