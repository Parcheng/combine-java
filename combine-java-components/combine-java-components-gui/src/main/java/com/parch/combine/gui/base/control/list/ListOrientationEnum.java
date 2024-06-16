package com.parch.combine.gui.base.control.list;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import javax.swing.JList;

public enum ListOrientationEnum implements IOptionSetting {

    VERTICAL("垂直", JList.VERTICAL_WRAP, true),
    HORIZONTAL("水平", JList.HORIZONTAL_WRAP, true);

    private String name;
    private int value;
    private boolean isValid;

    ListOrientationEnum(String name, int value, boolean isValid) {
        this.name = name;
        this.value = value;
        this.isValid = isValid;
    }

    public static ListOrientationEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return VERTICAL;
        }
        for (ListOrientationEnum value : ListOrientationEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
                return value;
            }
        }
        return VERTICAL;
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
