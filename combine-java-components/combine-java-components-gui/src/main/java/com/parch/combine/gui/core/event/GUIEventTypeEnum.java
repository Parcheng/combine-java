package com.parch.combine.gui.core.event;

import com.parch.combine.core.component.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

public enum GUIEventTypeEnum implements IOptionSetting {

    CLICK("单击", true),
    DBL_CLICK("双击", true),
    MOVE_IN("鼠标移入", true),
    MOVE_OUT("鼠标移出", true),
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
