package com.github.signaflo.timeseries;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * An
 */
class FixedSeries {

    private final List<Observation> series;

    FixedSeries(List<Observation> series) {
        this.series = ImmutableList.copyOf(series);
    }

    double sum() {
        double result = 0.0;
        for (Observation obs : series) {
            result += obs.getValue();
        }
        return result;
    }

    public static class Builder {

        private double[] observations;
        private Time startTime = Time.builder().build();
        private TimePeriod samplingInterval = TimePeriod.oneYear();

        private Builder(double[] observations) {
            this.observations = observations.clone();
        }

        FixedSeries build() {
            return new FixedSeries(buildObservationList());
        }

        List<Observation> buildObservationList() {
            List<Observation> observationList = new ArrayList<>(this.observations.length);
            Time obsTime = startTime;
            for (double observation : observations) {
                observationList.add(new Observation(obsTime, observation));
                obsTime = obsTime.plus(samplingInterval);
            }
            return observationList;
        }
    }

}
