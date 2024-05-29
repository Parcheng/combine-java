package com.parch.combine.components.file.parse;

import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 文件解析逻辑配置类
 */
public abstract class FileParseLogicConfig extends LogicConfig {

    @Field(key = "source", name = "数据来源", type = FieldTypeEnum.EXPRESSION, isRequired = true)
    @FieldEg(eg = "$r.data001", desc = "读取 ID 为 data001 组件的返回结果作为文件数据")
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
