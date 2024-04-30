package com.parch.combine.components.web.elements.settings;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.trigger.TriggerEntity;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 页面元素设置
 */
public class SelectSettings extends BaseSettings{

    @ComponentField(key = "key", name = "下拉框KEY属性，在获取数据时作为字段名", type = FieldTypeEnum.TEXT)
    private String key;

    @ComponentField(key = "value", name = "当前选中的值", type = FieldTypeEnum.TEXT)
    private String value;

    @ComponentField(key = "defaultText", name = "默认显示文本", type = FieldTypeEnum.TEXT)
    private String defaultText;

    @ComponentField(key = "defaultValue", name = "默认选择的值", type = FieldTypeEnum.TEXT)
    private String defaultValue;

    @ComponentField(key = "option", name = "下拉项配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = OptionSettings.class)
    private OptionSettings option;

    @ComponentField(key = "triggers", name = "下拉框触发配置（用于实现多级联动，暂未不支持使用）", type = FieldTypeEnum.OBJECT, isArray = true)
    @ComponentFieldRef(key = WebSettingCanstant.TRIGGER_KEY)
    private List<TriggerEntity> triggers;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public OptionSettings getOption() {
        return option;
    }

    public void setOption(OptionSettings option) {
        this.option = option;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<TriggerEntity> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<TriggerEntity> triggers) {
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
