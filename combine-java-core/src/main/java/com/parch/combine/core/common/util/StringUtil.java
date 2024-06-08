package com.parch.combine.core.common.util;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtil {

    private StringUtil() {}

    /**
     * 字符串拼接
     *
     * @param list 集合
     * @param separator 拼接字符
     * @return 结果
     */
    public static String join(Collection<?> list, String separator) {
        if (CheckEmptyUtil.isEmpty(list)) {
            return CheckEmptyUtil.EMPTY;
        }
        int index = 0;
        StringBuilder result = new StringBuilder(CheckEmptyUtil.EMPTY);
        for (Object item : list) {
            result.append(item == null ? CheckEmptyUtil.EMPTY : item.toString());
            if (index != list.size() -1) {
                result.append(separator);
            }
            index++;
        }
        return result.toString();
    }

    /**
     * 字符串拼接
     *
     * @param array 集合
     * @param separator 拼接字符
     * @return 结果
     */
    public static String join(Object[] array, String separator) {
        if (array == null || array.length == 0) {
            return CheckEmptyUtil.EMPTY;
        }
        int index = 0;
        StringBuilder result = new StringBuilder(CheckEmptyUtil.EMPTY);
        for (Object item : array) {
            result.append(item == null ? CheckEmptyUtil.EMPTY : item.toString());
            if (index != array.length -1) {
                result.append(separator);
            }
            index++;
        }
        return result.toString();
    }

    /**
     * 匹配
     *
     * @param str 字符串
     * @param regex 正则
     * @param func 自定义函数
     */
    public static void matcher(String str, String regex, Consumer<String> func) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String paramStr = matcher.group();
            func.accept(paramStr);
        }
    }
}
