package com.parch.combine.gitlab.base.auth.login;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.auth.GitlabAuthConfig;

public interface GitLabAuthLoginLoginConfig extends GitlabAuthConfig {

    @Field(key = "username", name = "用户名", type = FieldTypeEnum.TEXT, isRequired = true)
    String username();

    @Field(key = "password", name = "密码", type = FieldTypeEnum.TEXT, isRequired = true)
    String password();
}
