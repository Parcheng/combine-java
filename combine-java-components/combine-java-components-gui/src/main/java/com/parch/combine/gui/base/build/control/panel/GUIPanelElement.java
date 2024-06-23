package com.parch.combine.gui.base.build.control.panel;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JPanel;
import javax.swing.JComponent;

import java.util.HashMap;
import java.util.Map;

public class GUIPanelElement extends AbsGUIElement<GUIPanelElementTemplate, GUIPanelElement.Config> {

    private JPanel panel = null;

    public GUIPanelElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIPanelElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "panel", template, config, GUIPanelElementTemplate.class);
    }

    @Override
    public JComponent build() {
        this.panel = new JPanel();
        super.loadTemplates(this.panel, this.sysTemplate.getExternal(), this.template.getExternal());

        for (JComponent item : buildItems()) {
            this.panel.add(item);
        }

        return panel;
    }

    public JComponent[] buildItems() {
        Object data = this.config.data;
        ElementItemConfig[] items = this.config.elementConfigs;
        if (items == null) {
            return new JComponent[0];
        }

        JComponent[] body = new JComponent[items.length];
        for (int i = 0; i < items.length; i++) {
            ElementItemConfig config = items[i];
            Object itemData = null;
            if (data != null) {
                if (CheckEmptyUtil.isEmpty(config.dataField)) {
                    itemData = data;
                } else if (data instanceof Map) {
                    itemData =  ((Map<?, ?>) data).get(config.dataField);
                }
            }

            config.element.setValue(itemData);
            body[i] = config.element.build(this.frame);
            super.registerEvents(body[i], config.events);
        }

        return body;
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        config.data = data;
        if (this.panel != null) {
            this.panel.removeAll();
            for (JComponent item : buildItems()) {
                this.panel.add(item);
            }
            this.panel.revalidate();
            this.panel.repaint();
        }

        return true;
    }

    @Override
    public Object getValue() {
        if (this.config.elementConfigs == null) {
            return this.config.data;
        }

        Map<String, Object> data = new HashMap<>();
        for (ElementItemConfig itemConfig : this.config.elementConfigs) {
            Object itemData = itemConfig.element.getData();
            if (itemConfig.key == null) {
                return itemData;
            }

            data.put(itemConfig.key, itemData);
        }

        return data.size() > 0 ? data : null;
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIPanelElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config {
        public Object data;
        public ElementItemConfig[] elementConfigs;
    }

    public static class ElementItemConfig {
        public String key;
        public String dataField;
        public IGUIElement element;
        public EventConfig[] events;
    }
}
