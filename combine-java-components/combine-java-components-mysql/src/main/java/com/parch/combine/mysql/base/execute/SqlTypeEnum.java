package com.parch.combine.mysql.base.execute;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.settings.config.IOptionSetting;

public enum SqlTypeEnum implements IOptionSetting {

    INSERT("新增", true),
    INSERT_INCR("新增（主键自增长）", true),
    UPDATE("修改", true),
    DELETE("删除", true),
    SELECT("多条查询", true),
    SELECT_ONE("单条查询", true),
    SELECT_LIMIT("分页查询", true),
    DDL("DDL语句", true),
    NONE("未知", false);

    private String name;
    private boolean isValid;

    SqlTypeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    public static SqlTypeEnum get(String sqlType) {
        if (CheckEmptyUtil.isEmpty(sqlType)) {
            return NONE;
        }
        for (SqlTypeEnum value : SqlTypeEnum.values()) {
            if (value.toString().equals(sqlType.toUpperCase())) {
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
