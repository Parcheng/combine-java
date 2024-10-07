package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.template.TextareaElementTemplateLogicConfig;
import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "textarea", name = "多行文本输入框元素", templateClass = TextareaElementTemplateLogicConfig.class)
public class TextareaElementConfig extends ElementConfig<TextareaElementTemplateLogicConfig> {

    @Field(key = "key", name = "多行文本KEY属性，在获取数据时作为字段名", type = FieldTypeEnum.TEXT)
    private String key;

    @Field(key = "value", name = "多行文本内容", type = FieldTypeEnum.TEXT)
    private String value;

    public TextareaElementConfig() {
        super(SystemElementPathTool.buildJsPath("textarea"), SystemElementPathTool.buildCssPath("textarea"),
                SystemElementPathTool.buildTemplatePath("textarea"), TextareaElementTemplateLogicConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
