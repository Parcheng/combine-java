package com.parch.combine.gui.core.style.enums;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.awt.*;

public enum AlignmentYEnum implements IOptionSetting {

    TOP("顶部", Component.TOP_ALIGNMENT, true),
    BOTTOM("底部", Component.BOTTOM_ALIGNMENT, true),
    CENTER("居中", Component.CENTER_ALIGNMENT, true);

    private String name;
    private float value;
    private boolean isValid;

    AlignmentYEnum(String name, float value, boolean isValid) {
        this.name = name;
        this.value = value;
        this.isValid = isValid;
    }

    public static AlignmentYEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return TOP;
        }
        for (AlignmentYEnum value : AlignmentYEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
                return value;
            }
        }
        return TOP;
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
