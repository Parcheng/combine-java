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
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.button = new JButton();
        super.loadTemplates(this.button, this.sysTemplate.getButton(), this.template.getButton());
        this.button.setText(this.value);
        super.registerEvents(this.button, this.config.events);

        panel.add(this.button);
        return panel;
    }

    @Override
    public boolean setValue(Object data) {
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
        public EventConfig[] events;
    }
}
