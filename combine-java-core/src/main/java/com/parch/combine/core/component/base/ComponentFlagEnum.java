package com.parch.combine.core.component.base;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.settings.config.IOptionSetting;

/**
 * 组件标识
 */
public enum ComponentFlagEnum implements IOptionSetting {
    STATIC("静态", true), NONE("未知", false);

    private String name;
    private boolean isValid;

    ComponentFlagEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

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

    @Override
    public String getKey() {
        return name();
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
