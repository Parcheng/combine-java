package com.parch.combine.common.util;

/**
 * 流程Key工具类
 */
public class FlowKeyUtil {

    /**
     * 获取KEY
     *
     * @param domain 域
     * @param function 函数
     * @return 结果
     */
    public static String getKey(String domain, String function) {
        return domain + (CheckEmptyUtil.isEmpty(function) ? CheckEmptyUtil.EMPTY : ("/" + function));
    }

    /**
     * 解析KEY
     *
     * @param key KEY
     * @return [域，函数]
     */
    public static String[] parseKey(String key) {
        String[] keyArr = new String[2];
        String[] urlPathArr = key.split("/");
        keyArr[0] = urlPathArr[0];
        keyArr[1] = urlPathArr.length > 1 ? urlPathArr[1] : CheckEmptyUtil.EMPTY;
        return keyArr;
    }
}
