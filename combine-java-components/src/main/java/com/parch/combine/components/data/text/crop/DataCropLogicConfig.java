package com.parch.combine.components.data.text.crop;

import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 文件内容读取逻辑配置类
 */
public class DataCropLogicConfig extends LogicConfig {

    @Field(key = "source", name = "数据来源", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "$r.data001", desc = "读取 ID 为 data001 组件的返回结果作为文件数据")
    private String source;

    @Field(key = "startRow", name = "从第几行开始读取", type = FieldTypeEnum.NUMBER, defaultValue = "1")
    @FieldEg(eg = "2", desc = "从第二行开始读取，可以用于跳过表头")
    private Integer startRow;

    @Field(key = "startIndex", name = "从行的第几项开始读取", type = FieldTypeEnum.NUMBER, defaultValue = "1")
    @FieldEg(eg = "1", desc = "从首行第一项开始读取")
    private Integer startIndex;

    @Field(key = "startSkipCount", name = "每行跳过前几项", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    @FieldDesc("提示：表格文件表示跳过前几列，文本文件表示跳过前几个字符")
    @FieldEg(eg = "5", desc = "读取时每行跳过前五项")
    private Integer startSkipCount;

    @Field(key = "endDiscardCount", name = "每行丢弃掉后几项", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    @FieldEg(eg = "5", desc = "读取时每行舍弃掉最后五项")
    private Integer endDiscardCount;

    @Override
    public void init() {
        if (getStartRow() == null) {
            setStartRow(1);
        }
        if (getStartIndex() == null) {
            setStartIndex(1);
        }
        if (getStartSkipCount() == null) {
            setStartSkipCount(0);
        }
        if (getEndDiscardCount() == null) {
            setEndDiscardCount(0);
        }
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getStartSkipCount() {
        return startSkipCount;
    }

    public void setStartSkipCount(Integer startSkipCount) {
        this.startSkipCount = startSkipCount;
    }

    public Integer getEndDiscardCount() {
        return endDiscardCount;
    }

    public void setEndDiscardCount(Integer endDiscardCount) {
        this.endDiscardCount = endDiscardCount;
    }
}
