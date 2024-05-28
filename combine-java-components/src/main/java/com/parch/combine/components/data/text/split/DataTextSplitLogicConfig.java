package com.parch.combine.components.data.text.split;

import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class DataTextSplitLogicConfig extends LogicConfig {

    @Field(key = "isReplace", name = "是否替换掉源数据", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean isReplace;

    @Field(key = "source", name = "数据源", type = FieldTypeEnum.TEXT, hasExpression = true, isRequired = true)
    @FieldDesc("注意：非文本类型会先转成JSON再处理")
    private String source;

    @Field(key = "regex", name = "拆分的正则表达式", type = FieldTypeEnum.TEXT, isRequired = true)
    private String regex;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public void init() {
        if (isReplace == null) {
            isReplace = false;
        }
    }

    public Boolean getIsReplace() {
        return isReplace;
    }

    public void setIsReplace(Boolean replace) {
        isReplace = replace;
    }

    public Boolean getReplace() {
        return isReplace;
    }

    public void setReplace(Boolean replace) {
        isReplace = replace;
    }
}
