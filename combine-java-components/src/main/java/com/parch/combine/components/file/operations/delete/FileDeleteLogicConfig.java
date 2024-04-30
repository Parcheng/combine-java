package com.parch.combine.components.file.operations.delete;


import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class FileDeleteLogicConfig extends LogicConfig {

    @ComponentField(key = "source", name = "文件路径", type = FieldTypeEnum.TEXT, isRequired = true)
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
