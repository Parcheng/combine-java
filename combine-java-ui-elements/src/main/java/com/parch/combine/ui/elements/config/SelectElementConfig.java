package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.trigger.Trigger;
import com.parch.combine.core.ui.settings.PageSettingCanstant;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.common.OptionElementConfig;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "select", name = "下拉框元素", templateClass = AudioElementTemplateConfig.class)
public class SelectElementConfig extends ElementConfig<SelectElementTemplateConfig> {

    @Field(key = "key", name = "下拉框KEY属性，在获取数据时作为字段名", type = FieldTypeEnum.TEXT)
    private String key;

    @Field(key = "value", name = "当前选中的值", type = FieldTypeEnum.TEXT)
    private String value;

    @Field(key = "defaultText", name = "默认显示文本", type = FieldTypeEnum.TEXT)
    private String defaultText;

    @Field(key = "defaultValue", name = "默认选择的值", type = FieldTypeEnum.TEXT)
    private String defaultValue;

    @Field(key = "option", name = "下拉项配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = OptionElementConfig.class)
    private OptionElementConfig option;

    @Field(key = "triggers", name = "下拉框触发配置（用于实现多级联动，暂未不支持使用）", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef(key = PageSettingCanstant.TRIGGER_KEY)
    @Trigger
    private Object triggers;

    public SelectElementConfig() {
        super(SystemElementPathTool.buildJsPath("select"), SystemElementPathTool.buildTemplatePath("select"), SelectElementTemplateConfig.class);
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

    public OptionElementConfig getOption() {
        return option;
    }

    public void setOption(OptionElementConfig option) {
        this.option = option;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getTriggers() {
        return triggers;
    }

    public void setTriggers(Object triggers) {
        this.triggers = triggers;
    }

    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
