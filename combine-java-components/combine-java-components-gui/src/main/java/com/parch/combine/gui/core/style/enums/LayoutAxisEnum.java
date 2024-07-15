package com.parch.combine.gui.core.style.enums;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import javax.swing.BoxLayout;

@Deprecated
public enum LayoutAxisEnum implements IOptionSetting {
    X("X轴方向排列", BoxLayout.X_AXIS, true),
    Y("Y轴方向排列", BoxLayout.Y_AXIS, true);

    private String name;
    private int value;
    private boolean isValid;

    LayoutAxisEnum(String name, int value, boolean isValid) {
        this.name = name;
        this.value = value;
        this.isValid = isValid;
    }

    public static LayoutAxisEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return X;
        }
        for (LayoutAxisEnum value : LayoutAxisEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
                return value;
            }
        }
        return X;
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
