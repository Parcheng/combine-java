package com.parch.combine.core.common.util.typeof;

import java.util.List;

public class ClassTypeUtil {

    public static <R> R execute(Object data, List<IClassTypeExecute<R>> executes) {
        for (IClassTypeExecute<R> execute : executes) {
            if (!execute.instanceOf(data)) {
                continue;
            }

            return execute.execute(data);
        }

        return null;
    }

}
