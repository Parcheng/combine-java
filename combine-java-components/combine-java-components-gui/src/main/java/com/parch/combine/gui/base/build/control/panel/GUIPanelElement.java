package com.parch.combine.gui.base.build.control.panel;

import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.AbsComponentElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;

import javax.swing.JPanel;
import javax.swing.JComponent;
import java.util.Map;

public class GUIPanelElement extends AbsComponentElement<GUIPanelElementTemplate, GUIPanelElement.Config> {

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
        GUISubElementConfig[] items = this.config.elementConfigs;
        if (items == null) {
            return new JComponent[0];
        }

        return GUISubElementHelper.build(data, items, this);
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        return GUISubElementHelper.setValue(data, this.config.elementConfigs);
    }

    @Override
    public Object getValue() {
        if (this.config.elementConfigs == null) {
            return this.config.data;
        }

        return GUISubElementHelper.getValue(this.config.elementConfigs);
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
        public GUISubElementConfig[] elementConfigs;
    }
}
