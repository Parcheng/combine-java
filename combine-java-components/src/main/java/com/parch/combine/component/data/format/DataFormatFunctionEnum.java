package com.parch.combine.component.data.format;

import com.parch.combine.common.util.CheckEmptyUtil;

/**
 * 函数枚举
 */
public enum DataFormatFunctionEnum {

    JSON, CLEAR_DUPLICATE, SORT, TEXT_CONVERT, GROUP, TO_MAP, TO_TREE, RANG, CUSTOM, MD5, NONE;

    public static DataFormatFunctionEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return NONE;
        }
        for (DataFormatFunctionEnum value : DataFormatFunctionEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
                return value;
            }
        }
        return NONE;
    }
}
