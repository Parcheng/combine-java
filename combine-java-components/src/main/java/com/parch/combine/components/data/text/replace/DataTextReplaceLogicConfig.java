package com.parch.combine.components.data.text.replace;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.annotations.ComponentFieldSelect;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class DataTextReplaceLogicConfig extends LogicConfig {

    @ComponentField(key = "isReplace", name = "是否替换掉源数据", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    private Boolean isReplace;

    @ComponentField(key = "mode", name = "替换方式", type = FieldTypeEnum.SELECT, defaultValue = "ALL")
    @ComponentFieldSelect(enumClass = DataTextReplaceModeEnum.class)
    private String mode;

    @ComponentField(key = "source", name = "数据源", type = FieldTypeEnum.TEXT, isRequired = true)
    @ComponentFieldDesc("支持结构对象、集合和文本类型")
    private String source;

    @ComponentField(key = "oldText", name = "旧文本", type = FieldTypeEnum.TEXT, isRequired = true)
    private String oldText;

    @ComponentField(key = "newText", name = "新文本", type = FieldTypeEnum.TEXT, isRequired = true)
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
