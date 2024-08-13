package com.parch.combine.gui.base.build.control.button;

import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.Map;

public class GUIButtonElement extends AbstractGUIComponentElement<GUIButtonElementTemplate, GUIButtonElement.Config, String> {

    private JButton button = null;

    public GUIButtonElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIButtonElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "button", template, config, GUIButtonElementTemplate.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.template.getExternal());

        this.button = new JButton();
        this.button.setText(this.value);
        super.registerEvents(this.button, this.config.events);
        super.addSubComponent(panel, this.button, this.template.getButton());
        super.loadFancyTemplates(this.button, this.config.type, this.template.getButtonTypes());

        return panel;
    }

    @Override
    public synchronized boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        if (this.button != null) {
            this.button.setText(data.toString());
        }
        this.value = data.toString();
        return true;
    }

    @Override
    public Object getValue() {
        return this.button == null ? this.value : this.button.getText();
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIButtonElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String> {
        public String type;
        public EventConfig[] events;
    }
}
