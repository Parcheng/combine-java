package com.parch.combine.data.base.general.edit;

import com.parch.combine.core.component.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

/**
 * 数据编辑类型枚举
 */
public enum DataEditTypeEnum implements IOptionSetting {
    ADD(1, "追加", true,  "支持集合类型数据",
            new String[]{"\t参数1-n：要设置的值"}),

    SET(1, "设置", true,  "",
            new String[]{"\t参数1-n（常规类型）：要设置的值，格式必须为：:字段值", "\t参数1-n（集合类型）：要设置的值，格式必须为：索引:字段值"}),

    PUT(1, "添加", true,  "支持对象类型数据，",
            new String[]{"\t参数1-n：要设置的值，格式可以为：(数据类型:字段名:字段值)，或#{...}引用的对象类型数据"}),

    REMOVE(1, "删除", true,  "支持对象和集合类型数据，",
            new String[]{"\t参数1（对象类型）：要删除的字段名", "\t参数1（集合类型）：要删除的集合中的值"}),
    REMOVE_INDEX(1, "根据索引删除", true, "支持对象和集合类型数据，",
            new String[]{"\t参数1（对象类型）：要删除的字段名", "\t参数1（集合类型）：要删除的集合值的索引"}),
    REMOVE_ALL(1, "删除全部",true,  "支持对象和集合类型数据，",
            new String[]{"\t参数1-n（对象类型）：要删除的所有字段，格式可以为：字段名（多个），或者#{...}引用的对象类型数据，或者#{...}引用的集合",
                    "\t参数1-n（集合类型）：要删除的所有值"}),

    NONE(0, "未知", false,  null, null);

    private int minParamCount;

    private String name;
    private boolean isValid;
    private String desc;
    private String[] details;

    DataEditTypeEnum(int minParamCount, String name, boolean isValid, String desc, String[] details) {
        this.minParamCount = minParamCount;
        this.name = name;
        this.isValid = isValid;
        this.desc = desc;
        this.details = details;
    }

    public static DataEditTypeEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return NONE;
        }
        for (DataEditTypeEnum value : DataEditTypeEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
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

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public String[] getDetails() {
        return this.details;
    }
}
