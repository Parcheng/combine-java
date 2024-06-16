package com.parch.combine.gui.core.style.enums;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.awt.*;

public enum LayoutTypeEnum implements IOptionSetting {

    FLOW("流式布局", true),
    BOX("盒子布局", true);

    private String name;
    private boolean isValid;

    LayoutTypeEnum(String name,boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    public static LayoutTypeEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return FLOW;
        }
        for (LayoutTypeEnum value : LayoutTypeEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
                return value;
            }
        }
        return FLOW;
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
