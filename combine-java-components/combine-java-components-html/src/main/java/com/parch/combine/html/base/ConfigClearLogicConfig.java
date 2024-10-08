package com.parch.combine.html.base;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

import java.util.Map;

public interface ConfigClearLogicConfig extends ILogicConfig {

    @Field(key = "configId", name = "配置ID", type = FieldTypeEnum.TEXT)
    @FieldDesc("为空则清理全部")
    String configId();
}
