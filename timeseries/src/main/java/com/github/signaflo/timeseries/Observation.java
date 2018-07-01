package com.github.signaflo.timeseries;

import lombok.NonNull;

import java.time.OffsetDateTime;

/**
 * An observation of a numerical event.
 *
 * @author Jacob Rachiele
 * Nov. 12, 2017
 */
public class Observation {

    private final double value;
    private final OffsetDateTime observationPeriod;

    /**
     * Create a new observation with the value observed and the observation period.
     *
     * @param observationPeriod the period in which the value was observed.
     * @param value the value observed.
     */
    public Observation(@NonNull OffsetDateTime observationPeriod, double value) {
        this.value = value;
        this.observationPeriod = observationPeriod;
    }

    public double getValue() {
        return this.value;
    }

    public OffsetDateTime getObservationPeriod() {
        return this.observationPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Observation that = (Observation) o;

        if (value != that.value) return false;
        return observationPeriod.equals(that.observationPeriod);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.hashCode(value);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + observationPeriod.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return Double.toString(value) + " at " + this.observationPeriod.toString();
    }
}
