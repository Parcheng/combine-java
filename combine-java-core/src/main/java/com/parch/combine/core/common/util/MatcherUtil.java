package com.parch.combine.core.common.util;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正在匹配工具类
 */
public class MatcherUtil {

    private MatcherUtil() {}

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
