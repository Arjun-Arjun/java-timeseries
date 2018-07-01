package com.github.signaflo.timeseries;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class FiniteSeries {

    private final List<Observation> series;

    FiniteSeries(List<Observation> series) {
        this.series = ImmutableList.copyOf(series);
    }

    double sum() {
        double result = 0.0;
        for (Observation obs : series) {
            result += obs.getValue();
        }
        return result;
    }

}
