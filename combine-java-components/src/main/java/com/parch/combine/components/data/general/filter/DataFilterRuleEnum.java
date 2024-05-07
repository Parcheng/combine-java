package com.parch.combine.components.data.general.filter;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.settings.config.IOptionSetting;

public enum DataFilterRuleEnum implements IOptionSetting {
    CLEAR("清除数据", "无参数", true),
    REPLACE("替换数据", "参数为新值", true),
    NONE("未知", null, false);

    private String name;
    private String desc;
    private boolean isValid;

    DataFilterRuleEnum(String name, String desc, boolean isValid) {
        this.name = name;
        this.desc = desc;
        this.isValid = isValid;
    }

    public static DataFilterRuleEnum get(String sqlType) {
        if (CheckEmptyUtil.isEmpty(sqlType)) {
            return NONE;
        }
        for (DataFilterRuleEnum value : DataFilterRuleEnum.values()) {
            if (value.toString().equals(sqlType.toUpperCase())) {
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
    public String getDesc() {
        return this.desc;
    }

    @Override
    public boolean isValid() {
        return this.isValid;
    }
}
