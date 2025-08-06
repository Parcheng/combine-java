package com.parch.combine.security.base.rsa;


import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface SecurityRSAEncryptInitConfig extends IInitConfig {

    @Field(key = "publicKey", name = "公钥", type = FieldTypeEnum.TEXT, isRequired = true)
    String publicKey();
}
