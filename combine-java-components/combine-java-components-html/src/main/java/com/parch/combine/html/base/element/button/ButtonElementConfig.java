package com.parch.combine.html.base.element.button;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.element.ElementConfig;
import com.parch.combine.ui.core.base.SubConfig;
import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.base.trigger.Trigger;
import com.parch.combine.ui.core.base.trigger.TriggerCallFlowConfig;
import com.parch.combine.ui.core.base.trigger.TriggerCallFuncConfig;
import com.parch.combine.ui.core.base.trigger.TriggerCallUrlConfig;
import com.parch.combine.ui.core.base.trigger.TriggerCustomConfig;
import com.parch.combine.ui.core.base.trigger.TriggerLoadConfig;
import com.parch.combine.ui.core.base.trigger.TriggerLoadDataConfig;
import com.parch.combine.ui.core.base.trigger.TriggerSkipConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "button", name = "按钮组元素", templateClass = ButtonElementTemplateConfig.class)
public class ButtonElementConfig extends ElementConfig<ButtonElementTemplateConfig> {

    @Field(key = "items", name = "按钮配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldObject(ButtonItemSettings.class)
    private List<ButtonItemSettings> items;

    public ButtonElementConfig() {
        super(SystemElementPathTool.buildJsPath("button"), SystemElementPathTool.buildCssPath("button"),
                SystemElementPathTool.buildTemplatePath("button"), ButtonElementTemplateConfig.class);
    }

    @SubConfig
    public static class ButtonItemSettings {

        @Field(key = "type", name = "按钮样式类型（要与与模板一致）", type = FieldTypeEnum.OBJECT, isArray = true)
        private String type;

        @Field(key = "size", name = "按钮大小（可选值1-4）", type = FieldTypeEnum.NUMBER, isArray = true)
        private Integer size;

        @Field(key = "text", name = "按钮文本", type = FieldTypeEnum.OBJECT, isArray = true)
        private String text;

        @Field(key = "triggers", name = "按钮触发配置", type = FieldTypeEnum.TRIGGER, isArray = true)
        @FieldRef({TriggerCallFlowConfig.class, TriggerCallFuncConfig.class, TriggerCallUrlConfig.class, 
            TriggerCustomConfig.class, TriggerLoadConfig.class, TriggerLoadDataConfig.class, TriggerSkipConfig.class})
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

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public List<ButtonItemSettings> getItems() {
        return items;
    }

    public void setItems(List<ButtonItemSettings> items) {
        this.items = items;
    }
}
