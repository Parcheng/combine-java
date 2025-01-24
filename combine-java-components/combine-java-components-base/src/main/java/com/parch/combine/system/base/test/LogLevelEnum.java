package com.parch.combine.system.base.test;

import com.parch.combine.core.component.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

public enum LogLevelEnum implements IOptionSetting {

    INFO("常规", 1,true),
    WARN("警告", 2, true),
    FAIL("失败", 3,true),
    ERROR("异常", 4, true),
    NONE("未知", -1,false);

    private int level;
    private String name;
    private boolean isValid;

    LogLevelEnum(String name, int level, boolean isValid) {
        this.name = name;
        this.level = level;
        this.isValid = isValid;
    }

    public static LogLevelEnum get(String level) {
        if (CheckEmptyUtil.isEmpty(level)) {
            return NONE;
        }
        for (LogLevelEnum value : LogLevelEnum.values()) {
            if (value.toString().equals(level.toUpperCase())) {
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

    public int getLevel() {
        return level;
    }
}
