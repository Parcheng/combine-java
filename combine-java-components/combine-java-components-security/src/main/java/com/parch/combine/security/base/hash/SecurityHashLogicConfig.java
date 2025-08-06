package com.parch.combine.security.base.hash;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface SecurityHashLogicConfig extends ILogicConfig {

    @Field(key = "data", name = "待加密数据", type = FieldTypeEnum.ANY, isRequired = true)
    Object data();

    @Field(key = "digits", name = "Hash结果位数", type = FieldTypeEnum.NUMBER, defaultValue = "64")
    @FieldDesc("目前仅支持:32,64,128")
    Integer digits();
}
