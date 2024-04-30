package com.parch.combine.components.file.parse;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 文件解析逻辑配置类
 */
public abstract class FileParseLogicConfig extends LogicConfig {

    @ComponentField(key = "source", name = "数据来源", type = FieldTypeEnum.TEXT, isRequired = true)
    @ComponentFieldEg(eg = "$r.data001", desc = "读取 ID 为 data001 组件的返回结果作为文件数据")
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
