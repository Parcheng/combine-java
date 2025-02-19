package com.parch.combine.core.common.util.tuple;

public class TwoTuples<F, S> {

    private final F first;
    private final S second;

    public TwoTuples(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}