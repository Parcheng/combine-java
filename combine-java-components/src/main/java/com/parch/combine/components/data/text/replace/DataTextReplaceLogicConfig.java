package com.parch.combine.components.data.text.replace;

import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class DataTextReplaceLogicConfig extends LogicConfig {

    @Field(key = "isReplace", name = "是否替换掉源数据", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    private Boolean isReplace;

    @Field(key = "mode", name = "替换方式", type = FieldTypeEnum.SELECT, defaultValue = "ALL")
    @FieldSelect(enumClass = DataTextReplaceModeEnum.class)
    private String mode;

    @Field(key = "source", name = "数据源", type = FieldTypeEnum.TEXT, hasExpression = true, isRequired = true)
    @FieldDesc("支持结构对象、集合和文本类型")
    private String source;

    @Field(key = "oldText", name = "旧文本", type = FieldTypeEnum.TEXT, hasExpression = true, isRequired = true)
    private String oldText;

    @Field(key = "newText", name = "新文本", type = FieldTypeEnum.TEXT, hasExpression = true, isRequired = true)
    private String newText;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getOldText() {
        return oldText;
    }

    public void setOldText(String oldText) {
        this.oldText = oldText;
    }

    public String getNewText() {
        return newText;
    }

    public void setNewText(String newText) {
        this.newText = newText;
    }

    @Override
    public void init() {
        if (isReplace == null) {
            isReplace = true;
        }
        if (mode == null) {
            mode = DataTextReplaceModeEnum.ALL.name();
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
