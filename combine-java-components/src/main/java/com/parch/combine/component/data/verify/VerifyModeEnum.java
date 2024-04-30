package com.parch.combine.component.data.verify;

import com.parch.combine.common.util.CheckEmptyUtil;

/**
 * 验证模型
 */
public enum VerifyModeEnum {
    /**
     * 只返回第一个错误
     */
    FIRST,
    /**
     * 返回全部错误
     */
    ALL,
    /**
     * 未知类型
     */
    NONE;

    public static VerifyModeEnum get(String sqlType) {
        if (CheckEmptyUtil.isEmpty(sqlType)) {
            return NONE;
        }
        for (VerifyModeEnum value : VerifyModeEnum.values()) {
            if (value.toString().equals(sqlType.toUpperCase())) {
                return value;
            }
        }
        return NONE;
    }
}
