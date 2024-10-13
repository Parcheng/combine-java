package com.parch.combine.file.base.build.pdf;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

public enum PdfPageTypeEnum implements IOptionSetting {

    A1("A1", true),

    A2("A2", true),

    A3("A3", true),

    A4("A4", true),

    A5("A5", true),

    A6("A6", true),

//    CUSTOM("自定义", true),

    NONE("未知", false);

    private String name;
    private boolean isValid;

    PdfPageTypeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }


    public static PdfPageTypeEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return NONE;
        }
        for (PdfPageTypeEnum value : PdfPageTypeEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
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
    public boolean isValid() {
        return isValid;
    }
}
