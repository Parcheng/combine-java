package com.parch.combine.component.data.calc;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.settings.config.IOptionSetting;

/**
 * 数据运算方式枚举
 */
public enum DataCalcModeEnum implements IOptionSetting {
    CALC("计算器", true, 1),
    MAX("计算最大值", true, 2),
    MIN("计算最小值", true, 2),
    RANDOM("生成随机数", true, 1),
    UUID("生成UUID", true, 0),
    NONE("未知", false, 0);

    private int minParamCount;
    private String name;
    private boolean isValid;

    DataCalcModeEnum(String name, boolean isValid, int minParamCount) {
        this.name = name;
        this.isValid = isValid;
        this.minParamCount = minParamCount;
    }


    public static DataCalcModeEnum get(String sqlType) {
        if (CheckEmptyUtil.isEmpty(sqlType)) {
            return NONE;
        }
        for (DataCalcModeEnum value : DataCalcModeEnum.values()) {
            if (value.toString().equals(sqlType.toUpperCase())) {
                return value;
            }
        }
        return NONE;
    }

    public int getMinParamCount() {
        return minParamCount;
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
    public String getDesc() {
        return null;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }
}
