/*
 * Copyright (c) 2017 Jacob Rachiele
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to
 * do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * Contributors:
 *
 * Jacob Rachiele
 */

package com.github.signaflo.data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import com.github.signaflo.timeseries.TimeSeries;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.signaflo.timeseries.Ts;

public class DataSetSpec {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  @SuppressWarnings({"unused", "ConstantConditions"})
  public void whenDataSetCreatedWithNullArrayThenExceptionThrown() {
    double[] data = null;
    exception.expect(NullPointerException.class);
    new DoubleDataSet(data);
  }

  @Test
  public void whenSumRequestedThenResultCorrect() {
    double[] data = new double[] { 3.0, 7.0, 10.0 };
    DataSet dataSet = new DoubleDataSet(data);
    double expected = 20.0;
    double actual = dataSet.sum();
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void whenMeanRequestedThenResultCorrect() {
    double[] data = new double[] { 3.0, 5.5, 6.5 };
    DataSet dataSet = new DoubleDataSet(data);
    double expected = 5.0;
    double actual = dataSet.mean();
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void whenMedianRequestedThenResultCorrect() {
    double[] data = new double[] { 5.5, 6.5, 3.0 };
    DataSet dataSet = new DoubleDataSet(data);
    double expected = 5.5;
    double actual = dataSet.median();
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void whenMedianRequestedEvenNumDataPointsThenResultCorrect() {
    double[] data = new double[] { 6.5, 10.0, 3.0, 5.5 };
    DataSet dataSet = new DoubleDataSet(data);
    double expected = 6.0;
    double actual = dataSet.median();
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void whenVarianceRequestedEvenNumDataPointsThenResultCorrect() {
    double[] data = new double[] { 6.5, 10.0, 3.0, 5.5 };
    DataSet dataSet = new DoubleDataSet(data);
    double expected = 8.416667;
    double actual = dataSet.variance();
    assertThat(actual, is(closeTo(expected, 1E-4)));
  }

  @Test
  public void whenStdDeviationRequestedEvenNumDataPointsThenResultCorrect() {
    double[] data = new double[] { 6.5, 10.0, 3.0, 5.5 };
    DataSet dataSet = new DoubleDataSet(data);
    double expected = 2.901149;
    double actual = dataSet.stdDeviation();
    assertThat(actual, is(closeTo(expected, 1E-4)));
  }

  @Test
  public void whenCovarianceRequestedEvenNumDataPointsThenResultCorrect() {
    double[] data1 = new double[] { 6.5, 10.0, 3.0, 5.5 };
    double[] data2 = new double[] { 3.0, 5.0, 7.0, 4.5 };
    DataSet dataSet = new DoubleDataSet(data1);
    DoubleDataSet dataSet2 = new DoubleDataSet(data2);
    double expected = -2.208333;
    double actual = dataSet.covariance(dataSet2);
    assertThat(actual, is(closeTo(expected, 1E-4)));
  }

  @Test
  public void whenCorrelationRequestedEvenNumDataPointsThenResultCorrect() {
    double[] data1 = new double[] { 6.5, 10.0, 3.0, 5.5 };
    double[] data2 = new double[] { 3.0, 5.0, 7.0, 4.5 };
    DataSet dataSet = new DoubleDataSet(data1);
    DoubleDataSet dataSet2 = new DoubleDataSet(data2);
    double expected = -0.4607651;
    double actual = dataSet.correlation(dataSet2);
    assertThat(actual, is(closeTo(expected, 1E-4)));
  }

  @Test
  public void whenLengthRequestedThenResultCorrect() {
    double[] data = new double[] { 5.0, 7.5 };
    DataSet dataSet = new DoubleDataSet(data);
    long expected = 2;
    long actual = dataSet.size();
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void whenTimesOperationCalledThenResultCorrect() {
    double[] data1 = new double[] { 5.0, 7.5 };
    double[] data2 = new double[] { 3.0, 10.0 };
    DataSet dataSet1 = new DoubleDataSet(data1);
    DoubleDataSet dataSet2 = new DoubleDataSet(data2);
    double[] expected = new double[] { 15.0, 75.0 };
    assertThat(dataSet1.times(dataSet2).asArray(), is(equalTo(expected)));
  }

  @Test
  public void whenPlusOperationCalledThenResultCorrect() {
    DataSet dataSet1 = new DoubleDataSet(5.0, 7.5);
    DoubleDataSet dataSet2 = new DoubleDataSet(3.0, 10.0);
    double[] expected = new double[] { 8.0, 17.5 };
    assertThat(dataSet1.plus(dataSet2).asArray(), is(equalTo(expected)));
  }

  @Test
  public void whenSumOfSquaresComputedResultCorrect() {
    DataSet dataSet = new DoubleDataSet(1.0, 2.0, 3.0);
    assertThat(dataSet.sumOfSquares(), is(equalTo(14.0)));
  }

  @Test
  public void whenTwoDataSetsEqualThenEqualsAndHashCodeCorrect() {
    DoubleDataSet dataSet = new DoubleDataSet(1.0, 2.0, 3.0);
    DoubleDataSet dataSet2 = new DoubleDataSet(1.0, 2.0, 3.0);
    assertThat(dataSet.equals(dataSet2), is(true));
    assertThat(dataSet2.equals(dataSet), is(true));
    assertThat(dataSet.hashCode(), is(equalTo(dataSet2.hashCode())));
  }

  @Test
  public void whenTwoDataSetsNotEqualThenEqualsAndHashCodeCorrect() {
    DoubleDataSet dataSet = new DoubleDataSet(1.0, 2.0, 3.0);
    DoubleDataSet dataSet2 = new DoubleDataSet(1.0, 2.0, 3.0, 4.0);
    assertThat(dataSet.equals(dataSet2), is(false));
    assertThat(dataSet2.equals(dataSet), is(false));
    assertThat(dataSet.hashCode(), is(not(equalTo(dataSet2.hashCode()))));
  }

  @Test
  public void whenDataSetNotEqualOtherObjectThenEqualsAndHashCodeCorrect() {
    DoubleDataSet dataSet = new DoubleDataSet(1.0, 2.0, 3.0);
    TimeSeries dataSet2 = Ts.newAnnualSeries(2011, 3.5, 7.4, 8.8);
    assertThat(dataSet.equals(dataSet2), is(false));
    assertThat(dataSet2.equals(dataSet), is(false));
    assertThat(dataSet.hashCode(), is(not(equalTo(dataSet2.hashCode()))));
  }
  
  @Test
  public void whenDataSetEqualToNullThenFalse() {
    DoubleDataSet dataSet = new DoubleDataSet(1.0, 2.0, 3.0);
    //noinspection ObjectEqualsNull
    assertThat(dataSet.equals(null), is(false));
  }
}
