package com.parch.combine.core.common.util;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据类型判断工具类
 */
public class DataTypeIsUtil {

    private DataTypeIsUtil() {}

    /**
     * 是否为数字
     *
     * @param data 数据
     * @return 结果
     */
    public static boolean isNumber(Object data) {
        return data != null && data.toString().matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * 是否为整数
     *
     * @param data 数据
     * @return 结果
     */
    public static boolean isInteger(Object data) {
        if (data == null || CheckEmptyUtil.isEmpty(data.toString())) {
            return false;
        } else {
            try {
                Integer.parseInt(data.toString());
                return true;
            } catch (NumberFormatException var2) {
                return false;
            }
        }
    }

    /**
     * 是否为长整数
     *
     * @param data 数据
     * @return 结果
     */
    public static boolean isLong(Object data) {
        if (data == null || CheckEmptyUtil.isEmpty(data.toString())) {
            return false;
        } else {
            try {
                Long.parseLong(data.toString());
                return true;
            } catch (NumberFormatException var2) {
                return false;
            }
        }
    }

    /**
     * 是否为长浮点数
     *
     * @param data 数据
     * @return 结果
     */
    public static boolean isDouble(Object data) {
        if (data == null || CheckEmptyUtil.isEmpty(data.toString())) {
            return false;
        } else {
            try {
                Double.parseDouble(data.toString());
            } catch (NumberFormatException var2) {
                return false;
            }

            return true;
        }
    }

    /**
     * 是否为String
     *
     * @param data 数据
     * @return 结果
     */
    public static boolean isString(Object data) {
        return data instanceof String;
    }

    /**
     * 是否为日期
     *
     * @param data 数据
     * @return 结果
     */
    public static boolean isDate(Object data) {
        if (data instanceof Date) {
            return true;
        }

        if (data != null) {
            try {
                return DataParseUtil.parseDate(data.toString()) != null;
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }

    /**
     * 是否为布尔
     *
     * @param data 数据
     * @return 结果
     */
    public static boolean isBoolean(Object data) {
        if (data == null) {
            return false;
        }

        if (data instanceof Boolean) {
            return true;
        }

        if (data instanceof String) {
            return "true".equals(data.toString()) || "false".equals(data.toString());
        }

        return false;
    }

    /**
     * 是否为结构对象
     *
     * @param data 数据
     * @return 结果
     */
    public static boolean isObject(Object data) {
        return data instanceof Map;
    }

    /**
     * 是否为集合
     *
     * @param data 数据
     * @return 结果
     */
    public static boolean isCollection(Object data) {
        return data instanceof Collection;
    }

    /**
     * 是否为List集合
     *
     * @param data 数据
     * @return 结果
     */
    public static boolean isList(Object data) {
        return data instanceof List;
    }
}
