package com.parch.combine.components.file.operations.copy;


import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface FileCopyLogicConfig extends ILogicConfig {

    @Field(key = "source", name = "数据来源路径", type = FieldTypeEnum.TEXT, isRequired = true)
    String source();

    @Field(key = "target", name = "写入路径", type = FieldTypeEnum.TEXT, isRequired = true)
    String target();

}
