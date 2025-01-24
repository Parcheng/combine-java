package com.parch.combine.gui.core.style.enums;

import com.parch.combine.core.component.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.awt.*;

public enum GridFillEnum implements IOptionSetting {

    ALL("全部", GridBagConstraints.BOTH, true),
    VERTICAL("垂直", GridBagConstraints.VERTICAL, true),
    HORIZONTAL("水平", GridBagConstraints.HORIZONTAL, true),
    NONE("无", GridBagConstraints.NONE, true);

    private String name;
    private int value;
    private boolean isValid;

    GridFillEnum(String name, int value, boolean isValid) {
        this.name = name;
        this.value = value;
        this.isValid = isValid;
    }

    public static GridFillEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return NONE;
        }
        for (GridFillEnum value : GridFillEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
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
        return this.name;
    }

    @Override
    public boolean isValid() {
        return this.isValid;
    }

    public int getValue() {
        return value;
    }
}
