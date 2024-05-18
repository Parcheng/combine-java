package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.SubConfig;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.settings.PageSettingCanstant;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.common.OptionElementConfig;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "checkbox", name = "多选框元素", templateClass = CheckboxElementTemplateConfig.class)
public class CheckboxElementConfig extends ElementConfig<CheckboxElementTemplateConfig> {

    @Field(key = "layout", name = "布局 INLINE | MULTILINE", type = FieldTypeEnum.TEXT, defaultValue = "MULTILINE")
    private String layout;

    @Field(key = "key", name = "多选框的KEY属性，在获取数据时作为字段名", type = FieldTypeEnum.TEXT)
    private String key;

    @Field(key = "value", name = "当前选中的值", type = FieldTypeEnum.TEXT)
    private String value;

    @Field(key = "disabled", name = "是否禁选", type = FieldTypeEnum.BOOLEAN)
    private Boolean disabled;

    @Field(key = "option", name = "多选项配置", type = FieldTypeEnum.OBJECT, isRequired = true)
    @FieldObject(type = OptionElementConfig.class)
    private OptionElementConfig option;

    @Field(key = "triggers", name = "多选框触发配置（用于实现多级联动，暂未不支持使用", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef(key = PageSettingCanstant.TRIGGER_KEY)
    private Object triggers;

    public CheckboxElementConfig() {
        super(SystemElementPathTool.buildJsPath("checkbox"), SystemElementPathTool.buildTemplatePath("checkbox"), CheckboxElementTemplateConfig.class);
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

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
