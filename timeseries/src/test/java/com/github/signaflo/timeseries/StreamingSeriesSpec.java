package com.github.signaflo.timeseries;

import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class StreamingSeriesSpec {

    private OffsetDateTime start = OffsetDateTime.of(
            2000, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
    private double x= 1.0;
    private List<Observation> series = new ArrayList<>();

    {
        OffsetDateTime startTime = start;
        for (int i = 0; i < 10; i++) {
            series.add(new Observation(startTime, x));
            startTime = startTime.plusYears(1L);
            x += 1.0;
        }
    }

    @Test
    public void testSeries() {
        StreamingSeries streamingSeries = new StreamingSeries(series.stream());
        OffsetDateTime startTime = start.plusYears(3L);
        OffsetDateTime endTime = start.plusYears(7L);
        FiniteSeries finiteSeries = streamingSeries.from(startTime, endTime);
        System.out.println(finiteSeries.sum());
    }
}
