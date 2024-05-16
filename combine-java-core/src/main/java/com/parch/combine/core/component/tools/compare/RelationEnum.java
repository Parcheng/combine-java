package com.parch.combine.core.component.tools.compare;

import com.parch.combine.core.common.util.CheckEmptyUtil;

/**
 * 关联类型
 */
public enum RelationEnum {

    AND, OR;

    public static RelationEnum get(String code) {
        if (CheckEmptyUtil.isEmpty(code)) {
            return AND;
        }
        for (RelationEnum value : RelationEnum.values()) {
            if (value.toString().equals(code.toUpperCase())) {
                return value;
            }
        }
        return AND;
    }
}
