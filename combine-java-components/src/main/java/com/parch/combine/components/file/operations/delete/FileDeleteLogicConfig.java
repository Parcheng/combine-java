package com.parch.combine.components.file.operations.delete;


import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class FileDeleteLogicConfig extends LogicConfig {

    @Field(key = "source", name = "文件路径", type = {FieldTypeEnum.TEXT, FieldTypeEnum.EXPRESSION}, isRequired = true)
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
