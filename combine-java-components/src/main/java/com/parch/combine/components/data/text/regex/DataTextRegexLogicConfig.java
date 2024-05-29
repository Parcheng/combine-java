package com.parch.combine.components.data.text.regex;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class DataTextRegexLogicConfig extends LogicConfig {

    @Field(key = "source", name = "数据源", type = {FieldTypeEnum.TEXT, FieldTypeEnum.EXPRESSION}, isRequired = true)
    @FieldDesc("提示：非文本类型会转为JSON格式处理")
    private String source;

    @Field(key = "regex", name = "正则表达式", type = {FieldTypeEnum.TEXT, FieldTypeEnum.EXPRESSION})
    private String regex;

    @Field(key = "resultMode", name = "返回结果的方式", type = FieldTypeEnum.SELECT, defaultValue = "FULL")
    @FieldSelect(enumClass = DataTextRegexResultModeEnum.class)
    private String resultMode;

    @Override
    public void init() {
        if (CheckEmptyUtil.isEmpty(resultMode)) {
            resultMode = DataTextRegexResultModeEnum.FULL.name();
        }
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getResultMode() {
        return resultMode;
    }

    public void setResultMode(String resultMode) {
        this.resultMode = resultMode;
    }
}
