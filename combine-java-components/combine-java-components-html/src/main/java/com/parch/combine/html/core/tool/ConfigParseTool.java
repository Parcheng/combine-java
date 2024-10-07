package com.parch.combine.html.core.tool;

import com.parch.combine.core.common.util.PrintLogUtil;
import com.parch.combine.html.core.canstant.ConfigFiledConstant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ConfigParseTool {

    private ConfigParseTool() {}

    public static Map<String, Object> parseInterfaceToMap(String id, String type, Object interfaceObj) {
        Map<String, Object> config = new HashMap<>();

        Method[] methods = interfaceObj.getClass().getMethods();
        for (Method method : methods) {
            Object value = null;
            try {
                value = method.invoke(interfaceObj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                PrintLogUtil.printError(e);
            }
            config.put(method.getName(), value);
        }

        config.put(ConfigFiledConstant.ID, id);
        config.put(ConfigFiledConstant.TYPE, type);

        return config;
    }
}
