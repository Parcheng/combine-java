package com.parch.combine.core.common.util;

import cn.hutool.core.date.DateUtil;
import com.parch.combine.core.common.canstant.SymbolConstant;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * 数据解析工具类
 */
public class DataParseUtil {

    public static Object parse(Object data, Class<?> typeClass) {
        if (data == null) {
            return null;
        }

        try {
            if (typeClass.isInstance(data)) {
                return data;
            } else if (String.class.equals(typeClass)) {
                return getString(data);
            } else if (Double.class.equals(typeClass)) {
                return Double.parseDouble(data.toString());
            } else if (Float.class.equals(typeClass)) {
                return Float.parseFloat(data.toString());
            } else if (Long.class.equals(typeClass)) {
                return Long.parseLong(data.toString());
            } else if (Integer.class.equals(typeClass)) {
                return Integer.parseInt(data.toString());
            } else if (Short.class.equals(typeClass)) {
                return Short.parseShort(data.toString());
            } else if (Date.class.equals(typeClass)) {
                return parseDate(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getString(Object data) {
        return getString(data, null);
    }

    public static String getString(Object data, String defaultValue) {
        if (data == null) {
            return defaultValue;
        }

        if (data instanceof Map || data instanceof Collection) {
            return JsonUtil.serialize(data);
        } else {
            return data.toString();
        }
    }

    public static Integer getInteger(Object data, Integer defaultValue) {
        if (!DataTypeIsUtil.isInteger(data)) {
            return defaultValue;
        }

        return Integer.parseInt(data.toString());
    }

    public static Long getLong(Object data, Long defaultValue) {
        if (!DataTypeIsUtil.isLong(data)) {
            return defaultValue;
        }

        return Long.parseLong(data.toString());
    }

    public static Double getDouble(Object data, Double defaultValue) {
        if (!DataTypeIsUtil.isDouble(data)) {
            return defaultValue;
        }

        return Double.parseDouble(data.toString());
    }

    /**
     * 解析Date数据
     *
     * @param data 数据
     * @return 数据
     */
    public static Date parseDate(Object data) {
        if (data != null) {
            try {
                return DateUtil.parse(data.toString());
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    /**
     * 解析数字
     *
     * @param data 数据
     * @return 数据
     */
    public static Object parseNumber(Object data) {
        if (data == null) {
            return null;
        }

        if (data instanceof Double || data instanceof Float ||
                data instanceof Long || data instanceof Integer || data instanceof Short) {
            return data;
        }

        String dataStr = data.toString();
        if (DataTypeIsUtil.isNumber(dataStr)) {
            if (dataStr.contains(SymbolConstant.DOT)) {
                return Double.parseDouble(dataStr);
            } else {
                return Long.parseLong(dataStr);
            }
        }

        return null;
    }

    /**
     * 解析布尔
     *
     * @param data 数据
     * @return 数据
     */
    public static Object parseBoolean(Object data) {
        if (data == null) {
            return null;
        }

        if (data instanceof Boolean) {
            return data;
        }

        if (data instanceof Double || data instanceof Float ||
                data instanceof Long || data instanceof Integer || data instanceof Short) {
            return ((Number) data).doubleValue() > 0;
        }

        return data.toString().equalsIgnoreCase("true");
    }
}
