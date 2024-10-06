package com.parch.combine.html.core.tool;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.PrintLogUtil;
import com.parch.combine.html.core.canstant.ConfigFiledConstant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConfigParseTool {

    private ConfigParseTool() {}

    public static Map<String, Object> parseInterfaceToMap(Object interfaceObj) {
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

        Object id = config.get(ConfigFiledConstant.ID);
        if (id == null || CheckEmptyUtil.isEmpty(id.toString())) {
            config.put(ConfigFiledConstant.ID, UUID.randomUUID().toString());
        }

        return config;
    }
}
