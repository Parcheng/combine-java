package com.parch.combine.components.data.convert.table2text;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.components.data.text.crop.DataCropLogicConfig;
import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 文件解析逻辑配置类
 */
public class DataTableToTextLogicConfig extends LogicConfig {

    @ComponentField(key = "source", name = "数据来源", type = FieldTypeEnum.TEXT, isRequired = true)
    @ComponentFieldEg(eg = "$r.data001", desc = "读取 ID 为 data001 组件的返回结果作为文件数据")
    private String source;

    @ComponentField(key = "separator", name = "分隔符", type = FieldTypeEnum.TEXT, defaultValue = "|")
    @ComponentFieldDesc("提示：该字段用于区分表格列的分隔符")
    @ComponentFieldEg(eg = ",", desc = "文本文件转为表格数据，每一列的数据用逗号进行拆分成列")
    private String separator;

    @Override
    public void init() {
        super.init();
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
