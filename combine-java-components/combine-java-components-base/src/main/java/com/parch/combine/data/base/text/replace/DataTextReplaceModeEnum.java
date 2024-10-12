package com.parch.combine.data.base.text.replace;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

/**
 * 枚举
 */
public enum DataTextReplaceModeEnum implements IOptionSetting {

    FIRST("替换首个", true),

    ALL("替换全部", true),

    NONE("未知", false);

    private String name;
    private boolean isValid;

    DataTextReplaceModeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }


    public static DataTextReplaceModeEnum get(String mode) {
        if (CheckEmptyUtil.isEmpty(mode)) {
            return NONE;
        }
        for (DataTextReplaceModeEnum value : DataTextReplaceModeEnum.values()) {
            if (value.name().equals(mode.toUpperCase())) {
                return value;
            }
        }
        return NONE;
    }

    @Override
    public String getKey() {
        return this.name();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }
}
