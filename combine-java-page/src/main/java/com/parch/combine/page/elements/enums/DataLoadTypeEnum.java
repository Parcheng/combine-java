package com.parch.combine.page.elements.enums;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.settings.config.IOptionSetting;

/**
 * 数据加载类型
 */
public enum DataLoadTypeEnum implements IOptionSetting {

    FLOW("流程数据", true),
    API("外部API", true),
    FILE("文件数据", true),
    REF("引用", true);

    private String name;
    private boolean isValid;

    DataLoadTypeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    public static DataLoadTypeEnum get(String name) {
        if (CheckEmptyUtil.isEmpty(name)) {
            return REF;
        }
        for (DataLoadTypeEnum value : DataLoadTypeEnum.values()) {
            if (value.toString().equals(name.toUpperCase())) {
                return value;
            }
        }
        return REF;
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
