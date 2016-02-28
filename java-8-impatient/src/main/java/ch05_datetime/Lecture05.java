package ch05_datetime;

import java.time.*;

public class Lecture05 {
    public static void main(String[] args) {
        final ZoneId asiaSeoul = ZoneId.of("Asia/Seoul");
        System.out.println("Asia/Seoul : " + asiaSeoul);

        LocalDateTime now = LocalDateTime.now();
        final ZonedDateTime zonedNow = now.atZone(asiaSeoul);
        System.out.println("ZonedNow : " + zonedNow);

        ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0, ZoneId.of("America/New_York"));
        System.out.println("Apollo 11 launch : " + apollo11launch);
        final Instant apollo11launchInstantt = apollo11launch.toInstant();
        System.out.println("Apollo 11 launch instant : " + apollo11launchInstantt);
        System.out.println("Apollo 11 launch in Seoul : " + apollo11launchInstantt.atZone(asiaSeoul));
        System.out.println("Apollo 11 launch in LocalDateTime : " + apollo11launch.toLocalDateTime());

        // Summer Time
        // 중유럽 2013년 3월 31일 2시에 Summertime 시작. 따라서 2시부터 3시 사이의 시간은 존재하지 않음.
        ZonedDateTime skipped = ZonedDateTime.of(2013, 3, 31, 2, 30, 0, 0, ZoneId.of("Europe/Berlin"));
        System.out.println("2013/3/31 2:30 in Berlin : " + skipped); // 3:30 이 생성됨.

        // SummerTime이 끝나는 시점은 반대로 같은 시각을 가리키는 두개의 시간 인스턴스트가 생긴다.
        // 중유럽 2013년 10월 27일 2시에 종료.
        ZonedDateTime ambiguous = ZonedDateTime.of(LocalDate.of(2013, 10, 27), LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        System.out.println("2013/10/27 2:30 in Berlin : " + ambiguous);
        System.out.println("2013/10/27 2:30 + 1 hour in Berlin : " + ambiguous.plusHours(1));

        // Summer Time 경계를 가로질러 날짜를 조정할 때는 Duration이 아니라 Period를 사용해야 한다.
        ZonedDateTime meeting = ZonedDateTime.of(LocalDate.of(2013, 10, 25), LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        System.out.println("With Duration +7 : " + meeting.plus(Duration.ofDays(7))); // 11월 1일 1:30으로 잡힘
        System.out.println("With Period +7 : " + meeting.plus(Period.ofDays(7))); // 11월 1일 2:30으로 잡힘.
    }
}
