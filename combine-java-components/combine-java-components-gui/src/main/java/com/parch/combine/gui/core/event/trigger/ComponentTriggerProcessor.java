package com.parch.combine.gui.core.event.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.tools.SubComponentTool;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.Map;

public class ComponentTriggerProcessor extends AbsTriggerProcessor<ComponentTriggerProcessor.Config>{

    private CombineManager manager;

    public ComponentTriggerProcessor(CombineManager manager, JFrame frame, ComponentTriggerProcessor.Config config) {
        super(frame, config);
        this.manager = manager;
    }

    @Override
    public void trigger(ComponentEvent event) {
        SubComponentTool.execute(manager, config.getKey(), config.getParams(), config.getElementIds().toArray(new String[0]));
    }

    public static class Config {

        @Field(key = "key", name = "自定义流程KEY", type = FieldTypeEnum.TEXT)
        private String key;

        @Field(key = "elementIds", name = "组件集合", type = FieldTypeEnum.TEXT, isArray = true)
        private List<String> elementIds;

        @Field(key = "params", name = "参数", type = FieldTypeEnum.MAP)
        private Map<String, Object> params;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getElementIds() {
            return elementIds;
        }

        public void setElementIds(List<String> elementIds) {
            this.elementIds = elementIds;
        }

        public Map<String, Object> getParams() {
            return params;
        }

        public void setParams(Map<String, Object> params) {
            this.params = params;
        }
    }
}
