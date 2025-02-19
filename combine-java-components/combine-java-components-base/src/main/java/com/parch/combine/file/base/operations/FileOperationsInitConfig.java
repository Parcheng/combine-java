package com.parch.combine.file.base.operations;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.IInitConfig;

public interface FileOperationsInitConfig extends IInitConfig {

    @Field(key = "useSystemDir", name = "是否使用系统资源路径", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    @FieldDesc("使用项目中的路径路径作为基础路径")
    Boolean useSystemDir();

    @Field(key = "dir", name = "根路径", type = FieldTypeEnum.TEXT)
    String dir();
}
