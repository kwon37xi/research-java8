package ch05_datetime.exec;

import java.time.Instant;
import java.time.ZoneId;

public class Exec08 {
    public static void main(String[] args) {
        Instant now = Instant.now();

        ZoneId.getAvailableZoneIds().stream()
            .forEach(zoneId -> {
                System.out.printf("Zone : %s's offset is %s.%n", zoneId, ZoneId.of(zoneId).getRules().getOffset(now));
            });
    }
}
