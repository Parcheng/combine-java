package com.parch.combine.gui.base.build.control.label;

import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Map;

public class GUILabelElement extends AbsGUIElement<GUILabelElementTemplate, GUILabelElement.Config> {

    private JLabel label = null;

    public GUILabelElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUILabelElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "label", template, config, GUILabelElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.label = new JLabel();
        super.loadTemplates(this.label, this.sysTemplate.getLabel(), this.template.getLabel());
        this.label.setText(this.config.text);
        super.registerEvents(this.label, this.config.events);

        panel.add(this.label);
        return panel;
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        if (this.label != null) {
            this.label.setText(data.toString());
        }
        this.config.text = data.toString();

        return true;
    }

    @Override
    public Object getValue() {
        return this.label == null ? config.text : this.label.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUILabelElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config {
        public String text;
        public EventConfig[] events;
    }
}
