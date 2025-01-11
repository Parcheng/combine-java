package com.parch.combine.gitlab.base.auth;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface GitLabAuthTokenLogicConfig extends GitlabAuthConfig {

    @Field(key = "accessToken", name = "访问Token", type = FieldTypeEnum.TEXT, isRequired = true)
    String accessToken();
}
