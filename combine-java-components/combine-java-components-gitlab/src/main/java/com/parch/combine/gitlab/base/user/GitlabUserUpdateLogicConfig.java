package com.parch.combine.gitlab.base.user;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabLogicConfig;

public interface GitlabUserUpdateLogicConfig extends GitlabLogicConfig {

    @Field(key = "idOrName", name = "名称或ID", type = FieldTypeEnum.ANY, isRequired = true)
    Object idOrName();

    @Field(key = "password", name = "密码", type = FieldTypeEnum.TEXT, isRequired = true)
    String password();
}
