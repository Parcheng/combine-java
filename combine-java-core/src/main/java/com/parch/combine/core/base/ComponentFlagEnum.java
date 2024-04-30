package com.parch.combine.core.base;

import com.parch.combine.common.util.CheckEmptyUtil;

/**
 * 组件标识
 */
public enum ComponentFlagEnum {
    STATIC, NONE;

    public static ComponentFlagEnum get(String flag) {
        if (CheckEmptyUtil.isEmpty(flag)) {
            return NONE;
        }
        for (ComponentFlagEnum value : ComponentFlagEnum.values()) {
            if (value.toString().equals(flag.toUpperCase())) {
                return value;
            }
        }
        return NONE;
    }
}
