package com.github.signaflo.timeseries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamingSeries  {

    private final static Logger logger = LoggerFactory.getLogger(StreamingSeries.class);

    private final Stream<Observation> series;

    StreamingSeries(Stream<Observation> series) {
        this.series = series;
    }

    FixedSeries from(Time start, Time end) {
        Predicate<Observation> selection = (Observation o) -> {
            Time time = o.getObservationTime();
            return time.equals(start) || (time.isAfter(start) &&
                    time.isBefore(end) ) || time.equals(end);
        };
        return new FixedSeries(series.filter(selection).collect(Collectors.toList()));
    }

    Optional<Observation> at(Time t) {
        return this.series.filter(observation -> observation.getObservationTime().equals(t)).findAny();
    }

    StreamingSeries add(Observation observation) {
        return new StreamingSeries(Stream.concat(this.series, Stream.of(observation)));
    }
}
