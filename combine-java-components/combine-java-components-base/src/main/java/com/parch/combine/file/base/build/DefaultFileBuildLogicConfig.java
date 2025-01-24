package com.parch.combine.file.base.build;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldEg;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

/**
 * 文件解析逻辑配置类
 */
public interface DefaultFileBuildLogicConfig extends BaseFileBuildLogicConfig {

    @Field(key = "source", name = "数据来源", type = FieldTypeEnum.EXPRESSION, isRequired = true)
    @FieldDesc({"构建表格：数据必须是对象或者对象集合的格式", "构建文本：数据可以是任意类型，但都会转为文本处理"})
    @FieldEg(eg = "$r.data001", desc = "要将ID为 data001 组件的结果作为数据源")
    Object source();
}
