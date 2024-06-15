package com.parch.combine.components.file.operations.compress;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface FileCompressLogicConfig extends ILogicConfig {

    @Field(key = "source", name = "数据来源路径", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("该字段如果为路径，则表示要对该路径压缩；如果为压缩文件地址，则表示要对改压缩包解压")
    String source();

    @Field(key = "target", name = "写入路径", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("该字段如果为路径，则表示要将压缩文件解压到该路径；如果为压缩文件地址，则表示要压缩为该文件")
    String target();
}
