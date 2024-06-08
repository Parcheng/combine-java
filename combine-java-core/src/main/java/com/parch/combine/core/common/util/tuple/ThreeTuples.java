package com.parch.combine.core.common.util.tuple;

public class ThreeTuples<F, S, T> extends TwoTuples<F, S>{

    private T third;

    public ThreeTuples(F first, S second, T third) {
        super(first, second);
        this.third = third;
    }

    public T getThird() {
        return third;
    }
}