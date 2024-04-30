package com.parch.combine.components.tool.cache;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.settings.config.IOptionSetting;

public enum CacheKeyMatchRuleEnum implements IOptionSetting {

    EXACT("精确匹配", true),

    FUZZY("模糊匹配", true),

    NONE("未知", false);

    private final String name;
    private final boolean isValid;

    CacheKeyMatchRuleEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }


    public static CacheKeyMatchRuleEnum get(String mode) {
        if (CheckEmptyUtil.isEmpty(mode)) {
            return EXACT;
        }
        for (CacheKeyMatchRuleEnum value : CacheKeyMatchRuleEnum.values()) {
            if (value.toString().equals(mode.toUpperCase())) {
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
        return name;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }
}
