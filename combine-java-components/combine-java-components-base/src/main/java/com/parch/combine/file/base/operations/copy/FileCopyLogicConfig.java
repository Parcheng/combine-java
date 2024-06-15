package com.parch.combine.file.base.operations.copy;


import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface FileCopyLogicConfig extends ILogicConfig {

    @Field(key = "source", name = "数据来源路径", type = FieldTypeEnum.TEXT, isRequired = true)
    String source();

    @Field(key = "target", name = "写入路径", type = FieldTypeEnum.TEXT, isRequired = true)
    String target();

}
