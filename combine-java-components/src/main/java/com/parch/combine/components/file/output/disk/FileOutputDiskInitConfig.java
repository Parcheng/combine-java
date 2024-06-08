package com.parch.combine.components.file.output.disk;

import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface FileOutputDiskInitConfig extends IInitConfig {

    @Field(key = "useSystemDir", name = "是否使用系统资源路径", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    @FieldDesc("使用项目中的路径路径作为基础路径")
    Boolean useSystemDir();

    @Field(key = "dir", name = "根目录", type = FieldTypeEnum.TEXT)
    String dir();
}
