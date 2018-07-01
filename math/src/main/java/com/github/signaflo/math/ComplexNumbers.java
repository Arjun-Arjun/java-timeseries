package com.github.signaflo.math;

public class ComplexNumbers implements Field<Complex> {

    private final Complex zero = Complex.zero();
    private final Complex one = new Complex(1.0);

    private ComplexNumbers() {}

    @Override
    public Complex additiveIdentity() {
        return zero;
    }

    @Override
    public Complex multiplicativeIdentity() {
        return one;
    }

    private static class ComplexNumbersHolder {
        private static ComplexNumbers INSTANCE = new ComplexNumbers();
    }

    static ComplexNumbers getInstance() {
        return ComplexNumbersHolder.INSTANCE;
    }
}
