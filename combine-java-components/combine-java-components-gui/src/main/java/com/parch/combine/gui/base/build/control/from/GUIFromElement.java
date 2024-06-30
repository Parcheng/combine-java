package com.parch.combine.gui.base.build.control.from;

import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.*;
import java.util.Map;

public class GUIFromElement extends AbstractGUIComponentElement<GUIFromElementTemplate, GUIFromElement.Config, String> {

    private JLabel text = null;

    public GUIFromElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIFromElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "text", template, config, GUIFromElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.text = new JLabel();
        super.loadTemplates(this.text, this.sysTemplate.getText(), this.template.getText());
        this.text.setText(this.value);
        super.registerEvents(this.text, this.config.events);

        panel.add(this.text);
        return panel;
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        if (this.text != null) {
            this.text.setText(data.toString());
        }
        this.value = data.toString();

        return true;
    }

    @Override
    public Object getValue() {
        return this.text == null ? this.value : this.text.getText();
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIFromElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String> {
        public EventConfig[] events;
    }
}
