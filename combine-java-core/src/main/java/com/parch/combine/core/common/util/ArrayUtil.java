package com.parch.combine.core.common.util;

import java.lang.reflect.Array;

/**
 * 数据工具类
 */
public class ArrayUtil {

    /**
     * 数组截取
     *
     * @param arr 数组
     * @param startIndex 起始坐标
     * @param endIndex 结束坐标
     * @return 新数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] split(T[] arr, Class<T> tClass, int startIndex, int endIndex) {
        int newLength = endIndex - startIndex + 1;
        T[] result = (T[]) Array.newInstance(tClass, newLength);
        System.arraycopy(arr, startIndex, result, 0, newLength);
        return result;
    }
}
