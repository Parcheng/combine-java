package com.parch.combine.components.web.elements.settings;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.trigger.TriggerEntity;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 页面元素设置
 */
public class SelectSettings extends BaseSettings{

    @Field(key = "key", name = "下拉框KEY属性，在获取数据时作为字段名", type = FieldTypeEnum.TEXT)
    private String key;

    @Field(key = "value", name = "当前选中的值", type = FieldTypeEnum.TEXT)
    private String value;

    @Field(key = "defaultText", name = "默认显示文本", type = FieldTypeEnum.TEXT)
    private String defaultText;

    @Field(key = "defaultValue", name = "默认选择的值", type = FieldTypeEnum.TEXT)
    private String defaultValue;

    @Field(key = "option", name = "下拉项配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = OptionSettings.class)
    private OptionSettings option;

    @Field(key = "triggers", name = "下拉框触发配置（用于实现多级联动，暂未不支持使用）", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef(key = WebSettingCanstant.TRIGGER_KEY)
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
