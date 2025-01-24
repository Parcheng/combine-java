package com.parch.combine.gitlab.base.auth;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface GitLabAuthTokenLogicConfig extends GitlabAuthConfig {

    @Field(key = "accessToken", name = "访问Token", type = FieldTypeEnum.TEXT, isRequired = true)
    String accessToken();
}
