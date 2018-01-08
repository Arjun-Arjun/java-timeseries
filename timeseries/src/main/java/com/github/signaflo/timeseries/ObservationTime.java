package com.github.signaflo.timeseries;

import java.time.OffsetDateTime;

/**
 * [Insert class description]
 *
 * @author Jacob Rachiele
 * Dec. 29, 2017
 */
public class ObservationTime {

    private final OffsetDateTime dateTime;

    static ObservationTime from(OffsetDateTime dateTime) {
        return new ObservationTime(dateTime);
    }

    ObservationTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    ObservationTime plus(TimePeriod timePeriod) {
        return new ObservationTime(this.dateTime.plus(timePeriod.unitLength(), timePeriod.timeUnit().temporalUnit()));
    }
}
