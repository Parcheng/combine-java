package com.parch.combine.components.data.general.verify;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.settings.config.IOptionSetting;

/**
 * 验证模型
 */
public enum VerifyModeEnum implements IOptionSetting {

    FIRST("只返回第一个错误", true),
    ALL("返回全部错误", true),
    NONE("未知", false);

    private String name;
    private boolean isValid;

    VerifyModeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

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

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isValid() {
        return this.isValid;
    }
}
