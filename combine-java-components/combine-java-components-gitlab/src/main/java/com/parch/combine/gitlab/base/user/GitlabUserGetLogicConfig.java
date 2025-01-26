package com.parch.combine.gitlab.base.user;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabLogicConfig;

public interface GitlabUserGetLogicConfig extends GitlabLogicConfig {

    @Field(key = "idOrName", name = "名称或ID", type = FieldTypeEnum.ANY, isRequired = true)
    Object idOrName();
}
