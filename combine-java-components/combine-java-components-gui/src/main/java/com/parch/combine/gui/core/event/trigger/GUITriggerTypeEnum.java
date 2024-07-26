package com.parch.combine.gui.core.event.trigger;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

public enum GUITriggerTypeEnum implements IOptionSetting {

    COMPONENT("执行组件", true),
    DIALOG_BOX("弹窗", true),
    INTERNAL("内部拓展处理器", false),
    NONE("未知", false);

    private String name;
    private boolean isValid;

    GUITriggerTypeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    public static GUITriggerTypeEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return NONE;
        }
        for (GUITriggerTypeEnum value : GUITriggerTypeEnum.values()) {
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
