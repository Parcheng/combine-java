package com.parch.combine.common.util;

import cn.hutool.core.date.DateUtil;
import com.parch.combine.common.constant.SymbolConstant;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据解析工具类
 */
public class DataParseUtil {

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

        if (data.toString().contains(SymbolConstant.DOT)) {
            return Double.parseDouble(data.toString());
        } else {
            return Long.parseLong(data.toString());
        }
    }
}
