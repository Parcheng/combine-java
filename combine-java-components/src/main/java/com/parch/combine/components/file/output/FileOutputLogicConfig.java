package com.parch.combine.components.file.output;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface FileOutputLogicConfig extends ILogicConfig {

    @Field(key = "source", name = "文件的数据来源", type = FieldTypeEnum.EXPRESSION, isRequired = true)
    @FieldDesc("必须是文件的字节数据")
    @FieldEg(eg = "$r.data001", desc = "获取 data001 组件的结果数据（文件字节数据）")
    Object source();

    @Field(key = "charset", name = "字符集编码", type = FieldTypeEnum.TEXT)
    @FieldDesc("注意：仅在文本数据时生效，默认根据系统环境决定")
    String charset();
}
