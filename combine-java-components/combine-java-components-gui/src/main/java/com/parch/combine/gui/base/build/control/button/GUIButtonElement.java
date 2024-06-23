package com.parch.combine.gui.base.build.control.button;

import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.Map;

public class GUIButtonElement extends AbsGUIElement<GUIButtonElementTemplate, GUIButtonElement.Config> {

    private JButton button = null;

    public GUIButtonElement(String scopeKey, String elementId, Map<String, Object> data, GUIButtonElementTemplate template, Config config) {
        super(scopeKey, elementId, data, "button", template, config, GUIButtonElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.button = new JButton();
        super.loadTemplates(this.button, this.sysTemplate.getButton(), this.template.getButton());
        this.button.setText(this.config.text);
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
        this.config.text = data.toString();
        return true;
    }

    @Override
    public Object getValue() {
        return this.button == null ? config.text : this.button.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIButtonElement(this.scopeKey, this.id, this.data, this.template, this.config);
    }

    public static class Config {
        public String text;
        public EventConfig[] events;
    }
}
