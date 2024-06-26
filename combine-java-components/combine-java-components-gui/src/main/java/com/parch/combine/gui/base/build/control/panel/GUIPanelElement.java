package com.parch.combine.gui.base.build.control.panel;

import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;

import javax.swing.JPanel;
import javax.swing.JComponent;
import java.util.Map;

public class GUIPanelElement extends AbstractGUIComponentElement<GUIPanelElementTemplate, GUIPanelElement.Config, Object> {

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
        Object data = this.value;
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

        this.value = data;
        return GUISubElementHelper.setValue(data, this.config.elementConfigs);
    }

    @Override
    public Object getValue() {
        if (this.config.elementConfigs == null) {
            return this.value;
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

    public static class Config extends GUIElementConfig<Object> {
        public GUISubElementConfig[] elementConfigs;
    }
}
