package com.github.signaflo.math;

public interface Field<T extends FieldElement<T>> {

    T additiveIdentity();
    T multiplicativeIdentity();
}
