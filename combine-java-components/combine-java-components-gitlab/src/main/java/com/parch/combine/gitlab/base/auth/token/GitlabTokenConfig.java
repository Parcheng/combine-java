package com.parch.combine.gitlab.base.auth.token;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.auth.GitlabAuthConfig;

public interface GitlabTokenConfig extends GitlabAuthConfig {

    @Field(key = "accessToken", name = "访问Token", type = FieldTypeEnum.TEXT, isRequired = true)
    String accessToken();
}
