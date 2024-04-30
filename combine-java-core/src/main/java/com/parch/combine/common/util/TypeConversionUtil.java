package com.parch.combine.common.util;

/**
 * 类型转换工具类
 */
public class TypeConversionUtil {

    public static Integer parseInt(Object data, Integer defaultInt) {
        if (data == null) {
            return defaultInt;
        }

        try {
            return Integer.parseInt(data.toString());
        } catch (Exception e) {
            return defaultInt;
        }
    }

    public static <T> T parseJava(Object data, Class<T> clazz) {
        String dataString = JsonUtil.serialize(data);
        return JsonUtil.deserialize(dataString, clazz);
    }
}
