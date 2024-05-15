package com.parch.combine.core.ui.base.dataload;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

/**
 * 数据加载类型
 */
public enum DataLoadTypeEnum implements IOptionSetting {

    FLOW("内部FLOW", true),
    API("外部API", true),
    FILE("文件数据", true),
    NONE("未知", false);

    private String name;
    private boolean isValid;

    DataLoadTypeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    public static DataLoadTypeEnum get(String name) {
        if (CheckEmptyUtil.isEmpty(name)) {
            return NONE;
        }
        for (DataLoadTypeEnum value : DataLoadTypeEnum.values()) {
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
