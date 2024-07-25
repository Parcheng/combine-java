package com.parch.combine.gui.base.build.control.from;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

public enum LayoutEnum implements IOptionSetting {

    INLINE("内联", true),
    HORIZONTAL("水平", true),
    VERTICAL("垂直", true);

    private String name;
    private boolean isValid;

    LayoutEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    public static LayoutEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return HORIZONTAL;
        }
        for (LayoutEnum value : LayoutEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
                return value;
            }
        }
        return HORIZONTAL;
    }


    @Override
    public String getKey() {
        return this.name();
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
