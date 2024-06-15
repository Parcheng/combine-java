package com.parch.combine.file.base.output.download;

import com.parch.combine.file.base.output.FileOutputLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface FileOutputDownloadLogicConfig extends FileOutputLogicConfig {

    @Field(key = "name", name = "文件名称", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "#{name}", desc = "从入参的 name 字段作为文件名称")
    String name();

    @Field(key = "postfix", name = "文件后缀", type = FieldTypeEnum.TEXT)
    @FieldEg(eg = "txt", desc = "文件为TXT文件")
    String postfix();
}
