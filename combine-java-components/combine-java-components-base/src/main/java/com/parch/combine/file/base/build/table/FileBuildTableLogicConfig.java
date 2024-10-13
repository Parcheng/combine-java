package com.parch.combine.file.base.build.table;

import com.parch.combine.file.base.build.DefaultFileBuildLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface FileBuildTableLogicConfig extends DefaultFileBuildLogicConfig {

    @Field(key = "header", name = "表头", type = FieldTypeEnum.TEXT, isArray = true)
    @FieldDesc("个数必须与 filedNames 配置的个数一致")
    @FieldEg(eg = "[\"ID\", \"名称\", \"年龄\"]")
    String[] header();

    @Field(key = "filedNames", name = "表格数据KEY集合", type = FieldTypeEnum.TEXT, isArray = true)
    @FieldDesc("表格每一列对应的字段取值")
    @FieldEg(eg = "[\"id\", \"name\", \"age\"]", desc = "按顺序从 data001 组件的结果中取id, name, age 字段写入表格文件数据中")
    String[] filedNames();
}
