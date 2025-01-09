package com.parch.combine.gitlab.base.auth;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

import java.util.Map;

public interface GitlabAuthConfig extends ILogicConfig {

    @Field(key = "key", name = "链接KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    String key();

    @Field(key = "url", name = "主机地址", type = FieldTypeEnum.TEXT, isRequired = true)
    String url();

    @Field(key = "secretToken", name = "秘钥", type = FieldTypeEnum.TEXT)
    String secretToken();

    @Field(key = "clientConfig", name = "客户端配置", type = FieldTypeEnum.MAP)
    Map<String, Object> clientConfig();

    @Field(key = "ignoreCertificateErrors", name = "是否忽略证书错误", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean ignoreCertificateErrors();
}
