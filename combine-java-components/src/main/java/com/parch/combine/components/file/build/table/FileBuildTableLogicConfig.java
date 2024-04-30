package com.parch.combine.components.file.build.table;

import com.parch.combine.components.file.build.FileBuildLogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 文件表格写逻辑配置类
 */
public class FileBuildTableLogicConfig extends FileBuildLogicConfig {

    @ComponentField(key = "header", name = "表头", type = FieldTypeEnum.TEXT, isArray = true)
    @ComponentFieldDesc("个数必须与 filedNames 配置的个数一致")
    @ComponentFieldEg(eg = "[\"ID\", \"名称\", \"年龄\"]")
    private List<String> header;

    @ComponentField(key = "filedNames", name = "表格数据KEY集合", type = FieldTypeEnum.TEXT, isArray = true)
    @ComponentFieldDesc("表格每一列对应的字段取值")
    @ComponentFieldEg(eg = "[\"id\", \"name\", \"age\"]", desc = "按顺序从 data001 组件的结果中取id, name, age 字段写入表格文件数据中")
    private List<String> filedNames;

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<String> getFiledNames() {
        return filedNames;
    }

    public void setFiledNames(List<String> filedNames) {
        this.filedNames = filedNames;
    }
}
