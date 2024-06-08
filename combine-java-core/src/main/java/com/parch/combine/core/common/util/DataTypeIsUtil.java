package com.parch.combine.core.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据类型判断工具类
 */
public class DataTypeIsUtil {

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
        return data != null && NumberUtil.isInteger(data.toString());
    }

    /**
     * 是否为长整数
     *
     * @param data 数据
     * @return 结果
     */
    public static boolean isLong(Object data) {
        return data != null && NumberUtil.isLong(data.toString());
    }

    /**
     * 是否为长浮点数
     *
     * @param data 数据
     * @return 结果
     */
    public static boolean isDouble(Object data) {
        return data != null && NumberUtil.isDouble(data.toString());
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
                DateUtil.parse(data.toString());
                return true;
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
