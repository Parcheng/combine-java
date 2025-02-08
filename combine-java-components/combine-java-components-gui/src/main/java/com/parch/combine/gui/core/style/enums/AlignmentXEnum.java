package com.parch.combine.gui.core.style.enums;

import com.parch.combine.core.component.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.awt.Component;

public enum AlignmentXEnum implements IOptionSetting {

    LEFT("靠左", Component.LEFT_ALIGNMENT, true),
    RIGHT("靠右", Component.RIGHT_ALIGNMENT, true),
    CENTER("居中", Component.CENTER_ALIGNMENT, true);

    private String name;
    private float value;
    private boolean isValid;

    AlignmentXEnum(String name, float value, boolean isValid) {
        this.name = name;
        this.value = value;
        this.isValid = isValid;
    }

    public static AlignmentXEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return LEFT;
        }
        for (AlignmentXEnum value : AlignmentXEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
                return value;
            }
        }
        return LEFT;
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

    public float getValue() {
        return value;
    }
}
