package com.parch.combine.gui.core.event.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.tools.SubComponentTool;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ComponentTriggerProcessor extends AbstractTriggerProcessor {

    private String key;
    private Map<String, Object> params;
    private final String[] componentIds;

    private final CombineManager manager;

    public ComponentTriggerProcessor(CombineManager manager, JFrame frame, ComponentTriggerProcessor.Config config) {
        super(frame);
        this.key = config.key();
        if (CheckEmptyUtil.isEmpty(this.key)) {
            this.key = UUID.randomUUID().toString();
        }
        this.componentIds = config.components();
        this.params = config.params();
        if (this.params == null) {
            this.params = Collections.emptyMap();
        }

        this.manager = manager;
    }

    @Override
    public void trigger(ComponentEvent event) {
        SubComponentTool.execute(manager, key, params, componentIds);
    }

    public interface Config {

        @Field(key = "key", name = "自定义流程KEY", type = FieldTypeEnum.TEXT)
        String key();

        @Field(key = "components", name = "组件集合", type = FieldTypeEnum.COMPONENT, isArray = true)
        String[] components();

        @Field(key = "params", name = "参数", type = FieldTypeEnum.MAP)
        Map<String, Object> params();
    }
}
