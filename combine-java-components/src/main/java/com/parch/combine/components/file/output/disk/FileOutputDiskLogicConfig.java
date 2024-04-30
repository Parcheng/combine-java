package com.parch.combine.components.file.output.disk;

import com.parch.combine.components.file.output.FileOutputLogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 文件保存逻辑配置类
 */
public class FileOutputDiskLogicConfig extends FileOutputLogicConfig {

    @ComponentField(key = "targetPath", name = "目标路径", type = FieldTypeEnum.TEXT, isRequired = true)
    @ComponentFieldEg(eg = "temp/test.txt", desc = "写入到项目 target/class/temp/test.txt 文件中")
    private String targetPath;

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }
}
