package com.parch.combine.components.data.text.regex;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.annotations.ComponentFieldSelect;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class DataTextRegexLogicConfig extends LogicConfig {

    @ComponentField(key = "source", name = "数据源", type = FieldTypeEnum.TEXT, isRequired = true)
    @ComponentFieldDesc("提示：非文本类型会转为JSON格式处理")
    private String source;

    @ComponentField(key = "regex", name = "正则表达式", type = FieldTypeEnum.TEXT, isRequired = true)
    private String regex;

    @ComponentField(key = "resultMode", name = "返回结果的方式", type = FieldTypeEnum.SELECT, defaultValue = "FULL")
    @ComponentFieldSelect(enumClass = DataTextRegexResultModeEnum.class)
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
