package com.github.signaflo.timeseries;

import lombok.NonNull;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * The time something happened.
 */
public class Time implements Comparable<Time> {

    private final OffsetDateTime dateTime;

    private Time(@NonNull OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    boolean isAfter(Time otherTime) {
        return this.compareTo(otherTime) > 0;
    }

    boolean isBefore(Time otherTime) {
        return this.compareTo(otherTime) < 0;
    }

    Time plus(TimePeriod timePeriod) {
        return this.plus(timePeriod.periodLength(), timePeriod.timeUnit());
    }

    private Time plus(long numberOfUnits, TimeUnit unit) {
        return new Time(this.dateTime.plus(numberOfUnits, unit.temporalUnit()));
    }

    static Time now() {
        return new Time(OffsetDateTime.now());
    }

    public static Time year(int year) {
        return builder().setYear(year).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Time: " + this.dateTime.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(dateTime, time.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime);
    }

    @Override
    public int compareTo(@NonNull Time otherTime) {
        return this.dateTime.compareTo(otherTime.dateTime);
    }

    public static class Builder {

        private int year = 1;
        private int month = 1;
        private int day = 1;
        private int hour = 0;
        private int minute = 0;
        private int second = 0;
        private int nanosecond = 0;
        private ZoneOffset offset = ZoneOffset.UTC;

        private Builder() {}

        Builder setYear(int year) {
            this.year = year;
            return this;
        }

        Time build() {
            OffsetDateTime dateTime = OffsetDateTime.of(year, month, day, hour, minute, second, nanosecond, offset);
            return new Time(dateTime);
        }
    }
}
