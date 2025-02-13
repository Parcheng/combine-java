package com.parch.combine.html.common.enums;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.settings.config.IOptionSetting;

/**
 * 页面元素操作类型枚举
 */
public enum AlignModeEnum implements IOptionSetting {

    LEFT("左对齐", true),
    RIGHT("右对齐", true),
    CENTRE("中对齐", true),
    NONE("未知", false);

    private final String name;
    private final boolean isValid;

    AlignModeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    public static AlignModeEnum get(String name) {
        if (CheckEmptyUtil.isEmpty(name)) {
            return NONE;
        }
        for (AlignModeEnum value : AlignModeEnum.values()) {
            if (value.toString().equals(name.toUpperCase())) {
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
