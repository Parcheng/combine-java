package com.parch.combine.core.tools.compare;

import com.parch.combine.common.util.CheckEmptyUtil;

import java.util.Collection;
import java.util.Map;

/**
 * 空值比较处理器
 */
public class EmptyCompare {

    /**
     * 比较
     *
     * @param config 测试配置
     */
    public static CompareResult compare(CompareConfig config) {
        Object source = config.parseSourceValue();

        boolean success = false;
        switch (config.getCompareType()) {
            case NO_NULL:
                success = source != null;
                break;
            case IS_NULL:
                success = source == null;
                break;
            case NO_EMPTY:
                if (source instanceof Collection) {
                    success = source != null && ((Collection<?>) source).size() > 0;
                } else if (source instanceof Map) {
                    success = source != null && ((Map<?,?>) source).size() > 0;
                } else {
                    success = source != null && CheckEmptyUtil.isNotEmpty(source.toString());
                }
                break;
            case IS_EMPTY:
                if (source instanceof Collection) {
                    success = source == null || ((Collection<?>) source).size() == 0;
                } else {
                    success = source == null || CheckEmptyUtil.isEmpty(source.toString());
                }
                break;
        }

        return success ? CompareResult.success() : CompareResult.fail();
    }
}
