package com.parch.combine.data.base.general.format;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

/**
 * 函数枚举
 */
public enum DataFormatFunctionEnum implements IOptionSetting {

    JSON("JSON数据转换", true, null,
            new String[]{"\t参数1（必填）：TO_JSON | JSON_TO_LIST | JSON_TO_OBJECT"}),

    CLEAR_DUPLICATE("清理重复数据", true, "只支持集合类型",
            new String[]{"\t参数1（选填）：用于判断重复的字段，默认转为字符串比较（多个字段用逗号分割）"}),

    SORT("排序", true, "只支持集合类型",
            new String[]{"\t参数1（选填）：用于排序的字段，默认转为字符串排序（多个字段用逗号分割）,也可以使用‘-’符号表示不指定排序字段",
                         "\t参数2（选填）：正序/倒序，默认正序，可选值（asc正序，desc倒序）"}),

    TEXT_CONVERT("文本转换函数", true, "文本和模型转换",
            new String[]{"\t参数1（必填）：MODEL_TO_TEXT, MODELS_TO_TEXTS, MODELS_TO_TEXT, TEXT_TO_MODEL, TEXT_TO_MODELS, TEXTS_TO_MODELS",
                         "\t参数2（必填）：模型字段名（多个用‘,’分割）",
                         "\t参数3（必填）：列分隔符",
                         "\t参数4（选填）：行分隔符（默认为所有空行符）"}),

    GROUP("集合分组函数", true, "只支持对象集合类型",
            new String[]{"\t参数1（必填）：用于分组的字段（多个字段用逗号分割）"}),

    TO_MAP("集合转MAP函数", true, "只支持对象集合类型",
            new String[]{"\t参数1（必填）：用于作为Map Key的字段（多个字段用逗号分割）",
                         "\t参数2（选填）：用于作为Map Value的字段（默认为整个对象）"}),

    TO_TREE("集合转树结构函数", true, "只支持对象集合类型",
            new String[]{"\t参数1（必填）：用于数据的唯一标识字段名",
                         "\t参数2（必填）：用于指向父数据的唯一标识字段名",
                         "\t参数3（必填）：子数据的存储字段名"}),

    RANG("获取区间数据函数", true, "只支持集合和字符串类型",
            new String[]{"\t参数1（必填）：起始下标",
                         "\t参数2（选填）：结束下标"}),

    CUSTOM("自定义函数", true, null,
            new String[]{"\t参数1（必填）：Java类路径（必须实现 ICustomFormat 接口）",
                         "\t参数2（支持多个，选填）：自定义函数额外参数"}),

    MD5("MD5 加密", true, null, new String[]{"无参数"}),

    NONE("未知", false, null, null);

    private String name;
    private boolean isValid;
    private String desc;
    private String[] details;

    DataFormatFunctionEnum(String name, boolean isValid, String desc, String[] details) {
        this.name = name;
        this.isValid = isValid;
        this.desc = desc;
        this.details = details;
    }

    public static DataFormatFunctionEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return NONE;
        }
        for (DataFormatFunctionEnum value : DataFormatFunctionEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
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

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public String[] getDetails() {
        return this.details;
    }
}
