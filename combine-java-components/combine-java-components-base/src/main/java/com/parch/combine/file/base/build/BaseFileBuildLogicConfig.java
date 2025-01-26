package com.parch.combine.file.base.build;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

/**
 * 文件解析逻辑配置类
 */
public interface BaseFileBuildLogicConfig extends ILogicConfig {

    @Field(key = "fileName", name = "文件名", type = FieldTypeEnum.TEXT)
    @FieldDesc("默认随机生成")
    String fileName();
}
