package com.parch.combine.components.data.text.regex;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

/**
 * 枚举
 */
public enum DataTextRegexResultModeEnum implements IOptionSetting {

    FULL("全字符", "返回正则匹配的全字符", true),

    GROUPS("字符分组集合", "返回正则表达式中所有括号内的内容", true),

    NONE("未知", null, false);

    private String name;
    private String desc;
    private boolean isValid;

    DataTextRegexResultModeEnum(String name, String desc, boolean isValid) {
        this.name = name;
        this.desc = desc;
        this.isValid = isValid;
    }


    public static DataTextRegexResultModeEnum get(String mode) {
        if (CheckEmptyUtil.isEmpty(mode)) {
            return NONE;
        }
        for (DataTextRegexResultModeEnum value : DataTextRegexResultModeEnum.values()) {
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
    public String getDesc() {
        return desc;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }
}
