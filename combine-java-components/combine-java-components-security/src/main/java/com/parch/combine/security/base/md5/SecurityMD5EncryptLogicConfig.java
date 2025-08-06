package com.parch.combine.security.base.md5;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface SecurityMD5EncryptLogicConfig extends ILogicConfig {

    @Field(key = "data", name = "待加密数据", type = FieldTypeEnum.ANY, isRequired = true)
    Object data();
}
