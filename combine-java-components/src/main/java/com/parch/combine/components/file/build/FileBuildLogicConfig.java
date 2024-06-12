package com.parch.combine.components.file.build;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 文件解析逻辑配置类
 */
public interface FileBuildLogicConfig extends ILogicConfig {

    @Field(key = "fileName", name = "文件名", type = FieldTypeEnum.TEXT, defaultValue = "随机生成")
    String fileName();

    @Field(key = "source", name = "数据来源", type = FieldTypeEnum.EXPRESSION, isRequired = true)
    @FieldDesc({"构建表格：数据必须是对象或者对象集合的格式", "构建文本：数据可以是任意类型，但都会转为文本处理"})
    @FieldEg(eg = "$r.data001", desc = "要将ID为 data001 组件的结果作为数据源")
    Object source();
}
