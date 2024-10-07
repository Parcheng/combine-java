package com.parch.combine.html.base.trigger.common;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

/**
 * 页面元素操作类型枚举
 */
public enum TriggerTypeEnum implements IOptionSetting {

    CALL_FLOW("调用流程", true),
    CALL_URL("调用URL", true),
    CALL_FUNC("调用页面元素函数", true),
    LOAD("加载元素", true),
    LOAD_DATA("加载数据", true),
    SKIP("跳转", true),
    CUSTOM("自定义", true),
    NONE("未知", false);

    private String name;
    private boolean isValid;

    TriggerTypeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    public static TriggerTypeEnum get(String name) {
        if (CheckEmptyUtil.isEmpty(name)) {
            return NONE;
        }
        for (TriggerTypeEnum value : TriggerTypeEnum.values()) {
            if (value.toString().equals(name.toUpperCase())) {
                return value;
            }
        }
        return NONE;
    }

    @Override
    public String getKey() {
        return name();
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
