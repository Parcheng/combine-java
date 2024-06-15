package com.parch.combine.components.file.input.open;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.IInitConfig;

public interface FileInputOpenInitConfig extends IInitConfig {

    @Field(key = "useSystemDir", name = "是否使用系统资源路径", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    @FieldDesc("使用项目中的路径路径作为基础路径")
    Boolean useSystemDir();

    @Field(key = "dir", name = "根路径", type = FieldTypeEnum.TEXT)
    String dir();

    @Field(key = "max", name = "文件大小限制（MB）", type = FieldTypeEnum.NUMBER)
    Integer max();

}
