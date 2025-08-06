package com.parch.combine.security.base.aes;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface SecurityAESEncryptLogicConfig extends ILogicConfig {

    @Field(key = "data", name = "待加密数据", type = FieldTypeEnum.ANY, isRequired = true)
    Object data();

    @Field(key = "toLowercase", name = "是否转小写", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean toLowercase();
}
