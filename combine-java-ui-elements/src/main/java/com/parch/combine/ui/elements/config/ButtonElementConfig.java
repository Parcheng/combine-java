package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.base.trigger.Trigger;
import com.parch.combine.core.ui.settings.PageSettingCanstant;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "button", name = "按钮组元素", templateClass = ButtonElementTemplateConfig.class)
public class ButtonElementConfig extends ElementConfig<ButtonElementTemplateConfig> {

    @Field(key = "items", name = "按钮配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    private List<ButtonItemSettings> items;

    public ButtonElementConfig() {
        super(SystemElementPathTool.buildJsPath("button"), SystemElementPathTool.buildTemplatePath("button"), ButtonElementTemplateConfig.class);
    }

    public static class ButtonItemSettings {

        @Field(key = "type", name = "按钮阳寿类型（要与与模板一致）", type = FieldTypeEnum.OBJECT, isArray = true)
        private String type;

        @Field(key = "text", name = "按钮文本", type = FieldTypeEnum.OBJECT, isArray = true)
        private String text;

        @Field(key = "triggers", name = "按钮触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
        @FieldRef(key = PageSettingCanstant.TRIGGER_KEY)
        @Trigger
        private Object triggers;

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

        public Object getTriggers() {
            return triggers;
        }

        public void setTriggers(Object triggers) {
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
