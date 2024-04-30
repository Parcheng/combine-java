package com.parch.combine.components.file.input.open;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 文件逻辑配置类
 */
public class FileInputOpenLogicConfig extends LogicConfig {

    @ComponentField(key = "path", name = "文件路径", type = FieldTypeEnum.TEXT, isRequired = true)
    @ComponentFieldEg(eg = "my/test.txt", desc = "打开项目资源目录下 target/class/files/my/test.txt 文件")
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
