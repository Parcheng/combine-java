package com.parch.combine.core.common.util.typeof;

import java.util.Map;

public abstract class ConfigMapClassTypeExecute<R> implements IClassTypeExecute<R>{

    @Override
    public boolean instanceOf(Object data) {
        return data instanceof Map;
    }

    @Override
    @SuppressWarnings("unchecked")
    public R execute(Object data) {
        return executeFunction((Map<String, Object>) data);
    }

    protected abstract R executeFunction(Map<String, Object> data);
}
