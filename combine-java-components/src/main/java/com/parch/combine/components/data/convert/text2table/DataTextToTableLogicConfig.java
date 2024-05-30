package com.parch.combine.components.data.convert.text2table;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 文件解析逻辑配置类
 */
public class DataTextToTableLogicConfig extends LogicConfig {

    @Field(key = "source", name = "数据来源", type = {FieldTypeEnum.TEXT, FieldTypeEnum.EXPRESSION}, isRequired = true)
    @FieldEg(eg = "$r.data001", desc = "读取 ID 为 data001 组件的返回结果作为文件数据")
    private String source;

    @Field(key = "separator", name = "分隔符", type = FieldTypeEnum.TEXT, defaultValue = "|")
    @FieldDesc("提示：该字段用于区分表格列的分隔符")
    @FieldEg(eg = ",", desc = "文本文件转为表格数据，每一列的数据用逗号进行拆分成列")
    private String separator;

    @Override
    public void init() {
        if (CheckEmptyUtil.isEmpty(getSeparator())) {
            setSeparator("|");
        }
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
