package com.github.signaflo.timeseries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamingSeries  {

    private final static Logger logger = LoggerFactory.getLogger(StreamingSeries.class);

    private final Stream<Observation> series;

    StreamingSeries(Stream<Observation> series) {
        this.series = series;
    }

    FiniteSeries from(OffsetDateTime start, OffsetDateTime end) {
        Predicate<Observation> selection = (Observation o) -> {
            OffsetDateTime time = o.getObservationPeriod();
            return time.equals(start) || (time.isAfter(start) &&
                    time.isBefore(end) ) || time.equals(end);
        };
        return new FiniteSeries(series.filter(selection).collect(Collectors.toList()));
    }
}
