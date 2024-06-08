package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.base.trigger.Trigger;
import com.parch.combine.core.ui.settings.PageSettingCanstant;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.common.OptionElementConfig;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "radio", name = "单选框元素", templateClass = RadioElementTemplateConfig.class)
public class RadioElementConfig extends ElementConfig<RadioElementTemplateConfig> {

    @Field(key = "layout", name = "布局 INLINE | MULTILINE", type = FieldTypeEnum.TEXT, defaultValue = "MULTILINE")
    private String layout;

    @Field(key = "key", name = "单选框的KEY属性，在获取数据时作为字段名", type = FieldTypeEnum.TEXT)
    private String key;

    @Field(key = "value", name = "当前选中的值", type = FieldTypeEnum.TEXT)
    private String value;

    @Field(key = "option", name = "单选项配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(OptionElementConfig.class)
    private OptionElementConfig option;

    @Field(key = "disabled", name = "是否禁选", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean disabled;

    @Field(key = "triggers", name = "单选框触发配置（用于实现多级联动，暂未不支持使用）", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef(key = PageSettingCanstant.TRIGGER_KEY)
    @Trigger
    private Object triggers;

    public RadioElementConfig() {
        super(SystemElementPathTool.buildJsPath("radio"), SystemElementPathTool.buildCssPath("radio"),
                SystemElementPathTool.buildTemplatePath("radio"), RadioElementTemplateConfig.class);
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

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public Object getTriggers() {
        return triggers;
    }

    public void setTriggers(Object triggers) {
        this.triggers = triggers;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
