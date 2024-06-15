package com.parch.combine.file.base;

import com.parch.combine.core.common.util.CheckEmptyUtil;

/**
 * 文件后缀枚举
 */
public enum FilePostfixEnum {

    XLS, XLSX, TXT, PDF, NONE;

    public static FilePostfixEnum get(String sqlType) {
        if (CheckEmptyUtil.isEmpty(sqlType)) {
            return NONE;
        }
        for (FilePostfixEnum value : FilePostfixEnum.values()) {
            if (value.toString().equals(sqlType.toUpperCase())) {
                return value;
            }
        }
        return NONE;
    }
}
