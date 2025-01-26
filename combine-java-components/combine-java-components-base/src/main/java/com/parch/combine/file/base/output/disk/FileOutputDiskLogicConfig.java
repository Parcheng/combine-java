package com.parch.combine.file.base.output.disk;

import com.parch.combine.file.base.output.FileOutputLogicConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldEg;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface FileOutputDiskLogicConfig extends FileOutputLogicConfig {

    @Field(key = "targetPath", name = "目标路径", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "temp/test.txt", desc = "写入到项目 target/class/temp/test.txt 文件中")
    String targetPath();
}
