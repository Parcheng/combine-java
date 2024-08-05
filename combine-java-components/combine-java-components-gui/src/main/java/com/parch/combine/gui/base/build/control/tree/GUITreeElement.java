package com.parch.combine.gui.base.build.control.tree;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.EventConfig;
import com.parch.combine.gui.core.event.GUIEventTypeEnum;
import com.parch.combine.gui.core.event.InternalEventConfig;
import com.parch.combine.gui.core.event.trigger.GUITriggerTypeEnum;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.config.ElementGridConfig;
import com.parch.combine.gui.core.style.enums.GridFillEnum;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class GUITreeElement extends AbstractGUIComponentElement<GUITreeElementTemplate, GUITreeElement.Config, String> {

    private Map<String, JPanel> childrenMap;
    private Map<String, String> keyUpLevelMap;

    public GUITreeElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUITreeElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "tree", template, config, GUITreeElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.template.getExternal());
        this.buildItems(panel);
        return panel;
    }

    private void buildItems(JPanel parent) {
        this.childrenMap = new HashMap<>(16);
        this.keyUpLevelMap = new HashMap<>(16);

        int line = 1;
        for (ItemConfig itemConfig : config.items) {
            this.buildItem(parent, null, itemConfig, 0, line);
            line+=2;
        }
        this.checked();
    }

    private void buildItem(JPanel parent, String parentKey, ItemConfig config, int layer, int line) {
        if (config == null || this.childrenMap == null || this.keyUpLevelMap == null) {
            return;
        }

        if (CheckEmptyUtil.isEmpty(config.key)) {
            return;
        }

        JButton text = new JButton(config.text);
        super.addSubComponent(parent, text, this.getElementConfigByLayer(layer), this.buildGridConfig(line));
        super.registerEvents(text, this.buildEvent(config.key));
        super.registerEvents(text, this.config.events);

        JPanel childrenPanel = new JPanel();
        super.addSubComponent(parent, childrenPanel, this.template.getChildren(), this.buildGridConfig(line+1));
        childrenPanel.setVisible(false);

        if (config.children != null && config.children.length > 0) {
            int nextLayer = layer + 1;
            int childrenLine = 1;
            for (ItemConfig itemConfig : config.children) {
                this.buildItem(childrenPanel, config.key, itemConfig, nextLayer, childrenLine);
                childrenLine += 2;
            }
        }

        this.childrenMap.put(config.key, childrenPanel);
        this.keyUpLevelMap.put(config.key, parentKey);
    }

    private ElementGridConfig buildGridConfig(int line) {
        ElementGridConfig itemGridConfig = new ElementGridConfig();
        itemGridConfig.setFill(GridFillEnum.HORIZONTAL.getKey());
        itemGridConfig.setWeightX(1D);
        itemGridConfig.setPositionX(1);
        itemGridConfig.setPositionY(line);
        return itemGridConfig;
    }

    private ElementConfig getElementConfigByLayer(int layer) {
        ElementConfig[] templateItems = this.template.getTexts();
        if (CheckEmptyUtil.isEmpty(templateItems)) {
            return new ElementConfig();
        }

        return templateItems[(layer)%templateItems.length];
    }

    private EventConfig buildEvent(String key) {
        return new InternalEventConfig(null, GUIEventTypeEnum.CLICK,
                GUITriggerTypeEnum.INTERNAL, event -> this.setValue(key));
    }

    private void checked() {
        if (this.childrenMap == null || this.keyUpLevelMap == null) {
            return;
        }

        this.childrenMap.values().forEach(item -> item.setVisible(false));
        if (this.value == null) {
            return;
        }

        String currKey = this.value;
        JPanel childrenPanel = this.childrenMap.get(currKey);
        while (currKey != null) {
            if (childrenPanel != null) {
                childrenPanel.setVisible(true);
            }

            currKey = this.keyUpLevelMap.get(currKey);
            if (currKey == null) {
                childrenPanel = null;
                continue;
            }

            childrenPanel = this.childrenMap.get(currKey);
        }
    }

    @Override
    public boolean setValue(Object data) {
        this.value = data == null ? null : data.toString();
        this.checked();
        return true;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUITreeElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String> {
        public ItemConfig[] items;
        public EventConfig[] events;
    }

    public static class ItemConfig {
        public String key;
        public String text;
        public ItemConfig[] children;
    }
}
