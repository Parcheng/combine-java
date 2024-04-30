package com.parch.combine.components.file.operations.compress;

import com.parch.combine.common.util.CheckEmptyUtil;

/**
 * 压缩类型枚举
 */
public enum FileCompressTypeEnum {
    ZIP, RAR, TAR_GZ, SEVEN_Z, NONE;

    public static FileCompressTypeEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return NONE;
        }
        for (FileCompressTypeEnum value : FileCompressTypeEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
                return value;
            }
        }
        return NONE;
    }
}
