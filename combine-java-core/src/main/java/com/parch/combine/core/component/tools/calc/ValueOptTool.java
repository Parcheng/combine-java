package com.parch.combine.core.component.tools.calc;

import com.parch.combine.core.common.util.DataTypeIsUtil;
import com.parch.combine.core.common.util.json.JsonUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 数据处理工具
 */
public class ValueOptTool {

    /**
     * 解析值到集合中
     *
     * @param value 值
     * @param list 集合
     */
    @SuppressWarnings("unchecked")
    public static void parseValueToList(Object value, List<Object> list) {
        if (value == null) {
            return;
        }

        if (value instanceof Collection) {
            for (Object valueItem : (Collection<Object>) value) {
                parseValueToList(valueItem, list);
            }
        } else {
            list.add(value);
        }
    }

    /**
     * 排序
     *
     * @param list 集合
     * @param fieldKeys 排序字段
     * @param desc 是否倒序
     */
    public static void sort(List<Object> list, String[] fieldKeys, boolean desc) {
        list.sort((a, b)->{
            int compareResult = compareTo(a, b, fieldKeys);
            return !desc ? compareResult : (-1 * compareResult);
        });
    }

    /**
     * 最大值
     *
     * @param list 集合
     * @param fieldKeys 排序字段
     */
    public static Object max(List<Object> list, String[] fieldKeys) {
        Object max = null;
        for (Object item : list) {
            if (max == null) {
                max = item;
                continue;
            }

            int compareResult = compareTo(item, max, fieldKeys);
            if (compareResult > 0) {
                max = item;
            }
        }

        return max;
    }

    /**
     * 最小值
     *
     * @param list 集合
     * @param fieldKeys 排序字段
     */
    public static Object min(List<Object> list, String[] fieldKeys) {
        Object min = null;
        for (Object item : list) {
            if (min == null) {
                min = item;
                continue;
            }

            int compareResult = compareTo(min, item, fieldKeys);
            if (compareResult > 0) {
                min = item;
            }
        }

        return min;
    }

    /**
     * 比较
     *
     * @param a 前值
     * @param b 后值
     * @param fieldKeys 排序字段集合
     * @return 比较结果
     */
    @SuppressWarnings("unchecked")
    public static int compareTo(Object a, Object b, String[] fieldKeys) {
        // MAP 类型先解析其值
        if (fieldKeys != null && fieldKeys.length > 0) {
            if (a instanceof Map) {
                StringBuilder keyA = new StringBuilder();
                for (String key : fieldKeys) {
                    keyA.append(((Map<String, Object>) a).get(key));
                }
                a = keyA.toString();
            }
            if (b instanceof Map) {
                StringBuilder keyB = new StringBuilder();
                for (String key : fieldKeys) {
                    keyB.append(((Map<String, Object>) b).get(key));
                }
                b = keyB.toString();
            }
        }

        // 集合和Map类型要转成SJON
        if (a instanceof Collection || a instanceof Map) {
            a = JsonUtil.obj2String(a);
        }
        if (b instanceof Collection || b instanceof Map) {
            b = JsonUtil.obj2String(b);
        }

        // 数字和字符串比较方式不同
        if (DataTypeIsUtil.isNumber(a) && DataTypeIsUtil.isNumber(b)) {
            return Double.compare(Double.parseDouble(a.toString()), Double.parseDouble(b.toString()));
        } else {
            return a.toString().compareTo(b.toString());
        }
    }
}
