package com.parch.combine.component.data.edit;

import com.parch.combine.common.util.CheckEmptyUtil;

/**
 * 数据编辑类型枚举
 */
public enum DataEditTypeEnum {
    ADD(2), ADD_ALL(2), SET(2), SET_ALL(2), PUT(1), PUT_ALL(1), REMOVE(1), REMOVE_INDEX(1), REMOVE_ALL(1), NONE(0);

    private int minParamCount;

    private DataEditTypeEnum(int minParamCount) {
        this.minParamCount = minParamCount;
    }

    public static DataEditTypeEnum get(String sqlType) {
        if (CheckEmptyUtil.isEmpty(sqlType)) {
            return NONE;
        }
        for (DataEditTypeEnum value : DataEditTypeEnum.values()) {
            if (value.toString().equals(sqlType.toUpperCase())) {
                return value;
            }
        }
        return NONE;
    }

    public int getMinParamCount() {
        return minParamCount;
    }
}
