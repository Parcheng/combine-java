package com.parch.combine.gui.core.style.enums;

import com.parch.combine.core.component.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.awt.*;

@Deprecated
public enum LayoutAlignTypeEnum implements IOptionSetting {


    LEFT("靠左", FlowLayout.LEFT, true),
    RIGHT("靠右", FlowLayout.RIGHT, true),
    LEADING("自适应靠左", FlowLayout.LEADING, true),
    TRAILING("自适应靠右", FlowLayout.TRAILING, true),
    CENTER("居中", FlowLayout.CENTER, true);

    private String name;
    private int value;
    private boolean isValid;

    LayoutAlignTypeEnum(String name, int value, boolean isValid) {
        this.name = name;
        this.value = value;
        this.isValid = isValid;
    }

    public static LayoutAlignTypeEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return LEFT;
        }
        for (LayoutAlignTypeEnum value : LayoutAlignTypeEnum.values()) {
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

    public int getValue() {
        return value;
    }
}
