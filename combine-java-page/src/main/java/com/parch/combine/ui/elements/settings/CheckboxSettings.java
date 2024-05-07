package com.parch.combine.ui.elements.settings;

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
public class CheckboxSettings extends BaseSettings{

    @Field(key = "layout", name = "布局 INLINE | MULTILINE", type = FieldTypeEnum.TEXT, defaultValue = "MULTILINE")
    private String layout;

    @Field(key = "key", name = "多选框的KEY属性，在获取数据时作为字段名", type = FieldTypeEnum.TEXT)
    private String key;

    @Field(key = "value", name = "当前选中的值", type = FieldTypeEnum.TEXT)
    private String value;

    @Field(key = "disabled", name = "是否禁选", type = FieldTypeEnum.BOOLEAN)
    private Boolean disabled;

    @Field(key = "option", name = "多选项配置", type = FieldTypeEnum.OBJECT, isRequired = true)
    @FieldObject(type = OptionSettings.class)
    private OptionSettings option;

    @Field(key = "triggers", name = "多选框触发配置（用于实现多级联动，暂未不支持使用", type = FieldTypeEnum.OBJECT, isArray = true)
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

    public static class Option {
        @Field(key = "value", name = "选项值", type = FieldTypeEnum.TEXT, isRequired = true)
        private String value;

        @Field(key = "text", name = "选项显示文本", type = FieldTypeEnum.TEXT, isRequired = true)
        private String text;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public List<TriggerEntity> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<TriggerEntity> triggers) {
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
