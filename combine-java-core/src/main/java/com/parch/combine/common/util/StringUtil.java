package com.parch.combine.common.util;

import org.apache.commons.lang3.StringUtils;

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
        return StringUtils.join(list, separator);
    }
}
