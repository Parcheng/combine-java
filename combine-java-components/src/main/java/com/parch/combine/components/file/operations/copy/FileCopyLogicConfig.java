package com.parch.combine.components.file.operations.copy;


import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class FileCopyLogicConfig extends LogicConfig {

    @Field(key = "source", name = "数据来源路径", type = FieldTypeEnum.TEXT, isRequired = true)
    private String source;

    @Field(key = "target", name = "写入路径", type = FieldTypeEnum.TEXT, isRequired = true)
    private String target;

    @Override
    public void init() {}

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
