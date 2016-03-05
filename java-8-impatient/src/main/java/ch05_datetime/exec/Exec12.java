package ch05_datetime.exec;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 서로 다른 지역 시간대에서 잡힌 약속들을 읽어들여서
 * 현지 시간대 기준으로 한시간 이내의 약속들 출력한다.
 */
public class Exec12 {
    public static void main(String[] args) {
        ZonedDateTime current = ZonedDateTime.of(2016,3,5, 23, 0, 0, 0, ZoneId.of("Asia/Seoul"));

        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule(2016, 3, 5, 9, 5, ZoneId.of("America/New_York"), "Remote Meeting with NewYork Office"));
        schedules.add(new Schedule(2016, 3, 5, 14, 50, ZoneId.of("Europe/Berlin"), "Remote Meeting with Berlin Office"));
        schedules.add(new Schedule(2016, 3, 5, 16, 50, ZoneId.of("Asia/Istanbul"), "Remote Meeting with Istanbul Office"));

        schedules.stream().filter(schedule -> schedule.isInAnHour(current))
            .forEach(System.out::println);
    }

    public static class Schedule {
        private ZonedDateTime dateTime;
        private String title;

        public Schedule(ZonedDateTime dateTime, String title) {
            this.dateTime = dateTime;
            this.title = title;
        }

        public Schedule(int year, int month, int day, int hour, int minute, ZoneId zoneId, String title) {
            this(ZonedDateTime.of(year, month, day, hour, minute, 0, 0, zoneId), title);
        }

        public boolean isInAnHour(ZonedDateTime current) {
            Duration duration = Duration.between(current, dateTime);
            final long diffMinutes = duration.toMinutes();
            return diffMinutes > 0 && diffMinutes <= 60;
        }

        @Override
        public String toString() {
            return "Schedule{" +
                "dateTime=" + dateTime +
                ", title='" + title + '\'' +
                '}';
        }
    }
}
