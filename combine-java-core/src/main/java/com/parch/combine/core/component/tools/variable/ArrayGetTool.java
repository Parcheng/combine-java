package com.parch.combine.core.component.tools.variable;

import com.parch.combine.core.common.util.ArrayUtil;
import com.parch.combine.core.common.util.DataParseUtil;

/**
 * 参数数据获取工具类
 */
public class ArrayGetTool {

    public static String[] splitToString(Object[] arr, int startIndex, int endIndex) {
        Object[] splitArr = ArrayUtil.split(arr, Object.class, startIndex, endIndex);
        String[] result = new String[splitArr.length];
        for (int i = 0; i < splitArr.length; i++) {
            result[i] = getString(splitArr, i);
        }

        return result;
    }

    public static String getString(Object[] arr, int index) {
        if (arr == null || arr.length <= index) {
            return null;
        }
        return DataParseUtil.getString(arr[index], null);
    }

    public static String getString(Object[] arr, int index, String defaultValue) {
        if (arr == null || arr.length <= index) {
            return defaultValue;
        }
        return DataParseUtil.getString(arr[index], defaultValue);
    }

    public static Integer getInteger(Object[] arr, int index, Integer defaultValue) {
        if (arr == null || arr.length <= index) {
            return defaultValue;
        }
        return DataParseUtil.getInteger(arr[index], defaultValue);
    }

    public static Long getLong(Object[] arr, int index, Long defaultValue) {
        if (arr == null || arr.length <= index) {
            return defaultValue;
        }
        return DataParseUtil.getLong(arr[index], defaultValue);
    }

    public static Double getDouble(Object[] arr, int index, Double defaultValue) {
        if (arr == null || arr.length <= index) {
            return defaultValue;
        }
        return DataParseUtil.getDouble(arr[index], defaultValue);
    }
}
