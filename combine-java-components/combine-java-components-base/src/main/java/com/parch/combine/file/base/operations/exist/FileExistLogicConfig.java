package com.parch.combine.file.base.operations.exist;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface FileExistLogicConfig extends ILogicConfig {

    @Field(key = "source", name = "文件路径", type = FieldTypeEnum.TEXT, isRequired = true)
    String source();
}
