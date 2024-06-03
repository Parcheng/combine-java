package com.parch.combine.components.file.input.open;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface FileInputOpenLogicConfig extends ILogicConfig {

    @Field(key = "path", name = "文件路径", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "my/test.txt", desc = "打开项目资源目录下 target/class/files/my/test.txt 文件")
    String path();
}
