package com.parch.combine.security.base.aes;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface SecurityAESDecryptLogicConfig extends ILogicConfig {

    @Field(key = "ciphertext", name = "密文", type = FieldTypeEnum.TEXT, isRequired = true)
    String ciphertext();
}
