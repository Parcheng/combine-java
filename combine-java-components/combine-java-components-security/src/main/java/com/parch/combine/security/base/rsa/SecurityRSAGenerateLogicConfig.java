package com.parch.combine.security.base.rsa;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface SecurityRSAGenerateLogicConfig extends ILogicConfig {

    @Field(key = "keySize", name = "KEY长度", type = FieldTypeEnum.NUMBER, defaultValue = "2048")
    Integer keySize();
}
