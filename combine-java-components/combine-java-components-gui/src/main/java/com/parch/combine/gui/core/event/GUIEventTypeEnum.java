package com.parch.combine.gui.core.event;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

public enum GUIEventTypeEnum implements IOptionSetting {

    CLICK("单击", true),
    DBLCLICK("双击", true),
    NONE("未知", false);

    private String name;
    private boolean isValid;

    GUIEventTypeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    public static GUIEventTypeEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return NONE;
        }
        for (GUIEventTypeEnum value : GUIEventTypeEnum.values()) {
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
}
