package com.parch.combine.html.base;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface ConfigClearLogicConfig extends ILogicConfig {

    @Field(key = "configId", name = "配置ID", type = FieldTypeEnum.TEXT)
    @FieldDesc("为空则清理全部")
    String configId();
}
