package com.parch.combine.gui.core.style.enums;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

public enum BorderTypeEnum implements IOptionSetting {

    LINE("线边框", true),
    BEVEL("斜角边框", true),
    ETCHED("浮雕边框", true),
    STROKE("描边边框", true),
    SOFT_BEVEL("柔和斜角边框", true),
    MATTE("图像边框", true);

    private String name;
    private boolean isValid;

    BorderTypeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    public static BorderTypeEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return LINE;
        }
        for (BorderTypeEnum value : BorderTypeEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
                return value;
            }
        }
        return LINE;
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
    public boolean isValid() {
        return this.isValid;
    }
}
