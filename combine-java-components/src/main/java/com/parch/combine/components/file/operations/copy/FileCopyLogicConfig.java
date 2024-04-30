package com.parch.combine.components.file.operations.copy;


import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class FileCopyLogicConfig extends LogicConfig {

    @ComponentField(key = "source", name = "数据来源路径", type = FieldTypeEnum.TEXT, isRequired = true)
    private String source;

    @ComponentField(key = "target", name = "写入路径", type = FieldTypeEnum.TEXT, isRequired = true)
    private String target;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
