package com.parch.combine.gui.base.control.panel;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ElementStyleConstant;

import javax.swing.JPanel;
import javax.swing.JComponent;

import java.util.HashMap;
import java.util.Map;

public class GUIPanelElement implements IGUIElement {

    private JPanel panel = null;
    private GUIPanelElementTemplate template;
    private Config config;

    public GUIPanelElement(GUIPanelElementTemplate template, Config config) {
        this.template = template == null ? new GUIPanelElementTemplate() : template;
        this.config = config;
    }

    @Override
    public JComponent build() {
        this.panel = new JPanel(ElementStyleConstant.LEFT_FLOW_LAYOUT);
        ElementHelper.set(panel, template.getExternal());

        for (JComponent item : buildItems()) {
            this.panel.add(item);
        }

        return panel;
    }

    public JComponent[] buildItems() {
        Object data = this.config.data;
        ElementItemConfig[] items = config.elementConfigs;
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

            config.element.setData(itemData);
            body[i] = config.element.build();
        }

        return body;
    }

    @Override
    public boolean setData(Object data) {
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
    public Object getData() {
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
        return new GUIPanelElement(this.template, this.config);
    }

    public static class Config {
        public Object data;
        public ElementItemConfig[] elementConfigs;
    }

    public static class ElementItemConfig {
        public String key;
        public String dataField;
        public IGUIElement element;
    }
}
