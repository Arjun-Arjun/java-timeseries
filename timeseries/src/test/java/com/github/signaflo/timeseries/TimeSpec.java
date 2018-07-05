package com.github.signaflo.timeseries;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class TimeSpec {

    private final Time.Builder timeBuilder = Time.builder();

    @Test
    public void timeClassImplementsComparable() {
        Time earlier = Time.year(2015);
        Time later = Time.year(2020);
        Time later2 = Time.year(2020);
        assertThat(earlier.compareTo(later), is(lessThan(0)));
        assertThat(later.compareTo(later2), is(0));
        assertThat(later.compareTo(earlier), is(greaterThan(0)));
    }

    @Test
    public void toStringGivesExpectedOutput() {
        String expected = "Time: " + "0001-01-01T00:00Z";
        assertThat(timeBuilder.build().toString(), is(expected));
    }

    @Test
    public void testEqualAndHashCode() {
        Time time1 = timeBuilder.build();
        Time time2 = timeBuilder.build();
        Time time3 = timeBuilder.setYear(2020).build();
        assertThat(time1, is(time2));
        assertThat(time1.hashCode(), is(time2.hashCode()));
        assertThat(time1, is(not(time3)));
    }
}
