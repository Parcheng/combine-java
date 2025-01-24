package com.parch.combine.gitlab.base.auth;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface GitLabAuthLoginLoginConfig extends GitlabAuthConfig {

    @Field(key = "username", name = "用户名", type = FieldTypeEnum.TEXT, isRequired = true)
    String username();

    @Field(key = "password", name = "密码", type = FieldTypeEnum.TEXT, isRequired = true)
    String password();
}
