package com.parch.combine.core.common.util;

import java.util.Collection;

/**
 * 字符串工具类
 */
public class StringUtil {

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
}
