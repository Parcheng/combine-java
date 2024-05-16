package com.parch.combine.components.web.elements.settings;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.trigger.TriggerEntity;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 页面元素设置
 */
public class ButtonSettings extends BaseSettings{

    @Field(key = "items", name = "按钮配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    private List<ButtonItemSettings> items;

    public static class ButtonItemSettings {

        @Field(key = "type", name = "按钮阳寿类型（要与与模板一致）", type = FieldTypeEnum.OBJECT, isArray = true)
        private String type;

        @Field(key = "text", name = "按钮文本", type = FieldTypeEnum.OBJECT, isArray = true)
        private String text;

        @Field(key = "triggers", name = "按钮触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
        @FieldRef(key = WebSettingCanstant.TRIGGER_KEY)
        private List<TriggerEntity> triggers;

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

        public List<TriggerEntity> getTriggers() {
            return triggers;
        }

        public void setTriggers(List<TriggerEntity> triggers) {
            this.triggers = triggers;
        }
    }

    public List<ButtonItemSettings> getItems() {
        return items;
    }

    public void setItems(List<ButtonItemSettings> items) {
        this.items = items;
    }
}
