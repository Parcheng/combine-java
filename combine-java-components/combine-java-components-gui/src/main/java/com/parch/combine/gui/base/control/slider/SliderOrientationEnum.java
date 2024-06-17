package com.parch.combine.gui.base.control.slider;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import javax.swing.JSlider;

public enum SliderOrientationEnum implements IOptionSetting {

    VERTICAL("垂直", JSlider.VERTICAL, true),
    HORIZONTAL("水平", JSlider.HORIZONTAL, true);

    private String name;
    private int value;
    private boolean isValid;

    SliderOrientationEnum(String name, int value, boolean isValid) {
        this.name = name;
        this.value = value;
        this.isValid = isValid;
    }

    public static SliderOrientationEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return HORIZONTAL;
        }
        for (SliderOrientationEnum value : SliderOrientationEnum.values()) {
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

    public int getValue() {
        return value;
    }
}
