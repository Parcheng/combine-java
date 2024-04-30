package com.parch.combine.core.tools.compare;

import com.parch.combine.common.util.DataTypeIsUtil;

/**
 * 类型判断比较处理器
 */
public class DataTypeCompare {

    /**
     * 比较
     *
     * @param config 测试配置
     */
    public static CompareResult compare(CompareConfig config) {
        Object source = config.parseSourceValue();

        boolean success = false;
        switch (config.getCompareType()) {
            case IS_BOOLEAN:
                success = DataTypeIsUtil.isBoolean(source);
                break;
            case IS_NUMBER:
                success = DataTypeIsUtil.isNumber(source);
                break;
            case IS_STRING:
                success = DataTypeIsUtil.isString(source);
                break;
            case IS_DATE:
                success = DataTypeIsUtil.isDate(source);
                break;
            case IS_OBJECT:
                success = DataTypeIsUtil.isObject(source);
                break;
            case IS_LIST:
                success = DataTypeIsUtil.isList(source);
                break;
        }

        return success ? CompareResult.success() : CompareResult.fail();
    }
}
