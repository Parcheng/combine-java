package com.parch.combine.components.data.convert.text2table;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

/**
 * 文件解析逻辑配置类
 */
public interface DataTextToTableLogicConfig extends ILogicConfig {

    @Field(key = "source", name = "数据来源", type = FieldTypeEnum.EXPRESSION, isRequired = true)
    @FieldEg(eg = "$r.data001", desc = "读取 ID 为 data001 组件的返回结果作为文件数据")
    Object source();

    @Field(key = "separator", name = "分隔符", type = FieldTypeEnum.TEXT, defaultValue = "|")
    @FieldDesc("提示：该字段用于区分表格列的分隔符")
    @FieldEg(eg = ",", desc = "文本文件转为表格数据，每一列的数据用逗号进行拆分成列")
    String separator();
}
