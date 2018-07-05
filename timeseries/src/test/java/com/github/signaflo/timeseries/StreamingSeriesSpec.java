package com.github.signaflo.timeseries;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StreamingSeriesSpec {

    private Time start = Time.builder().build();
    private double x= 1.0;
    private List<Observation> series = new ArrayList<>();

    {
        Time startTime = start;
        for (int i = 0; i < 10; i++) {
            series.add(new Observation(startTime, x));
            startTime = startTime.plus(TimePeriod.oneYear());
            x += 1.0;
        }
    }

    @Test
    public void testSeries() {
        StreamingSeries streamingSeries = new StreamingSeries(series.stream());
        Time startTime = start.plus(TimePeriod.from(TimeUnit.YEAR, 3L));
        Time endTime = start.plus(TimePeriod.from(TimeUnit.YEAR, 7L));
        FixedSeries fixedSeries = streamingSeries.from(startTime, endTime);
        System.out.println(fixedSeries.sum());
    }
}
