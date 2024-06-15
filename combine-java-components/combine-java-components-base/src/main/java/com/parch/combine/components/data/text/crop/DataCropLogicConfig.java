package com.parch.combine.components.data.text.crop;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface DataCropLogicConfig extends ILogicConfig {

    @Field(key = "source", name = "数据来源", type = FieldTypeEnum.EXPRESSION, isRequired = true)
    @FieldEg(eg = "$r.data001", desc = "读取 ID 为 data001 组件的返回结果作为文件数据")
    Object source();

    @Field(key = "startRow", name = "从第几行开始读取", type = FieldTypeEnum.NUMBER, defaultValue = "1")
    @FieldEg(eg = "2", desc = "从第二行开始读取，可以用于跳过表头")
    Integer startRow();

    @Field(key = "startIndex", name = "从行的第几项开始读取", type = FieldTypeEnum.NUMBER, defaultValue = "1")
    @FieldEg(eg = "1", desc = "从首行第一项开始读取")
    Integer startIndex();

    @Field(key = "startSkipCount", name = "每行跳过前几项", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    @FieldDesc("提示：表格文件表示跳过前几列，文本文件表示跳过前几个字符")
    @FieldEg(eg = "5", desc = "读取时每行跳过前五项")
    Integer startSkipCount();

    @Field(key = "endDiscardCount", name = "每行丢弃掉后几项", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    @FieldEg(eg = "5", desc = "读取时每行舍弃掉最后五项")
    Integer endDiscardCount();
}
