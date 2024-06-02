package com.parch.combine.components.file.input.open;

import com.parch.combine.core.component.base.old.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 文件逻辑配置类
 */
public class FileInputOpenLogicConfig extends ILogicConfig {

    @Field(key = "path", name = "文件路径", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "my/test.txt", desc = "打开项目资源目录下 target/class/files/my/test.txt 文件")
    private String path;

    @Override
    public void init() {}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
