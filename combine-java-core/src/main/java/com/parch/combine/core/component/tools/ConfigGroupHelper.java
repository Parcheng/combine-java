package com.parch.combine.core.component.tools;

import com.parch.combine.core.common.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ConfigGroupHelper {

    /**
     * 配置分隔符
     */
    private static final String SEPARATOR = " ";

    /**
     * 拆分参数组
     *
     * @param configGroup
     * @return
     */
    public static String[] split(String configGroup) {
        if (configGroup == null) {
            return new String[0];
        }
        return configGroup.split(SEPARATOR);
    }

    /**
     * 根据下标获取配置数据
     *
     * @param data 数据数组
     * @param index 下标
     * @return 配置字符串
     */
    public static String getConfigByIndex(String[] data, int index) {
        if (data == null || data.length <= index) {
            return null;
        }

        return data[index];
    }

    /**
     * 根据起始下标获取配置数据组
     *
     * @param data 数据数组
     * @param startIndex 起始下标
     * @return 配置字符串
     */
    public static String[] getConfigsByIndex(String[] data, int startIndex) {
        return getConfigsByIndex(data, startIndex, data.length - 1);
    }

    /**
     * 根据起始下标获取配置数据组
     *
     * @param data 数据数组
     * @param startIndex 起始下标
     * @param endIndex 结束下标
     * @return 配置字符串
     */
    public static String[] getConfigsByIndex(String[] data, int startIndex, int endIndex) {
        if (data == null || data.length <= startIndex || endIndex < startIndex) {
            return new String[0];
        }

        // 截取数组
        return ArrayUtil.split(data, String.class, startIndex, endIndex);
    }

    /**
     * 构建配置项集合
     *
     * @param items 配置项字符串集合
     * @param <T> 配置对象泛型
     * @return 配置对象集合
     */
    public static <T> List<T> buildItemList(List<String> items, Function<String[], T> func) {
        List<T> result = new ArrayList<>();
        for (String itemStr : items) {
            String[] itemArr = ConfigGroupHelper.split(itemStr);
            result.add(func.apply(itemArr));
        }

        return result;
    }
}
