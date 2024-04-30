package com.parch.combine.component.web.elements.settings;

import com.parch.combine.component.web.elements.trigger.TriggerEntity;

import java.util.List;

/**
 * 页面元素设置
 */
public class ButtonSettings extends BaseSettings{

    private List<ButtonItemSettings> items;

    public static class ButtonItemSettings {

        private String type;

        private String text;

        private TriggerEntity trigger;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public TriggerEntity getTrigger() {
            return trigger;
        }

        public void setTrigger(TriggerEntity trigger) {
            this.trigger = trigger;
        }
    }

    public List<ButtonItemSettings> getItems() {
        return items;
    }

    public void setItems(List<ButtonItemSettings> items) {
        this.items = items;
    }
}
