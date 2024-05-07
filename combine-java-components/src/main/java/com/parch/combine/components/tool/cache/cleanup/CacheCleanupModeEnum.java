package com.parch.combine.components.tool.cache.cleanup;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.settings.config.IOptionSetting;

public enum CacheCleanupModeEnum implements IOptionSetting {

    ALL("全部", true),

    EXPIRED("过期", true),

    NEAR_EXPIRED("过期和临近过期", true),

    NONE("未知", false);

    private final String name;
    private final boolean isValid;

    CacheCleanupModeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }


    public static CacheCleanupModeEnum get(String mode) {
        if (CheckEmptyUtil.isEmpty(mode)) {
            return ALL;
        }
        for (CacheCleanupModeEnum value : CacheCleanupModeEnum.values()) {
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
