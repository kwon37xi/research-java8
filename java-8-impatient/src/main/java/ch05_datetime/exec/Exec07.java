package ch05_datetime.exec;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.of;

public class Exec07 {
    public static void main(String[] args) {
        printOverlap(new TimeInterval(of(2016,1,1,11,30), of(2016,1,1,12,0)), new TimeInterval(of(2016,1,1,11,59), of(2016,1,1,13,0)));
        printOverlap(new TimeInterval(of(2016,1,1,11,30), of(2016,1,1,12,0)), new TimeInterval(of(2016,1,1,12,1), of(2016,1,1,13,0)));

        printOverlap(new TimeInterval(of(2016,1,1,11,59), of(2016,1,1,13,0)), new TimeInterval(of(2016,1,1,11,30), of(2016,1,1,12,0)));
        printOverlap(new TimeInterval(of(2016,1,1,12,1), of(2016,1,1,13,0)), new TimeInterval(of(2016,1,1,11,30), of(2016,1,1,12,0)));
    }

    public static void printOverlap(TimeInterval timeInterval1, TimeInterval timeInterval2) {
        System.out.println(timeInterval1 + " overlaps " + timeInterval2 + "is " + timeInterval1.overlap(timeInterval2));
    }

    /**
     * 캘린더 이벤트 같은 두 시간의 간격(시작시간 종료시간)을 나타내는 클래스.
     * 두 TimeInterval 이 겹치는지 확인하는 코드 작성할 것.
     */
    public static class TimeInterval {
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;

        public TimeInterval(LocalDateTime startDateTime, LocalDateTime endDateTime) {
            if (startDateTime.isEqual(endDateTime) || startDateTime.isAfter(endDateTime)) {
                throw new IllegalArgumentException("startDateTime must be earlier than endDateTime.");
            }

            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
        }

        public LocalDateTime getStartDateTime() {
            return startDateTime;
        }

        public LocalDateTime getEndDateTime() {
            return endDateTime;
        }

        public boolean overlap(TimeInterval otherTimeInterval) {
            final LocalDateTime otherStartDateTime = otherTimeInterval.getStartDateTime();
            final LocalDateTime otherEndDateTime = otherTimeInterval.getEndDateTime();

            if ((otherStartDateTime.isEqual(getStartDateTime()) || otherStartDateTime.isAfter(getStartDateTime()))
                && (otherStartDateTime.isEqual(getEndDateTime()) || otherStartDateTime.isBefore(getEndDateTime()))) {
                return true;
            }

            if ((getStartDateTime().isEqual(otherStartDateTime) || getStartDateTime().isAfter(otherStartDateTime))
                && (getStartDateTime().isEqual(otherEndDateTime) || getStartDateTime().isBefore(otherEndDateTime))) {
                return true;
            }

            return false;
        }

        @Override
        public String toString() {
            return "TimeInterval{" +
                "startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                '}';
        }
    }
}
