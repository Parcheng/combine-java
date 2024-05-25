package com.parch.combine.core.common.util.typeof;

public interface IClassTypeExecute<R> {

    boolean instanceOf(Object data);

    R execute(Object data);
}
