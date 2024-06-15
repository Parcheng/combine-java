package com.parch.combine.data.base.general.calc;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

/**
 * 数据运算方式枚举
 */
public enum DataCalcModeEnum implements IOptionSetting {

    CALC("数学运算", true, 1, null,
            new String[]{"\t参数1（必填）：运算表达式，如：100 - 20 * 2 + (30 / 2 + 26 % 20) - 30"}),

    MAX("计算最大值", true, 2, "支持数字/日期/字符串比较，至少有1个参数",
            new String[]{"\t参数1-n（必填）：参与比较的值，支持 #{...} 表达式（如果是集合，则集合值都参与比较）"}),

    MIN("计算最小值", true, 2, "支持数字/日期/字符串比较，至少有1个参数",
            new String[]{"\t参数1-n（必填）：参与比较的值，支持 #{...} 表达式（如果是集合，则集合值都参与比较）"}),

    RANDOM("生成随机数", true, 1, null,
            new String[]{"\t参数1（必填）：生成随机数值的区间的最大值或最小值，必须为整数", "\t参数2（选填）：生成随机数值的区间的最大值或最小值，必须为整数"}),

    UUID("生成UUID", true, 0, null,
            new String[]{"\\t无参数"}),

    NONE("未知", false, 0, null, null);

    private int minParamCount;
    private String name;
    private boolean isValid;
    private String desc;
    private String[] details;

    DataCalcModeEnum(String name, boolean isValid, int minParamCount, String desc, String[] details) {
        this.name = name;
        this.isValid = isValid;
        this.minParamCount = minParamCount;
        this.desc = desc;
        this.details = details;
    }


    public static DataCalcModeEnum get(String mode) {
        if (CheckEmptyUtil.isEmpty(mode)) {
            return NONE;
        }
        for (DataCalcModeEnum value : DataCalcModeEnum.values()) {
            if (value.toString().equals(mode.toUpperCase())) {
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
        return desc;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public String[] getDetails() {
        return details;
    }
}
