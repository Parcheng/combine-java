package com.parch.combine.component.web.elements.settings;

import com.parch.combine.component.web.elements.trigger.TriggerEntity;

import java.util.List;

/**
 * 页面元素设置
 */
public class CheckboxSettings extends BaseSettings{

    private String layout;

    private String key;

    private String value;

    private Boolean disabled;

    private List<Option> options;

    /**
     * 用于实现多级联动
     */
    private TriggerEntity trigger;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static class Option {
        private String value;
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

    public TriggerEntity getTrigger() {
        return trigger;
    }

    public void setTrigger(TriggerEntity trigger) {
        this.trigger = trigger;
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
