package com.parch.combine.components.call;

import com.parch.combine.core.common.util.CheckEmptyUtil;

/**
 * 请求类型
 */
public enum CallTypeEnum {

    GET, POST, FILE, NONE;

    public static CallTypeEnum get(String sqlType) {
        if (CheckEmptyUtil.isEmpty(sqlType)) {
            return NONE;
        }
        for (CallTypeEnum value : CallTypeEnum.values()) {
            if (value.toString().equals(sqlType.toUpperCase())) {
                return value;
            }
        }
        return NONE;
    }
}
