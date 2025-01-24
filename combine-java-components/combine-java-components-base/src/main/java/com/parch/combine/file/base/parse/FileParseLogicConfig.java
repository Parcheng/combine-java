package com.parch.combine.file.base.parse;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldEg;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface FileParseLogicConfig extends ILogicConfig {

    @Field(key = "source", name = "数据来源", type = FieldTypeEnum.EXPRESSION)
    @FieldEg(eg = "$r.data001", desc = "读取 ID 为 data001 组件的返回结果作为文件数据")
    Object source();
}
