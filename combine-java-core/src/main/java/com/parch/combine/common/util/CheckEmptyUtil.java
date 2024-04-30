package com.parch.combine.common.util;

import java.util.Collection;

/**
 * 判断空工具类
 */
public class CheckEmptyUtil {

    public static final String SPACE = " ";
    public static final String EMPTY = "";
    public static final String LF = "\n";
    public static final String CR = "\r";

    /**
     * 判断空
     *
     * @param collection 集合
     * @return 结果
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 判断非空
     *
     * @param collection 集合
     * @return 结果
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && collection.size() > 0;
    }

    /**
     * 判断空
     *
     * @param str 字符串
     * @return 结果
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断非空
     *
     * @param str 字符串
     * @return 结果
     */
    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    /**
     * 任意一个为空
     *
     * @param strArr 字符串集合
     * @return 结果
     */
    public static boolean isAnyEmpty(String... strArr) {
        if (strArr != null && strArr.length != 0) {
            for (String str : strArr) {
                if (isEmpty(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
