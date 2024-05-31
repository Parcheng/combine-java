package com.parch.combine.core.component.a;

import java.lang.reflect.Proxy;
import java.util.Map;

public class ConfigProxyBuilder {

    @SuppressWarnings("unchecked")
    public static <T> T builder(String scopeKey, Class<T> configClass, Map<String, Object> config) {
        return (T) Proxy.newProxyInstance(
                ConfigProxyBuilder.class.getClassLoader(),
                new Class[]{configClass},
                new LogicConfigProxy(scopeKey, configClass, config));
    }
}
