package com.parch.combine.components.file.operations.delete;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface FileDeleteLogicConfig extends ILogicConfig {

    @Field(key = "source", name = "文件路径", type = FieldTypeEnum.TEXT, isRequired = true)
    String source();
}
