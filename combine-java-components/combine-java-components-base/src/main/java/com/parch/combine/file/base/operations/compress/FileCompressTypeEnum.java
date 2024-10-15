package com.parch.combine.file.base.operations.compress;

import com.parch.combine.core.common.util.CheckEmptyUtil;

/**
 * 压缩类型枚举
 */
public enum FileCompressTypeEnum {
    ZIP("zip"), RAR("rar"), TAR_GZ("tar.gz"), SEVEN_Z("7z"), NONE("");

    private final String postfix;

    FileCompressTypeEnum(String postfix) {
        this.postfix = postfix;
    }

    public static FileCompressTypeEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return NONE;
        }
        for (FileCompressTypeEnum value : FileCompressTypeEnum.values()) {
            if (value.getPostfix().equals(type.toLowerCase())) {
                return value;
            }
        }
        return NONE;
    }

    public String getPostfix() {
        return postfix;
    }
}
