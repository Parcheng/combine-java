package com.parch.combine.gui.core.common;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import javax.swing.SwingConstants;

public enum GUIAlignTypeEnum implements IOptionSetting {

    LEFT("执行组件", SwingConstants.LEFT, true),
    CENTER("弹窗", SwingConstants.CENTER, true),
    RIGHT("未知", SwingConstants.RIGHT, false);

    private String name;
    private int value;
    private boolean isValid;

    GUIAlignTypeEnum(String name, int value, boolean isValid) {
        this.name = name;
        this.value = value;
        this.isValid = isValid;
    }

    public static GUIAlignTypeEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return CENTER;
        }
        for (GUIAlignTypeEnum value : GUIAlignTypeEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
                return value;
            }
        }
        return CENTER;
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
