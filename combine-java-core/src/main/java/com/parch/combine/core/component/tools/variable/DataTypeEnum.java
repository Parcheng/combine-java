package com.parch.combine.core.component.tools.variable;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.settings.config.IOptionSetting;

/**
 * 数据类型枚举
 */
public enum DataTypeEnum implements IOptionSetting {
    STRING("字符串", null, true),
    NUMBER("数字", null,true),
    BOOLEAN("布尔", null,true),
    DATE("日期", "需为 “yyyyMMddHHmmssSSS” 格式", true),
    LIST("集合", null,true),
    OBJECT("对象结构", null,true),
    NONE("未知", null,false);

    private String name;
    private String desc;
    private boolean isValid;

    DataTypeEnum(String name, String desc, boolean isValid) {
        this.name = name;
        this.desc = desc;
        this.isValid = isValid;
    }

    public static DataTypeEnum get(String sqlType) {
        if (CheckEmptyUtil.isEmpty(sqlType)) {
            return NONE;
        }
        for (DataTypeEnum value : DataTypeEnum.values()) {
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
