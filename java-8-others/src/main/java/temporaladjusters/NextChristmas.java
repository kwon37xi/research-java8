package temporaladjusters;

import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextChristmas implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        // 이미 12월 25일이 지났을 경우
        if (temporal.get(ChronoField.MONTH_OF_YEAR) == 12 && temporal.get(ChronoField.DAY_OF_MONTH) >= 25) {
            temporal = temporal.plus(1, ChronoUnit.YEARS);
        }

        // 12월 25일
        return temporal.with(ChronoField.MONTH_OF_YEAR, 12).with(ChronoField.DAY_OF_MONTH, 25);
    }
}
