package ch05_datetime.exec;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Los Angeles 에서 3:05 PM 에 출발, 10시간 50분 걸려서 프랑크푸르트 도착.
 * 도착지에서의 도착 시간은?
 */
public class Exec10 {
    public static void main(String[] args) {
        // LA Timezone : America/Los_Angeles
        ZonedDateTime departure = ZonedDateTime.of(2016,1,1, 15, 5, 0, 0, ZoneId.of("America/Los_Angeles"));
        final ZonedDateTime arrival = departure.plusHours(10).plusMinutes(50);
        final ZonedDateTime arrivalWithCet = arrival.withZoneSameInstant(ZoneId.of("CET")); // Frankfrut는 CET 사용. Central European Time
        System.out.println(arrivalWithCet);
    }
}
