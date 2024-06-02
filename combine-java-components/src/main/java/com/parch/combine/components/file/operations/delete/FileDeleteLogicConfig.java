package com.parch.combine.components.file.operations.delete;


import com.parch.combine.core.component.base.old.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class FileDeleteLogicConfig extends ILogicConfig {

    @Field(key = "source", name = "文件路径", type = FieldTypeEnum.TEXT, isRequired = true)
    private String source;

    @Override
    public void init() {}

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
