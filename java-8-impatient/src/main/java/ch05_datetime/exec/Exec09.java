package ch05_datetime.exec;

import java.time.Instant;
import java.time.ZoneId;
import java.util.stream.Collectors;

/**
 * Offset이 정각이다? : Offset이 09:00 형태로 이뤄지는데 이게 시간으로 떨어지지 않고
 * 09:30 처럼 분이 존재하는 경우를 의미하는 것으로 보임.
 */
public class Exec09 {
    public static void main(String[] args) {
        Instant now = Instant.now();

        ZoneId.getAvailableZoneIds().stream()
            .filter(zoneId -> ZoneId.of(zoneId).getRules().getOffset(now).getTotalSeconds() % 3600 != 0)
            .collect(Collectors.toSet())
            .forEach(System.out::println);

    }
}
