package com.github.signaflo.timeseries;

import lombok.NonNull;

/**
 * An observation of a numerical event.
 *
 * @author Jacob Rachiele
 * Nov. 12, 2017
 */
public class Observation {

    private final double value;
    private final Time observationTime;

    /**
     * Create a new observation with the value observed and the observation time.
     *
     * @param observationTime the time at which the value was observed.
     * @param value the value observed.
     */
    public Observation(@NonNull Time observationTime, double value) {
        this.value = value;
        this.observationTime = observationTime;
    }

    public double getValue() {
        return this.value;
    }

    public Time getObservationTime() {
        return this.observationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Observation that = (Observation) o;

        if (value != that.value) return false;
        return observationTime.equals(that.observationTime);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.hashCode(value);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + observationTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return Double.toString(value) + " at " + this.observationTime.toString();
    }
}
