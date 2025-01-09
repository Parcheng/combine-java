package com.parch.combine.gitlab.base;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.IInitConfig;

import java.util.Map;

public interface GitlabInitConfig extends IInitConfig {

    @Field(key = "url", name = "主机地址", type = FieldTypeEnum.TEXT, isRequired = true)
    String url();

    @Field(key = "version", name = "版本", type = FieldTypeEnum.NUMBER, defaultValue = "4")
    Integer version();

    @Field(key = "accessToken", name = "访问Token", type = FieldTypeEnum.TEXT, isRequired = true)
    String accessToken();

    @Field(key = "secretToken", name = "秘钥", type = FieldTypeEnum.TEXT)
    String secretToken();

    @Field(key = "clientConfig", name = "客户端配置", type = FieldTypeEnum.MAP)
    Map<String, Object> clientConfig();
}
