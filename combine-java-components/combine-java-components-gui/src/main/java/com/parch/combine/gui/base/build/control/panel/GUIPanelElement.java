package com.parch.combine.gui.base.build.control.panel;

import com.parch.combine.gui.core.call.GUIElementCallFunctionHelper;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;

import javax.swing.JPanel;
import javax.swing.JComponent;
import java.util.Map;

public class GUIPanelElement extends AbstractGUIComponentElement<GUIPanelElementTemplate, GUIPanelElement.Config, Object> {

    private GUISubElementConfig[] subConfigs;

    public GUIPanelElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIPanelElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "panel", template, config, GUIPanelElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.template.getExternal());
        this.buildItems(panel);

        return panel;
    }

    public void buildItems(JPanel panel) {
        Object data = this.value;
        GUISubElementConfig[] items = this.config.elementConfigs;
        if (items == null) {
            return;
        }

        this.subConfigs = GUISubElementHelper.copyAndBuild(data, this.config.elementConfigs, this);
        GUISubElementHelper.setSubComponent(panel, this.subConfigs);
    }

    @Override
    public synchronized boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        this.value = data;
        return GUISubElementHelper.setValue(data, this.subConfigs);
    }

    @Override
    public Object getValue() {
        if (this.subConfigs == null) {
            return this.value;
        }

        return GUISubElementHelper.getValue(this.subConfigs);
    }

    @Override
    public IGUIElement copy() {
        return new GUIPanelElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return GUIElementCallFunctionHelper.buildElementFunction(this);
    }

    public static class Config extends GUIElementConfig<Object> {
        public GUISubElementConfig[] elementConfigs;
    }
}
