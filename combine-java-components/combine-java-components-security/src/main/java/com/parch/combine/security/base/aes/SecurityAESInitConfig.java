package com.parch.combine.security.base.aes;


import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface SecurityAESInitConfig extends IInitConfig {

    @Field(key = "key", name = "秘钥", type = FieldTypeEnum.TEXT, isRequired = true)
    String key();
}
